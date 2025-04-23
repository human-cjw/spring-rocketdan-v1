package com.metacoding.springrocketdanv1.company;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyRepository {

    @PersistenceContext
    private EntityManager em;

    public Company findById(Integer id) {
        return em.find(Company.class, id); // Lazy loading
    }
}
