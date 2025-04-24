package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.user.User;
import com.metacoding.springrocketdanv1.workField.WorkField;
import lombok.Data;

import java.util.List;

@Data
public class CompanyRequest {

    @Data
    public static class CompanySaveDTO {
        private String nameKr;
        private String nameEn;
        private String introduction;
        private String oneLineIntro;
        private String startDate;
        private String businessNumber;
        private List<String> techStack;
        private String email;
        private String contactManager;
        private String address;
        private String workFieldName;

        public Company toEntity(User user, WorkField workField) {
            return Company.builder()
                    .nameKr(nameKr)
                    .nameEn(nameEn)
                    .introduction(introduction)
                    .oneLineIntro(oneLineIntro)
                    .startDate(startDate)
                    .businessNumber(businessNumber)
                    .email(email)
                    .contactManager(contactManager)
                    .address(address)
                    .user(user)
                    .workField(workField)
                    .build();
        }
    }
}
