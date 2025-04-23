package com.metacoding.springrocketdanv1.resume;

import lombok.Data;

import java.sql.Timestamp;

public class ResumeResponse {

    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String gender;
        private int age;
        private String summary;
        private String careerLevel;
        private String education;
        private String birthdate;
        private String portfolioUrl;
        private String graduationType;
        private String major;
        private String phone;
        private String salaryRangeId;
        private Timestamp createdAt;

    }

}
