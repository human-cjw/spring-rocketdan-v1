package com.metacoding.springrocketdanv1.job;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobRepository {
    private final EntityManager em;

    public List<Job> findAll() {
        String sql = "select j from Job j";
        TypedQuery<Job> query = em.createQuery(sql, Job.class);
        return query.getResultList();
    }
}
