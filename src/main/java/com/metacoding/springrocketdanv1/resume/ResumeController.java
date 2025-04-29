package com.metacoding.springrocketdanv1.resume;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;
    private final ResumeRepository resumeRepository;

    @GetMapping("/resume/{id}")
    public String detail(@PathVariable("id") Integer resumeId, HttpServletRequest request) {

        ResumeResponse.DetailDTO detailDTO = resumeService.이력서상세보기(resumeId);
        System.out.println("🧪 DetailDTO title: " + detailDTO.getTitle());
        request.setAttribute("model", detailDTO);

        return "resume/detail";
    }

    @GetMapping("/resume/{id}/update-form")
    public String updateForm(@PathVariable("id") Integer resumeId, HttpServletRequest request) {

        ResumeResponse.DetailDTO detailDTO = resumeService.이력서수정하기(resumeId);
        request.setAttribute("model", detailDTO);

        return "resume/update-form";
    }


    @PostMapping("/resume/{resumeId}/update")
    public String 이력서수정완료(@PathVariable("resumeId") Integer resumeId, ResumeRequest.UpdateDTO requestDTO) {
        // 1. 기본 이력서 체크 여부
        boolean isDefault = Boolean.TRUE.equals(requestDTO.getIsDefault());

        // 2. 이력서 수정
        resumeService.이력서수정완료(resumeId, requestDTO);

        // 3. 만약 isDefault가 true면 이 이력서를 기본 이력서로 설정
        if (isDefault) {
            resumeService.기본이력서설정(resumeId);
        }

        return "redirect:/resume/" + resumeId + "/update";
    }


}
