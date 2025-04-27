package com.metacoding.springrocketdanv1.application;

import com.metacoding.springrocketdanv1.user.UserResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;
    private final HttpSession session;

    @GetMapping("/user/job/{jobId}/apply-form")
    public String applyForm(@PathVariable("jobId") Integer jobId) {
        UserResponse.SessionUserDTO sessionUserDTO = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");

        return "user/application/apply-form";
    }
}
