package com.metacoding.springrocketdanv1.resume;

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

    @GetMapping("/resume-detail/{id}")
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request) {
        Integer sessionUserId = 2; // 테스트용 userId, 실제 DB에 있는 userId여야 함

        ResumeResponse.DetailDTO detailDTO = resumeService.이력서상세보기(id, sessionUserId);

        request.setAttribute("model", detailDTO);
        return "resume/detail";
    }

}
