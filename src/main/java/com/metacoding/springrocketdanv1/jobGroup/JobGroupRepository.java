package com.metacoding.springrocketdanv1.jobGroup;

import com.metacoding.springrocketdanv1.workField.WorkField;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobGroupRepository {
    private final EntityManager em;

    public JobGroup findByName(String name) {
        String q = "SELECT w FROM WorkField w WHERE w.name = :name";
        List<JobGroup> result = em.createQuery(q, JobGroup.class)
                .setParameter("name", name)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public JobGroup save(JobGroup jobGroup) {
        em.persist(jobGroup);
        return jobGroup;
    }
}
