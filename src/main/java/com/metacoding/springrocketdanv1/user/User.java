package com.metacoding.springrocketdanv1.user;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String fileUrl; // 프로필 이미지
    @Column(nullable = false)
    private String userType; // 일반유저, 기업유저 값 : user or company

    // 기업 fk
    private Integer companyId; // userType이 user 면 null

    @CreationTimestamp
    private Timestamp createdAt;


    @Builder
    public User(String username, String password, String email, String userType, Timestamp createdAt) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.createdAt = createdAt;
    }
}
