package com.metacoding.springrocketdanv1.user;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
<<<<<<< HEAD
    private final HttpSession session;


    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO, HttpServletResponse response) {
        User sessionUser = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", sessionUser);
        if(loginDTO.getRememberMe()==null){
            Cookie cookie = new Cookie("username",null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }else {
            Cookie cookie = new Cookie ("username", loginDTO.getUsername());
            cookie.setMaxAge(60*60*24*7);
            response.addCookie(cookie);
        }
        return "job/list";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        userService.회원가입(joinDTO);
        return "redirect:/login-form";
    }
=======
>>>>>>> master

    @GetMapping("/join-form")
    public String joinform() {
        return "user/join-form";
    }
<<<<<<< HEAD


=======
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        userService.회원가입(joinDTO);
        return "user/login-form";
    }
>>>>>>> master
}