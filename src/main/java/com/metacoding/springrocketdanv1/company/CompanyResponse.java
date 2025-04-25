package com.metacoding.springrocketdanv1.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

public class CompanyResponse {

    @Getter
    @AllArgsConstructor
    public static class CompanyResponseDTO {
        private String nameKr;
        private String nameEn;
        private String ceo;
        private String businessNumber;
        private String email;
        private String phone;
        private String address;
        private String introduction;
        private String oneLineIntro;
        private String homepageUrl;
        private String logoImageUrl;
        private String infoImageUrl;
        private String contactManager;
        private String startDate;
        private String workFieldName;
        private List<String> techStackList;
    }

    @Data
    public static class UpdateFormDTO {
        private Integer id;
        private String nameKr;
        private String nameEn;
        private String oneLineIntro;
        private String introduction;
        private String startDate;
        private String businessNumber;
        private String email;
        private String contactManager;
        private String address;
        private String workFieldName;
        private List<CompanyResponse.TechStackDTO> techStacks;
    }

    @Data
    public static class TechStackDTO {
        private String name;
        private boolean isChecked;

        public TechStackDTO(String name, boolean isChecked) {
            this.name = name;
            this.isChecked = isChecked;
        }

    }
}
