package com.metacoding.springrocketdanv1.techStack;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TechStackRepository {
    private final EntityManager em;

    public List<TechStack> findAll() {
        return em.createQuery("SELECT j FROM TechStack j", TechStack.class)
                .getResultList();
    }
}