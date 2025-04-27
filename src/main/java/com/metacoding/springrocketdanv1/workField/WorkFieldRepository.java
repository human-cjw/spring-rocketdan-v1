package com.metacoding.springrocketdanv1.workField;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WorkFieldRepository {
    private final EntityManager em;

    public WorkField findByName(String name) {
        String q = "SELECT w FROM WorkField w WHERE w.name = :name";
        List<WorkField> result = em.createQuery(q, WorkField.class)
                .setParameter("name", name)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public WorkField save(WorkField workField) {
        em.persist(workField);
        return workField;
    }
}