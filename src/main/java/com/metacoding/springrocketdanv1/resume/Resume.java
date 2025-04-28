package com.metacoding.springrocketdanv1.resume;


import com.metacoding.springrocketdanv1.jobGroup.JobGroup;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.user.User;
import jakarta.persistence.*;
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
}
