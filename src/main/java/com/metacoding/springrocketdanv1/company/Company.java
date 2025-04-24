package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.user.User;
import com.metacoding.springrocketdanv1.workField.WorkField;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "company_tb")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameKr; // 한글이름
    private String nameEn; // 영어이름
    private String ceo; // 대표명
    private String businessNumber; // 사업자등록번호
    private String email;
    private String phone; // 대표번호
    private String address; // 주소
    @Column(columnDefinition = "text")
    private String introduction; // 회사소개
    private String oneLineIntro; // 회사한줄소개
    private String homepageUrl;
    private String logoImageUrl;
    private String infoImageUrl; // 회사소개이미지
    private String contactManager; // 문의담당자
    private String startDate; // 설립일


    @CreationTimestamp
    private Timestamp createdAt;

    // 유저 fk
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    // 업무분야 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private WorkField workField;
}
