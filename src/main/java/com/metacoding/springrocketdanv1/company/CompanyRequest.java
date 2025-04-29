package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.companyTechStack.CompanyTechStack;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.user.User;
import com.metacoding.springrocketdanv1.user.UserResponse;
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

        public Company toEntity(UserResponse.SessionUserDTO sessionUserDTO, WorkField workField, List<TechStack> techStackList) {
            Company company = Company.builder()
                    .nameKr(nameKr)
                    .nameEn(nameEn)
                    .introduction(introduction)
                    .oneLineIntro(oneLineIntro)
                    .startDate(startDate)
                    .businessNumber(businessNumber)
                    .email(email)
                    .contactManager(contactManager)
                    .address(address)
                    .user(User.builder()
                            .id(sessionUserDTO.getId())
                            .build())
                    .workField(workField)
                    .build();

            for (TechStack techStack : techStackList) {
                CompanyTechStack cts = new CompanyTechStack(company, techStack);
                company.getCompanyTechStackList().add(cts);
            }

            return company;
        }
    }

    @Data
    public static class UpdateDTO {
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
        private Integer workFieldId;
        private List<String> techStack;
    }
}
