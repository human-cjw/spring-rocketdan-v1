package com.metacoding.springrocketdanv1.resume;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResumeRepository {
    private final EntityManager em;

    public Resume findById(Integer id) {
        return em.find(Resume.class, id);
    }

    public List<Resume> findAllByUserId(Integer userId) {
        String q = "SELECT res FROM Resume res WHERE res.user.id = :userId";
        return em.createQuery(q, Resume.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
