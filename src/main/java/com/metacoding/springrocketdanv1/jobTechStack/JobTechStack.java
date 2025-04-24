package com.metacoding.springrocketdanv1.jobTechStack;

import com.metacoding.springrocketdanv1.job.Job;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Entity
@Table(name = "job_tech_stack_tb")
public class JobTechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 공고 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private Job job;

    // 기술스택 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private TechStack techStack;
}
