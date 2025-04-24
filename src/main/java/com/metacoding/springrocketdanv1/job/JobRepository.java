package com.metacoding.springrocketdanv1.job;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JobRepository {
    private final EntityManager em;
    public void save(Job job) {
        em.persist(job);
    }

    public Job findById(Long id) {
        return em.find(Job.class, id);
    }

}
