package com.metacoding.springrocketdanv1.jobTechStack;

import com.metacoding.springrocketdanv1.job.Job;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class JobTechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_stack_id")
    private TechStack techStack;

    public JobTechStack(Job job, TechStack techStack) {
        this.job = job;
        this.techStack = techStack;
    }
}