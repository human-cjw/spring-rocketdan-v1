package com.metacoding.springrocketdanv1.company;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepository {


    @PersistenceContext
    private EntityManager em;

    public Company findById(Integer id) {
        return em.find(Company.class, id); // Lazy loading
    }

    public List<Company> findAll() {

        String q = "SELECT c FROM Company c";

        return em.createQuery(q, Company.class).getResultList();
    }
}
