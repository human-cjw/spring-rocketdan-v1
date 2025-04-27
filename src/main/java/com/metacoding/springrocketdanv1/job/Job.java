package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.company.Company;
import com.metacoding.springrocketdanv1.jobBookmark.JobBookmark;
import com.metacoding.springrocketdanv1.jobGroup.JobGroup;
import com.metacoding.springrocketdanv1.jobTechStack.JobTechStack;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.workField.WorkField;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "job", cascade = CascadeType.PERSIST)
    private List<JobTechStack> jobTechStackList = new ArrayList<>();

    @Builder
    public Job(Integer id, String title, String description, String location, String employmentType, String deadline, String status, String careerLevel, Timestamp createdAt, Timestamp updatedAt, Company company, SalaryRange salaryRange, WorkField workField, JobGroup jobGroup) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.employmentType = employmentType;
        this.deadline = deadline;
        this.status = status;
        this.careerLevel = careerLevel;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.company = company;
        this.salaryRange = salaryRange;
        this.workField = workField;
        this.jobGroup = jobGroup;
    }
}
