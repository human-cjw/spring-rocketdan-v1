package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.company.Company;
import com.metacoding.springrocketdanv1.jobGroup.JobGroup;
import com.metacoding.springrocketdanv1.jobTechStack.JobTechStack;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.workField.WorkField;
import com.metacoding.springrocketdanv1.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "job_tb")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    @Column(columnDefinition = "text")
    private String description;
    private String location;
    private String employmentType;
    private String deadline;
    private String status;
    private String careerLevel;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    // 회사 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    // 연봉 범위 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private SalaryRange salaryRange;

    // 업무 분야 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private WorkField workField;

    @OneToMany(mappedBy = "job", cascade = CascadeType.PERSIST)
    private List<JobTechStack> companyTechStackList = new ArrayList<>();

    // 직무 그룹 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private JobGroup jobGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;  // 작성한 사용자

    @Builder
    public Job(Integer id, String title, String description, String location, String employmentType,
               String deadline, String status, String careerLevel, Timestamp createdAt, Timestamp updatedAt,
               Company company, SalaryRange salaryRange, WorkField workField, JobGroup jobGroup, User user) {
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
        this.user = user;
    }
}