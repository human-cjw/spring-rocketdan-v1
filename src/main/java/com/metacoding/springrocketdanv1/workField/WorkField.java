package com.metacoding.springrocketdanv1.workField;

import jakarta.persistence.*;
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

    private String name; // 업무분야. it, 금융, 판매

    public WorkField(String name) {
        this.name = name;
    }
}
