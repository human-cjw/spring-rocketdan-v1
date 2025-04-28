package com.metacoding.springrocketdanv1.techStack;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TechStackRepository {
    private final EntityManager em;

    public void save(TechStack techStack) {
        em.persist(techStack);
    }

    public List<TechStack> findAll() {
        String jpql = "SELECT t FROM TechStack t"; // JPQL 쿼리
        Query query = em.createQuery(jpql);
        return query.getResultList(); // 결과를 List로 반환

    }

    public TechStack findByName(String name) {
        String sql = "SELECT t FROM TechStack t WHERE t.name = :name";
        List<TechStack> result = em.createQuery(sql, TechStack.class)
                .setParameter("name", name)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}