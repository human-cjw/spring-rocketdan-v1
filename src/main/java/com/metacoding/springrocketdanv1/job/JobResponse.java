package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.salaryRange.SalaryRangeResponse;
import lombok.Data;

import java.sql.Timestamp;

public class JobResponse {

    @Data
    public static class DTO {
        private int id;
        private String title;
        private String careerLevel;

        private String nameKr;
    }

    @Data
    public static class DetailDTO {
        private String title;
        private String deadline;
        private String careerLevel;
        private Timestamp createdAt;
        private String description;
        private String location;
        private String employmentType;
        private String workField;
        private String nameKr;
        private SalaryRangeResponse.SalaryRangeDTO salaryRange;
        private Integer companyId;

        public DetailDTO(String title, String deadline, String careerLevel,
                         Timestamp createdAt, String description, String location,
                         String employmentType, String workField, String nameKr,
                         SalaryRangeResponse.SalaryRangeDTO salaryRange, Integer companyId) {
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
            this.companyId = companyId;
        }
    }
}