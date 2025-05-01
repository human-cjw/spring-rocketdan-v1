package com.metacoding.springrocketdanv1.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;
        private String userType;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .userType(userType)
                    .build();
        }
    }
    @Data
    public static class LoginDTO {
        private String username;
        private String password;
        private String rememberMe;
        }
}