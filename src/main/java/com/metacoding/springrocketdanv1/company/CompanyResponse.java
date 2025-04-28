package com.metacoding.springrocketdanv1.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class CompanyResponse {

    @Getter
    @NoArgsConstructor
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
}
