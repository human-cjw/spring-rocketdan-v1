package com.metacoding.springrocketdanv1.salaryRange;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "salary_range")
public class SalaryRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public SalaryRange(String name) {
        this.name = name;
    }
}