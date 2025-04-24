package com.metacoding.springrocketdanv1.workField;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WorkFieldRepository {
    private final EntityManager em;

    public String findNameById(Integer id) {
        return em.find(WorkField.class, id).getName();
    }
}