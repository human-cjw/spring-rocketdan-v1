package com.metacoding.springrocketdanv1.workField;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "work_field_tb")
public class WorkField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public WorkField(String name) {
        this.name = name;
    }

    @Builder
    public WorkField(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}