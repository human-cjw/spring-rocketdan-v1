package com.metacoding.springrocketdanv1.resume;

import com.metacoding.springrocketdanv1.jobGroup.JobGroup;
import com.metacoding.springrocketdanv1.resumeTechStack.ResumeTechStack;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.user.User;
import lombok.Data;

import java.util.List;

public class ResumeRequest {

    @Data
    public static class UpdateDTO {
        private String title; // 제목
        private String summary; // 자기소개
        private String portfolioUrl; // 포트폴리오 주소
        private String gender; // 성별
        private String education; // 학력사항
        private String birthdate; // 생년월일
        private String major; // 전공
        private String graduationType; // 졸업, 재학, 휴학
        private String phone; // 연락처
        private String enrollmentDate; // 입학날짜
        private String graduationDate; // 졸업날짜
        private String careerLevel;
        private Boolean isDefault;
        private String careerCompanyName;
        private String careerStartDate;
        private String careerEndDate;
        private String certificationName;
        private String certificationIssuer;
        private String certificationIssuedDate;
        private Integer salaryRangeId;
        private Integer jobGroupId;
        private List<Integer> techStackIds;
    }

    @Data
    public static class SaveDTO {
        private String title; // 제목
        private String summary; // 자기소개
        private String portfolioUrl; // 포트폴리오 주소
        private String gender; // 성별
        private String education; // 학력사항
        private String birthdate; // 생년월일
        private String major; // 전공
        private String graduationType; // 졸업, 재학, 휴학
        private String phone; // 연락처
        private String enrollmentDate; // 입학날짜
        private String graduationDate; // 졸업날짜
        private String careerLevel;
        private Boolean isDefault;
        private String careerCompanyName;
        private String careerStartDate;
        private String careerEndDate;
        private String certificationName;
        private String certificationIssuer;
        private String certificationIssuedDate;
        private Integer salaryRangeId;
        private Integer jobGroupId;
        private List<Integer> techStackIds;

        public Resume toEntity(Integer userId) {
            Resume resume = Resume.builder()
                    .user(User.builder().id(userId).build())
                    .title(title)
                    .summary(summary)
                    .portfolioUrl(portfolioUrl)
                    .gender(gender)
                    .education(education)
                    .birthdate(birthdate)
                    .major(major)
                    .graduationType(graduationType)
                    .phone(phone)
                    .enrollmentDate(enrollmentDate)
                    .graduationDate(graduationDate)
                    .careerLevel(careerLevel)
                    .isDefault(isDefault)
                    .salaryRange(SalaryRange.builder().id(salaryRangeId).build())
                    .jobGroup(JobGroup.builder().id(jobGroupId).build())
                    .build();

            resume.getResumeTechStacks().clear();
            for (Integer techStackId : techStackIds) {
                resume.getResumeTechStacks().add(
                        ResumeTechStack.builder()
                                .resume(resume)
                                .techStack(TechStack.builder()
                                        .id(techStackId)
                                        .build()
                                ).build()
                );
            }

            return resume;
        }
    }
}
