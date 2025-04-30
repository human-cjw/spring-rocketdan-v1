package com.metacoding.springrocketdanv1.jobBookmark;


import com.metacoding.springrocketdanv1.job.JobResponse;
import com.metacoding.springrocketdanv1.user.UserResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class JobBookmarkController {
    private final JobBookmarkRepository jobBookmarkRepository;
    private final JobBookmarkService jobBookmarkService;

    @GetMapping("/job")
    public String jobList(HttpSession session, Model model) {
        UserResponse.SessionUserDTO sessionUser = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");

        Integer sessionUserId = (sessionUser != null) ? sessionUser.getId() : null;

        List<JobBookmarkResponse.JobListWithBookmarkDTO> dtoList =
                jobBookmarkService.getAllJobsWithBookmarkInfo(sessionUserId); // null이면 북마크 표시 없이

        Long bookmarkCount = (sessionUserId != null) ? jobBookmarkService.count(sessionUserId) : 0L;

        model.addAttribute("models", dtoList);
        model.addAttribute("bookmarkCount", bookmarkCount);
        return "job/list";
    }

    @Transactional
    @PostMapping("/job/{jobId}/bookmark")
    public String toggle(@PathVariable("jobId") Integer jobId, HttpSession session) {
        UserResponse.SessionUserDTO sessionUser = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");
        if (sessionUser == null) return "redirect:/login";

        JobBookmarkRequest.SaveDTO dto = new JobBookmarkRequest.SaveDTO();
        dto.setJobId(jobId);

        jobBookmarkService.북마크토글(dto, sessionUser.getId());

        return "redirect:/job";
    }

    @GetMapping("/user/bookmark")
    public String bookmarkList(HttpSession session, Model model) {
        UserResponse.SessionUserDTO sessionUser = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");
        if (sessionUser == null) return "redirect:/login";

        List<JobBookmarkResponse.BookmarkListDTO> dtoList =
                jobBookmarkService.getBookmarkList(sessionUser.getId());

        model.addAttribute("model", dtoList);

        return "bookmark/jobBookmark";
    }

    @GetMapping("/user/bookmark/{bookmarkId}/delete")
    public String deleteBookmark(@PathVariable("bookmarkId") Integer bookmarkId) {
        jobBookmarkService.북마크삭제(bookmarkId);
        return "redirect:/user/bookmark";
    }

    @PostMapping("/job-bookmark/{jobId}/toggle")
    public String toggleFromDetail(@PathVariable Integer jobId, HttpSession session) {
        UserResponse.SessionUserDTO sessionUser = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");
        if (sessionUser == null) return "redirect:/login";

        JobBookmarkRequest.SaveDTO dto = new JobBookmarkRequest.SaveDTO();
        dto.setJobId(jobId);

        jobBookmarkService.북마크토글(dto, sessionUser.getId());

        // 현재 상세 페이지를 그대로 다시 보여주도록 redirect
        return "redirect:/job/" + jobId;
    }
}
