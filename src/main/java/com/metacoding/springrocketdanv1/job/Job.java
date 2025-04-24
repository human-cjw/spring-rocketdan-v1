package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.company.Company;
import com.metacoding.springrocketdanv1.jobGroup.JobGroup;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.workField.WorkField;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "job_tb")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    @Column(columnDefinition = "text")
    private String description; // 공고 설명
    private String location; // 근무지
    private String employmentType; // 정규직, 계약직, 인턴, 프리
    private String deadline; // 공고 마감일
    private String status; // 공고 상태. open, closed
    private String careerLevel; // 0년차, 1년차

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    // 기업 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    // 연봉범위 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private SalaryRange salaryRange;

    // 업무분야 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private WorkField workField;

    // 직무 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private JobGroup jobGroup;
}
