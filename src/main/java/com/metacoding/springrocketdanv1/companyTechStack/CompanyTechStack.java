package com.metacoding.springrocketdanv1.companyTechStack;

import com.metacoding.springrocketdanv1.company.Company;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "company_tech_stack_tb")
public class CompanyTechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 기업 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    // 기술스택 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private TechStack techStack;

    public CompanyTechStack(Company company, TechStack techStack) {
        this.company = company;
        this.techStack = techStack;
    }
}