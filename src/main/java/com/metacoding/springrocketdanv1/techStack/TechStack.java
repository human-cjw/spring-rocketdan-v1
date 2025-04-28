package com.metacoding.springrocketdanv1.techStack;

import com.metacoding.springrocketdanv1.jobTechStack.JobTechStack;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tech_stack_tb")
public class TechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "techStack", cascade = CascadeType.PERSIST)
    private List<JobTechStack> jobTechStackList = new ArrayList<>();

    public TechStack(String name) {
        this.name = name;
    }
}