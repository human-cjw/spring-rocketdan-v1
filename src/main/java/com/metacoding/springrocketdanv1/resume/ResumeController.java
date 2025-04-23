package com.metacoding.springrocketdanv1.resume;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("/user/resume-detail") // URI 주소
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {
        Resume resume = resumeService.이력서상세보기(id);
        request.setAttribute("model", resume);
        return "resume-detail"; // 파일명
    }

}
