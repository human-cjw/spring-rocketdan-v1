package com.metacoding.springrocketdanv1.career;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CareerRepository {
    private final EntityManager em;

    public List<Career> findCareersByResumeId(Integer resumeId) {
        String q = "SELECT car FROM Career car WHERE car.resume.id = :resumeId";
        return em.createQuery(q, Career.class)
                .setParameter("resumeId", resumeId)
                .getResultList();
    }
}
