package com.metacoding.springrocketdanv1.certification;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CertificationRepository {
    private final EntityManager em;

    public List<Certification> findCertificationsByResumeId(Integer resumeId) {
        String q = "SELECT c FROM Certification c WHERE c.resume.id = :resumeId";
        return em.createQuery(q, Certification.class)
                .setParameter("resumeId", resumeId)
                .getResultList();
    }

    public void deleteByResumeId(Integer resumeId) {
        em.createQuery("DELETE FROM Certification c WHERE c.resume.id = :resumeId")
                .setParameter("resumeId", resumeId)
                .executeUpdate();
    }

    public void save(Certification certification) {
        em.persist(certification);
    }

    public Certification findByResumeId(Integer resumeId) {
        String q = "SELECT c FROM Certification c WHERE c.resume.id = :resumeId";
        try {
            return em.createQuery(q, Certification.class)
                    .setParameter("resumeId", resumeId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
