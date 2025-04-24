package com.metacoding.springrocketdanv1.resume;

import com.metacoding.springrocketdanv1.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final HttpSession session;
    private final UserService userService;

    @GetMapping("/resume-detail/{id}")
    public String detail(@PathVariable("id") Integer resumeId, HttpServletRequest request) {
        Integer sessionUserId = 1; // 테스트용 userId, 실제 DB에 있는 userId여야 함

        ResumeResponse.DetailDTO detailDTO = resumeService.이력서상세보기(resumeId, sessionUserId);
        System.out.println("🧪 DetailDTO title: " + detailDTO.getTitle());


        request.setAttribute("model", detailDTO);
        return "resume/detail";
    }

    @GetMapping("/resume/{id}/update-form")
    public String updateForm(@PathVariable Integer id) {

        return "resume/update-form";
    }

}
