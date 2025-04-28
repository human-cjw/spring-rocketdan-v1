package com.metacoding.springrocketdanv1.application;

import com.metacoding.springrocketdanv1.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;
    private final HttpSession session;

    @GetMapping("/user/job/{jobId}/apply-form")
    public String applyForm(@PathVariable("jobId") Integer jobId,
                            HttpServletRequest request) {
        UserResponse.SessionUserDTO sessionUserDTO = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");

        ApplicationResponse.ApplyDTO respDTO = applicationService.지원보기(jobId, sessionUserDTO);

        request.setAttribute("model", respDTO);

        return "user/application/apply-form";
    }

    @PostMapping("/user/job/{jobId}/apply/save")
    public String applyForm(@PathVariable("jobId") Integer jobId,
                            ApplicationRequest.SaveDTO reqDTO) {
        UserResponse.SessionUserDTO sessionUserDTO = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");
        applicationService.지원하기(jobId, reqDTO, sessionUserDTO.getId());
        return "redirect:/";
    }
}
