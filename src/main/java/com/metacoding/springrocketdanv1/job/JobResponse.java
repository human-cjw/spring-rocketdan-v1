package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRangeResponse;
import lombok.Data;

import java.sql.Timestamp;

public class JobResponse {

    @Data
    public static class DTO {
        private String title;
        private String careerLevel;

        private String nameKr;
    }

    @Data
    public static class DetailDTO {
        private String title;
        private Timestamp deadline;
        private String careerLevel;
        private Timestamp createdAt;
        private String description;
        private String location;
        private String employmentType;
        private String workField;
        private String nameKr;
        private SalaryRangeResponse.SalaryRangeDTO salaryRange;

        public DetailDTO(String title, Timestamp deadline, String careerLevel,
                         Timestamp createdAt, String description, String location,
                         String employmentType, String workField, String nameKr,
                         SalaryRangeResponse.SalaryRangeDTO salaryRange) {
            this.title = title;
            this.deadline = deadline;
            this.careerLevel = careerLevel;
            this.createdAt = createdAt;
            this.description = description;
            this.location = location;
            this.employmentType = employmentType;
            this.workField = workField;
            this.nameKr = nameKr;
            this.salaryRange = salaryRange;
        }
    }
}