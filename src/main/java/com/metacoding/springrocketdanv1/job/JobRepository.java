package com.metacoding.springrocketdanv1.job;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JobRepository {
    @PersistenceContext
    private final EntityManager em;

    public Job save(Job job) {
        em.persist(job);
        return job;
    }

    public Job findById(Long id) {
        return em.find(Job.class, id);
    }

}
