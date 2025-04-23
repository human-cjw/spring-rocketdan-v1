package com.metacoding.springrocketdanv1.resume;

import com.metacoding.springrocketdanv1.career.CareerResponse;
import com.metacoding.springrocketdanv1.career.CareerService;
import com.metacoding.springrocketdanv1.user.User;
import com.metacoding.springrocketdanv1.user.UserResponse;
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

    private final UserService userService;

    private final ResumeService resumeService;
    private final HttpSession session;

    @GetMapping("/resume-detail/{id}")
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request) {
        Integer sessionUserId = 1; // 테스트용 userId, 실제 DB에 있는 userId여야 함

        ResumeResponse.DetailDTO detailDTO = resumeService.resumeDetail(id, sessionUserId);

        User user = userService.findById(id, sessionUserId);
        request.setAttribute("model", detailDTO);
        request.setAttribute("user", user);
        return "resume/detail";
    }

}
