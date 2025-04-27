package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.job.Job;
import com.metacoding.springrocketdanv1.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;  // 해당 기업을 등록한 사용자

    public Company(String name, User user) {
        this.name = name;
        this.user = user;
    }
}