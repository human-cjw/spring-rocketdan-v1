package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.workField.WorkField;
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
        private WorkField workField;
        private SalaryRange salaryRange;

        private String nameKr;
    }
}
