package com.metacoding.springrocketdanv1.resume;

import com.metacoding.springrocketdanv1.career.Career;
import com.metacoding.springrocketdanv1.certification.Certification;
import com.metacoding.springrocketdanv1.jobGroup.JobGroupResponse;
import com.metacoding.springrocketdanv1.resumeTechStack.ResumeTechStackResponse;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRangeResponse;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ResumeResponse {

    @Data
    public static class UpdateDTO {
        private Integer id;
        private String title;
        private String summary;
        private String education;
        private String birthdate;
        private String major;
        private String graduationType;
        private String phone;
        private String portfolioUrl;
        private String createdAt;
        private String jobGroupId;
        private String enrollmentDate;
        private String graduationDate;
        private boolean isDefault;
        private String name;
        private String email;
        private List<Certification> certifications; // 다른 테이블에서 가지고 온 것
        private List<TechStack> resumeTechStacks; // 다른 테이블에서 가지고 온 것 (유저가 갖고 있는거)
        private List<Career> careers;
        private List<ResumeTechStackResponse.ResumeTechStackResponseDTO> techStacks;
        private List<GraduationTypeDTO> graduationTypes;
        private List<CareerLevelTypeDTO> careerLevelTypes;
        private List<GenderTypeDTO> genderTypes;
        private List<SalaryRangeResponse.SalaryRangeUpdateDTO> salaryRangeUpdateDTOs;
        private List<JobGroupResponse.JobGroupUpdateDTO> jobGroupUpdateDTOs;

        public UpdateDTO(Resume resume,
                         List<Certification> certifications,
                         List<TechStack> resumeTechStacks,
                         String email,
                         String name,
                         List<Career> careers,
                         List<ResumeTechStackResponse.ResumeTechStackResponseDTO> techStacks,
                         List<GraduationTypeDTO> graduationTypes,
                         List<CareerLevelTypeDTO> careerLevelTypes,
                         List<GenderTypeDTO> genderTypes,
                         List<SalaryRangeResponse.SalaryRangeUpdateDTO> salaryRangeUpdateDTOs,
                         List<JobGroupResponse.JobGroupUpdateDTO> jobGroupUpdateDTOs
        ) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.summary = resume.getSummary();
            this.education = resume.getEducation();
            this.birthdate = resume.getBirthdate();
            this.major = resume.getMajor();
            this.enrollmentDate = resume.getEnrollmentDate();
            this.graduationDate = resume.getGraduationDate();
            this.graduationType = resume.getGraduationType();
            this.phone = resume.getPhone();
            this.portfolioUrl = resume.getPortfolioUrl();
            this.createdAt = resume.getCreatedAt().toString().substring(0, 10);
            this.certifications = certifications;
            this.resumeTechStacks = resumeTechStacks;
            this.email = email;
            this.name = name;
            this.careers = careers;
            this.techStacks = techStacks;
            this.graduationTypes = graduationTypes;
            this.careerLevelTypes = careerLevelTypes;
            this.genderTypes = genderTypes;
            this.isDefault = resume.getIsDefault();
            this.salaryRangeUpdateDTOs = salaryRangeUpdateDTOs;
            this.jobGroupUpdateDTOs = jobGroupUpdateDTOs;
        }
    }

    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String summary;
        private String education;
        private String birthdate;
        private String major;
        private String graduationType;
        private String phone;
        private String portfolioUrl;
        private String createdAt;
        private String jobGroupId;
        private String enrollmentDate;
        private String graduationDate;
        private boolean isDefault;
        private String name;
        private String email;
        private List<Certification> certifications; // 다른 테이블에서 가지고 온 것
        private List<TechStack> resumeTechStacks; // 다른 테이블에서 가지고 온 것 (유저가 갖고 있는거)
        private List<Career> careers;
        private List<ResumeTechStackResponse.ResumeTechStackResponseDTO> techStacks;
        private List<GraduationTypeDTO> graduationTypes;
        private List<CareerLevelTypeDTO> careerLevelTypes;
        private List<GenderTypeDTO> genderTypes;
        private boolean isOwner;

        public DetailDTO(Resume resume, List<Certification> certifications, List<TechStack> resumeTechStacks,
                         String email, String name, List<Career> careers,
                         List<ResumeTechStackResponse.ResumeTechStackResponseDTO> techStacks,
                         List<GraduationTypeDTO> graduationTypes, List<CareerLevelTypeDTO> careerLevelTypes,
                         List<GenderTypeDTO> genderTypes, Integer userId) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.summary = resume.getSummary();
            this.education = resume.getEducation();
            this.birthdate = resume.getBirthdate();
            this.major = resume.getMajor();
            this.enrollmentDate = resume.getEnrollmentDate();
            this.graduationDate = resume.getGraduationDate();
            this.graduationType = resume.getGraduationType();
            this.phone = resume.getPhone();
            this.portfolioUrl = resume.getPortfolioUrl();
            this.createdAt = resume.getCreatedAt().toString().substring(0, 10);
            this.certifications = certifications;
            this.resumeTechStacks = resumeTechStacks;
            this.email = email;
            this.name = name;
            this.careers = careers;
            this.techStacks = techStacks;
            this.graduationTypes = graduationTypes;
            this.careerLevelTypes = careerLevelTypes;
            this.genderTypes = genderTypes;
            this.isDefault = resume.getIsDefault();
            this.isOwner = resume.getUser().getId().equals(userId);
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

    @Data
    public static class ResumeListDTO {
        private boolean isAll;
        private boolean isDefault;
        private List<ResumeItemDTO> resumeItems = new ArrayList<>();

        public ResumeListDTO(List<Resume> resumes, boolean isDefault) {
            if (isDefault) {
                this.isDefault = true;
            } else {
                this.isAll = true;
            }

            for (Resume resume : resumes) {
                this.resumeItems.add(new ResumeItemDTO(resume.getId(), resume.getTitle(), resume.getCreatedAt().toString().substring(0, 10)));
            }
        }

        @Data
        class ResumeItemDTO {
            private Integer id;
            private String title;
            private String createdAt;

            public ResumeItemDTO(Integer id, String title, String createdAt) {
                this.id = id;
                this.title = title;
                this.createdAt = createdAt;
            }
        }
    }
}



