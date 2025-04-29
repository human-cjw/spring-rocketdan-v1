package com.metacoding.springrocketdanv1.resume;

import com.metacoding.springrocketdanv1.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;
    private final HttpSession session;

    @GetMapping("/resume/{id}")
    public String detail(@PathVariable("id") Integer resumeId, HttpServletRequest request) {
        UserResponse.SessionUserDTO sessionUserDTO = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");
        Integer userId = sessionUserDTO != null ? sessionUserDTO.getId() : null;
        ResumeResponse.DetailDTO detailDTO = resumeService.이력서상세보기(resumeId, userId);
        request.setAttribute("model", detailDTO);

        return "resume/detail";
    }

    @GetMapping("/resume/{id}/update-form")
    public String updateForm(@PathVariable Integer id) {

        return "resume/update-form";
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
