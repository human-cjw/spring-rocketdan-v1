package com.metacoding.springrocketdanv1.resume;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ResumeRepository {
    private final EntityManager em;

    public Resume findById(Integer id) {
        return em.find(Resume.class, id);
    }
}
