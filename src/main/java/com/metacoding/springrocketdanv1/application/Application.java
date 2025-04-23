package com.metacoding.springrocketdanv1.application;

import com.metacoding.springrocketdanv1.job.Job;
import com.metacoding.springrocketdanv1.resume.Resume;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "application_tb", uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_company", // 제약조건 이름 지정
                columnNames = {"user_id", "company_id"} // 복합 유니크 제약조건을 걸 컬럼명
        )
})
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status; // 지원 상태. 접수, 검토, 합격, 탈락

    // 사용자 ID (이력서로부터 유래)
    private Integer userId;

    // 회사 ID (공고로부터 유래)
    private Integer companyId;

    @CreationTimestamp
    private Timestamp createdAt;

    // 이력서 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    // 공고 FK
    @ManyToOne(fetch = FetchType.LAZY)
    private Job job;
}
