package com.metacoding.springrocketdanv1.company;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {
    private final EntityManager em;

    public List<Company> findAll() {

        String q = "SELECT c FROM Company c";

        return em.createQuery(q, Company.class).getResultList();
    }
}
