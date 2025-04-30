package com.metacoding.springrocketdanv1.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class UserResponse {

    @Data
    public static class SessionUserDTO {
        private Integer id;
        private String username;
        private String email;
        private String fileUrl;
        private String userType;
        private Integer companyId;
        private String companyName;

        public SessionUserDTO(Integer id, String username, String email, String fileUrl, String userType, Integer companyId, String companyName) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.fileUrl = fileUrl;
            this.userType = userType;
            this.companyId = companyId;
            this.companyName = companyName;
        }
    }

    @Data
    public static class PersonListDTO {
        private Integer userId;
        private String username;
        private String jobGroupName;
        private String fileUrl;

        public PersonListDTO(Integer userId, String username, String jobGroupName, String fileUrl) {
            this.userId = userId;
            this.username = username;
            this.jobGroupName = jobGroupName;
            this.fileUrl = fileUrl;
        }
    }
}