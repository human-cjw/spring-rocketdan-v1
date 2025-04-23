package com.metacoding.springrocketdanv1.resume;

import lombok.Data;

import java.sql.Timestamp;

public class ResumeResponse {

    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String summary;
        private String gender;
        private String education;
        private String careerLevel;
        private String birthdate;
        private String major;
        private String graduationType;
        private String phone;
        private String portfolioUrl;
        private Timestamp createdAt;
        private int userId;
        private String salaryRangeId;
        private String jobGroupId;
        private String enrollmentDate; // 입학날짜
        private String graduationDate;
        private String isDefault;


    }

}
