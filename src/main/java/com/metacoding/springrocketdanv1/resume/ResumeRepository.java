package com.metacoding.springrocketdanv1.resume;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ResumeRepository {
    private final EntityManager em;

    public Resume findById(Integer id) {
        return em.find(Resume.class, id);
    }

    public Resume save(Resume resume) {
        if (resume.getId() == null) {
            em.persist(resume); // 새 객체 저장
            return resume;
        } else {
            return em.merge(resume); // 기존 객체 수정
        }
    }
}
