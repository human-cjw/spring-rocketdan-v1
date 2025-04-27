package com.metacoding.springrocketdanv1.salaryRange;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SalaryRangeRepository {
    private final EntityManager em;

    public List<SalaryRange> findAll() {
        String jpql = "SELECT s FROM SalaryRange s"; // JPQL 쿼리
        Query query = em.createQuery(jpql);
        return query.getResultList(); // 결과를 List로 반환
    }

    public SalaryRange findById(Integer id) {
        return em.find(SalaryRange.class, id);
    }

}