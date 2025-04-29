package com.metacoding.springrocketdanv1.resume;

import com.metacoding.springrocketdanv1.career.Career;
import com.metacoding.springrocketdanv1.certification.Certification;
import com.metacoding.springrocketdanv1.resumeTechStack.ResumeTechStackResponse;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class ResumeResponse {

    @Data
    public static class DetailDTO {
        private Integer id;
        private String title; // 제목
        private String summary; // 자기소개
        private String gender; // 성별
        private String education; // 학력사항
        private String careerLevel; // 연차 (근데 안 쓰기로함)
        private String birthdate; // 생년월일
        private String major; // 전공
        private String graduationType; // 졸업, 재학, 휴학
        private String phone; // 연락처
        private String portfolioUrl; // 포트폴리오 url
        private Timestamp createdAt;
        private String jobGroupId;
        private String enrollmentDate; // 입학날짜
        private String graduationDate; // 졸업날짜
        private Boolean isDefault;
        private String name;
        private String email;
        private List<Certification> certifications; // 다른 테이블에서 가지고 온 것
        private List<TechStack> resumeTechStacks; // 다른 테이블에서 가지고 온 것 (유저가 갖고 있는거)
        private List<Career> careers;
        private List<ResumeTechStackResponse.ResumeTechStackResponseDTO> techStacks;
        private List<GraduationTypeDTO> graduationTypes;
        private List<CareerLevelTypeDTO> careerLevelTypes;
        private List<GenderTypeDTO> genderTypes;


        public DetailDTO(Resume resume, List<Certification> certifications, List<TechStack> resumeTechStacks,
                         String email, String name, List<Career> careers,
                         List<ResumeTechStackResponse.ResumeTechStackResponseDTO> techStacks,
                         List<GraduationTypeDTO> graduationTypes, List<CareerLevelTypeDTO> careerLevelTypes,
                         List<GenderTypeDTO> genderTypes) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.summary = resume.getSummary();
            this.gender = resume.getGender();
            this.education = resume.getEducation();
            this.careerLevel = resume.getCareerLevel();
            this.birthdate = resume.getBirthdate();
            this.major = resume.getMajor();
            this.enrollmentDate = resume.getEnrollmentDate();
            this.graduationDate = resume.getGraduationDate();
            this.graduationType = resume.getGraduationType();
            this.phone = resume.getPhone();
            this.portfolioUrl = resume.getPortfolioUrl();
            this.createdAt = resume.getCreatedAt();
            this.certifications = certifications;
            this.resumeTechStacks = resumeTechStacks;
            this.email = email;
            this.name = name;
            this.careers = careers;
            this.techStacks = techStacks;
            this.graduationTypes = graduationTypes;
            this.careerLevelTypes = careerLevelTypes;
            this.genderTypes = genderTypes;

        }

    }

    @Data
    public static class GraduationTypeDTO {
        private String value;
        private Boolean isSelected;

        public GraduationTypeDTO(String value, Boolean isSelected) {
            this.value = value;
            this.isSelected = isSelected;
        }
    }


    @Data
    public static class CareerLevelTypeDTO {
        private String value;
        private Boolean isSelected;

        public CareerLevelTypeDTO(String value, Boolean isSelected) {
            this.value = value;
            this.isSelected = isSelected;
        }
    }

    @Data
    public static class GenderTypeDTO {
        private String value;
        private Boolean isSelected;

        public GenderTypeDTO(String value, Boolean isSelected) {
            this.value = value;
            this.isSelected = isSelected;
        }
    }
}


