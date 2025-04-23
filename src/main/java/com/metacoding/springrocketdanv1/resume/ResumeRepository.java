package com.metacoding.springrocketdanv1.resume;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ResumeRepository {
    private final EntityManager em;


    public Resume findByUserId(int id) {
        Query query = em.createQuery("select r from Resume r where r.id = :id", Resume.class);
        query.setParameter("id", id);
        return (Resume) query.getSingleResult();
    }
}
