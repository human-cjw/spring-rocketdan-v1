package com.metacoding.springrocketdanv1.resume;

import com.metacoding.springrocketdanv1.career.Career;
import com.metacoding.springrocketdanv1.certification.Certification;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class ResumeResponse {

    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String summary;
        private String gender;
        private String education;
        private String careerLevel;
        private String birthdate;
        private String major;
        private String graduationType;
        private String phone;
        private String portfolioUrl;
        private Timestamp createdAt;
        private String jobGroupId;
        private String enrollmentDate;
        private String graduationDate;
        private String isDefault;
        private String name;
        private String email;
        private List<Certification> certifications; // 다른 테이블에서 가지고 온 것
        private List<TechStack> resumeTechStacks; // 다른 테이블에서 가지고 온 것 (유저가 갖고 있는거)
        private List<TechStack> TechStacks; // 다른 테이블에서 가지고 온 것 (모든 테크스택)
        private List<Career> careers;
        private boolean isCareerLevelNewbie;
        private boolean isCareerLevelOld;
        private boolean isFemale;
        private boolean isMale;

        public DetailDTO(Resume resume, List<Certification> certifications, List<TechStack> resumeTechStacks,
                         String email, String name, List<Career> careers, boolean isCareerLevelNewbie, boolean isCareerLevelOld,
                         boolean isFemale, boolean isMale, List<TechStack> TechStacks) {
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
            this.isCareerLevelNewbie = isCareerLevelNewbie;
            this.isCareerLevelOld = isCareerLevelOld;
            this.isFemale = isFemale;
            this.isMale = isMale;
            this.TechStacks = TechStacks;

        }
    }


}


