package com.metacoding.springrocketdanv1.job;

import lombok.Data;

public class JobResponse {

    @Data
    public static class DTO {
        private String title;
        private String careerLevel;
        
        private String nameKr;
    }
}
