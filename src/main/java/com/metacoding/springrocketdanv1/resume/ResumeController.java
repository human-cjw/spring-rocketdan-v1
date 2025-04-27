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

    @PostMapping("/resume/{id}/update")
    public String update(@PathVariable("id") Integer resumeId, ResumeRequest.UpdateDTO requestDTO) {
        // 수정 요청 처리
        resumeService.이력서수정완료(resumeId, requestDTO);

        // 상세 페이지로 리다이렉트 (새로고침된 상태로 열림)
        return "redirect:/resume/" + resumeId;
    }

    @PostMapping("/resume/{id}/default")
    public String setDefault(@PathVariable("id") Integer resumeId) {
        resumeService.기본이력서설정(resumeId);
        return "redirect:/resume/" + resumeId; // 설정 후 상세페이지로 이동
    }
}
