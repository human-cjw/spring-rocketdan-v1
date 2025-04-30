package com.metacoding.springrocketdanv1.job;


import com.metacoding.springrocketdanv1.jobBookmark.JobBookmarkService;
import com.metacoding.springrocketdanv1.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    private final HttpSession session;
    private final JobBookmarkService jobBookmarkService;

    @GetMapping("/")
    public String list(Model models, JobResponse.DTO dto) {
        List<JobResponse.DTO> jobllist = jobService.글목록보기();
        models.addAttribute("models", jobllist);
        models.addAttribute("nameKr", dto.getNameKr());
        return "redirect:/job";
    }

    @GetMapping("/job/{jobId}")
    public String show(@PathVariable("jobId") Integer id, HttpSession session, Model model) {
        UserResponse.SessionUserDTO sessionUser = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");
        Integer userId = (sessionUser != null) ? sessionUser.getId() : null;

        JobResponse.DetailDTO jobDetail = jobService.글상세보기(id, userId);

        model.addAttribute("jobDetail", jobDetail);
        model.addAttribute("nameKr", jobDetail.getNameKr());
        model.addAttribute("salaryRange", jobDetail.getSalaryRange());
        return "job/detail";
    }

    @GetMapping("/job/save-form")
    public String saveForm(HttpServletRequest request) {
        JobResponse.JobSaveDTO respDTO = jobService.등록보기();
        request.setAttribute("model", respDTO);
        return "job/save-form";
    }

    @PostMapping("/job/save")
    public String save(JobRequest.JobSaveDTO reqDTO) {
        UserResponse.SessionUserDTO sessionUserDTO = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");
        jobService.등록하기(reqDTO, sessionUserDTO.getCompanyId());

        return "redirect:/company/job";
    }

    @GetMapping("/job/{jobId}/update-form")
    public String updateForm(@PathVariable("jobId") Integer jobId,
                             HttpServletRequest request) {
        JobResponse.JobUpdateDTO respDTO = jobService.수정보기(jobId);
        request.setAttribute("model", respDTO);
        return "job/update-form";
    }

    @PostMapping("/job/{jobId}/update")
    public String update(@PathVariable("jobId") Integer jobId,
                         JobRequest.JobUpdateDTO reqDTO) {
        jobService.수정하기(jobId, reqDTO);
        return "redirect:/job/" + jobId;
    }
}