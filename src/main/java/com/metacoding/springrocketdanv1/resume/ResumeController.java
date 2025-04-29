package com.metacoding.springrocketdanv1.resume;

import com.metacoding.springrocketdanv1.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final ResumeRepository resumeRepository;
    private final HttpSession session;


    @GetMapping("/resume/{id}")
    public String detail(@PathVariable("id") Integer resumeId, HttpServletRequest request) {
        ResumeResponse.DetailDTO detailDTO = resumeService.이력서상세보기(resumeId);
        System.out.println("🧪 DetailDTO title: " + detailDTO.getTitle());
        request.setAttribute("model", detailDTO);

        return "resume/detail";
    }

    @GetMapping("/resume/{id}/update-form")
    public String updateForm(@PathVariable("id") Integer resumeId, HttpServletRequest request) {

        ResumeResponse.DetailDTO detailDTO = resumeService.이력서수정보기(resumeId);
        request.setAttribute("model", detailDTO);

        return "resume/update-form";
    }


    @PostMapping("/resume/{resumeId}/update")
    public String update(@PathVariable("resumeId") Integer resumeId, ResumeRequest.UpdateDTO requestDTO) {
        System.out.println(requestDTO.getIsDefault());
        // 1. 기본 이력서 체크 여부
        boolean isDefault = Boolean.TRUE.equals(requestDTO.getIsDefault());

        // 2. 이력서 수정
        resumeService.이력서수정하기(resumeId, requestDTO);

        // 3. 만약 isDefault가 true면 이 이력서를 기본 이력서로 설정
        if (isDefault) {
            resumeService.기본이력서설정(resumeId);
        }

        return "redirect:/resume/" + resumeId;
    }


    @GetMapping("/user/resume")
    public String list(HttpServletRequest request,
                       @RequestParam(required = false, value = "default", defaultValue = "") String isDefault) {
        UserResponse.SessionUserDTO sessionUserDTO = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");
        ResumeResponse.ResumeListDTO respDTO = resumeService.이력서목록보기(sessionUserDTO.getId(), Boolean.parseBoolean(isDefault));

        request.setAttribute("model", respDTO);

        return "resume/list";
    }

}
