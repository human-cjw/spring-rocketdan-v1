package com.metacoding.springrocketdanv1._core.interceptor;

import com.metacoding.springrocketdanv1._core.error.ex.Exception401;
import com.metacoding.springrocketdanv1._core.error.ex.ExceptionApi401;
import com.metacoding.springrocketdanv1.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        System.out.println("uri: " + uri);

        HttpSession session = request.getSession();
        UserResponse.SessionUserDTO sessionUser = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            if (uri.contains("/api")) {
                throw new ExceptionApi401("인증이 필요합니다");
            } else {
                throw new Exception401("인증이 필요합니다");
            }
        }

        return true;
    }
}