package com.metacoding.springrocketdanv1.resumeTechStack;

import com.metacoding.springrocketdanv1.techStack.TechStack;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResumeTechStackRepository {
    private final EntityManager em;

    public List<TechStack> findAllByResumeId(Integer resumeId) {
        String q = "SELECT r.techStack FROM ResumeTechStack r WHERE r.resume.id = :resumeId";
        return em.createQuery(q, TechStack.class)
                .setParameter("resumeId", resumeId)
                .getResultList();
    }

}