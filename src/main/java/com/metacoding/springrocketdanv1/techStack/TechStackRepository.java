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
        return em.createQuery("SELECT t FROM TechStack t", TechStack.class)
                .getResultList();
    }

    public TechStack findById(Integer techStackId) {
        return em.find(TechStack.class, techStackId);
    }
}