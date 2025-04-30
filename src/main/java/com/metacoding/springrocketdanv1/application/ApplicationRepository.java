package com.metacoding.springrocketdanv1.application;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplicationRepository {
    private final EntityManager em;

    public void save(Application application) {
        em.persist(application);
    }

    public List<Application> findByJobId(Integer jobId, String status) {
        String q = """
                    SELECT a
                    FROM Application a
                    WHERE a.job.id = :jobId
                    AND (:status IS NULL OR a.status = :status)
                """;
        return em.createQuery(q, Application.class)
                .setParameter("jobId", jobId)
                .setParameter("status", status)
                .getResultList();
    }


    public void updateByResumeId(Integer resumeId) {
        em.createQuery("UPDATE Application a SET a.resume = null, a.user = null WHERE a.resume.id = :resumeId")
                .setParameter("resumeId", resumeId)
                .executeUpdate();
    }

    public Application findById(Integer id) {
        return em.find(Application.class, id);
    }
}