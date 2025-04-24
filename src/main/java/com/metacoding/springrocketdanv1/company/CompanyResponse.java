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
        private String ceo;
        private String email;
        private String address;
        private String homepageUrl;
        private String introduction;
        private String oneLineIntro;
        private String logoImageUrl;
        private String infoImageUrl;
        private String contactManager;
        private String workFieldName;
        private List<String> techStackList;
        private String startDate;
    }
}
