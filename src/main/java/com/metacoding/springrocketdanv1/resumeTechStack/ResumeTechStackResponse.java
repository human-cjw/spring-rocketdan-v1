package com.metacoding.springrocketdanv1.resumeTechStack;

import lombok.Data;

public class ResumeTechStackResponse {
    // 이안에 DTO 만들기
    @Data
    public static class ResumeTechStackResponseDTO {
        private Integer id;
        private String name;
        private boolean isChecked;


        public ResumeTechStackResponseDTO(Integer id, String name, boolean isChecked) {
            this.id = id;
            this.name = name;
            this.isChecked = isChecked;
        }
    }


    public class ResumeTechStackUpdateDTO {
    }
}