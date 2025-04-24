package com.metacoding.springrocketdanv1.job;

import lombok.Data;

public class JobRequest {

    @Data
    public static class saveDTO {
        String title;
        String description;
        String introduction;
        String startDate;
        String businessNumber;
        String email;
        String status;
        String address;
        String employmentType;
        String careerLevel;
        String location;

    }
    
}
