package com.metacoding.springrocketdanv1.workField;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WorkFieldRepository {
    private final EntityManager em;

    public WorkField findByName(String name) {
        String q = "SELECT w FROM WorkField w WHERE w.name = :name";
        return em.createQuery(q, WorkField.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public String findNameById(Integer id) {
        return em.find(WorkField.class, id).getName();

    }

}