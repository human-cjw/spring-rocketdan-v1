package com.metacoding.springrocketdanv1.resume;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResumeRepository {
    private final EntityManager em;

    public Resume findById(Integer id) {
        return em.find(Resume.class, id);
    }

    public List<Resume> findAll() {
        String sql = "select r from Resume r";
        Query query = em.createQuery(sql, Resume.class);
        return query.getResultList();
    }
}
