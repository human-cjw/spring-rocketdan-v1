package com.metacoding.springrocketdanv1.jobTechStack;

import lombok.Data;

public class JobTechStackResponse {

    @Data
    public static class JobTechStackUpdateDTO {
        private Integer id;
        private String name;
        private boolean isChecked;

        public JobTechStackUpdateDTO(
                Integer id,
                String name,
                boolean isChecked
        ) {
            this.id = id;
            this.name = name;
            this.isChecked = isChecked;
        }
    }
}
