package com.metacoding.springrocketdanv1.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "board_tb")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String content;
    private String password;

    @CreationTimestamp
    private Timestamp createdAt;
/*
    // 유저 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
*/
}
