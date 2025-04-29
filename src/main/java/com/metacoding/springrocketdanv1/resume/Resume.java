package com.metacoding.springrocketdanv1.resume;


import com.metacoding.springrocketdanv1.jobGroup.JobGroup;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@ToString
@NoArgsConstructor
@Getter
@Entity
@Table(name = "resume_tb")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    @Column(columnDefinition = "text")
    private String summary; // 자기소개
    private String gender; // 남 여
    private String careerLevel; // 0년차, 1년차, 2년차
    private String education; // 학력사항
    private String birthdate; // 생년월일
    private String major; // 전공
    private String graduationType; // 졸업, 재학, 휴학
    private String phone; // 연락처
    private String portfolioUrl; // 포트폴리오 url
    private String enrollmentDate; // 입학날짜
    private String graduationDate; // 졸업날짜
    private Boolean isDefault; // 기본 이력서 인가?

    @CreationTimestamp
    private Timestamp createdAt;

    // 유저 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // 연봉범위 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private SalaryRange salaryRange;

    // 직무 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private JobGroup jobGroup;


    public void update(ResumeRequest.UpdateDTO requestDTO) {
        this.title = requestDTO.getTitle();
        this.summary = requestDTO.getSummary();
        this.portfolioUrl = requestDTO.getPortfolioUrl();
        this.gender = requestDTO.getGender();
        this.education = requestDTO.getEducation();
        this.birthdate = requestDTO.getBirthdate();
        this.major = requestDTO.getMajor();
        this.graduationType = requestDTO.getGraduationType();
        this.phone = requestDTO.getPhone();
        this.enrollmentDate = requestDTO.getEnrollmentDate();
        this.graduationDate = requestDTO.getGraduationDate();
        this.careerLevel = requestDTO.getCareerLevel();

    }

    public void changeDefaultTrue() {
        this.isDefault = true;
    }


    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Builder
    public Resume(Integer id, String title, String summary, String gender, String careerLevel, String education, String birthdate, String major, String graduationType, String phone, String portfolioUrl, String enrollmentDate, String graduationDate, Boolean isDefault, Timestamp createdAt, User user, SalaryRange salaryRange, JobGroup jobGroup) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.gender = gender;
        this.careerLevel = careerLevel;
        this.education = education;
        this.birthdate = birthdate;
        this.major = major;
        this.graduationType = graduationType;
        this.phone = phone;
        this.portfolioUrl = portfolioUrl;
        this.enrollmentDate = enrollmentDate;
        this.graduationDate = graduationDate;
        this.isDefault = isDefault;
        this.createdAt = createdAt;
        this.user = user;
        this.salaryRange = salaryRange;
        this.jobGroup = jobGroup;

    }

}