package com.metacoding.springrocketdanv1.companyTechStack;

import com.metacoding.springrocketdanv1.techStack.TechStack;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyTechStackRepository {
    private final EntityManager em;

    public List<TechStack> findByCompanyId(Integer companyId) {
        String q = "SELECT cts.techStack FROM CompanyTechStack cts WHERE cts.company.id = :companyId";
        return em.createQuery(q, TechStack.class)
                .setParameter("companyId", companyId)
                .getResultList();
    }

    public void save(CompanyTechStack cts) {
        em.persist(cts);
    }

    public void deleteByCompanyId(Integer companyId) {
        String q = "DELETE FROM CompanyTechStack cts WHERE cts.company.id = :companyId";
        em.createQuery(q)
                .setParameter("companyId", companyId)
                .executeUpdate();
    }

}
