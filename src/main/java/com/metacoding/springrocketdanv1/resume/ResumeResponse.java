package com.metacoding.springrocketdanv1.resume;

import com.metacoding.springrocketdanv1.career.Career;
import com.metacoding.springrocketdanv1.certification.Certification;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ResumeResponse {

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
        private List<TechStack> resumeTechStacks; // 다른 테이블에서 가지고 온 것
        private List<Career> careers;
        private List<CareerLevelDTO> careerLevels;
        private List<GenderDTO> genders;
        private boolean isOwner;

        public DetailDTO(Resume resume, List<Certification> certifications, List<TechStack> resumeTechStacks, String email, String name, List<Career> careers, Integer userId) {
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
            this.isDefault = resume.getIsDefault();
            this.isOwner = resume.getUser().getId().equals(userId);

            this.careerLevels = List.of(
                    new CareerLevelDTO("신입", "신입", resume.getCareerLevel().equals("신입")),
                    new CareerLevelDTO("경력", "경력", resume.getCareerLevel().equals("경력"))
            );

            this.genders = List.of(
                    new GenderDTO("남", "남", resume.getGender().equals("남")),
                    new GenderDTO("여", "여", resume.getGender().equals("여"))
            );

        }

        @Data
        class GenderDTO {
            private String value;
            private String name;
            private boolean isChecked;

            public GenderDTO(String value, String name, boolean isChecked) {
                this.value = value;
                this.name = name;
                this.isChecked = isChecked;
            }
        }

        @Data
        class CareerLevelDTO {
            private String value;
            private String name;
            private boolean isChecked;

            public CareerLevelDTO(String value, String name, boolean isChecked) {
                this.value = value;
                this.name = name;
                this.isChecked = isChecked;
            }
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


