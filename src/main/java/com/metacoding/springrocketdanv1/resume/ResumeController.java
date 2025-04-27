package com.metacoding.springrocketdanv1.resume;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @GetMapping("/resume/{id}")
    public String detail(@PathVariable("id") Integer resumeId, HttpServletRequest request) {

        ResumeResponse.DetailDTO detailDTO = resumeService.이력서상세보기(resumeId);
        System.out.println("🧪 DetailDTO title: " + detailDTO.getTitle());
        request.setAttribute("model", detailDTO);

        return "resume/detail";
    }

    @GetMapping("/resume/{id}/update-form")
    public String updateForm(@PathVariable Integer id) {

        return "resume/update-form";
    }

    @GetMapping("/person")
    public String list(HttpServletRequest request) {
        List<Resume> resumes = resumeService.구직자목록보기();
        request.setAttribute("models",resumes);
        return "person/list";
    }
}
