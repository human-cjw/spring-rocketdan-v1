package com.metacoding.springrocketdanv1.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CompanyResponse {

    @Getter
    @Setter
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
        private boolean isOwner;
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
        private List<CompanyResponse.WorkFieldDTO> workFields;
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

    @Data
    public static class WorkFieldDTO {
        private Integer id;
        private String name;
        private boolean isChecked;

        public WorkFieldDTO(Integer id, String name, boolean isChecked) {
            this.id = id;
            this.name = name;
            this.isChecked = isChecked;
        }
    }
}
