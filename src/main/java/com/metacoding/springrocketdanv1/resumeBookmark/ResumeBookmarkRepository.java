package com.metacoding.springrocketdanv1.resumeBookmark;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ResumeBookmarkRepository {
    private final EntityManager em;

    public void deleteByResumeId(Integer resumeId) {
        em.createQuery("DELETE FROM ResumeBookmark rb WHERE rb.resume.id = :resumeId")
                .setParameter("resumeId", resumeId)
                .executeUpdate();
    }
}