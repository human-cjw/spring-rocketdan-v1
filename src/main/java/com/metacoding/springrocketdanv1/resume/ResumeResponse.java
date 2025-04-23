package com.metacoding.springrocketdanv1.resume;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

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
        private String enrollmentDate;
        private String graduationDate;
        private String isDefault;

        private List<CareerDTO> careers;

        @Data
        public static class CareerDTO {

            private String careerId;
            private String careerName;
            private String startDate;
            private String endDate;
        }
    }

}
