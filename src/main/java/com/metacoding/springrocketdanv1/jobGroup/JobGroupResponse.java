package com.metacoding.springrocketdanv1.jobGroup;

import lombok.Data;

public class JobGroupResponse {

    @Data
    public static class JobGroupUpdateDTO {
        private Integer id;
        private String name;
        private boolean isSelected;

        public JobGroupUpdateDTO(Integer id, String name, boolean isSelected) {
            this.id = id;
            this.name = name;
            this.isSelected = isSelected;
        }
    }
}
