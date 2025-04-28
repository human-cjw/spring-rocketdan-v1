package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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


    public Company findByUser(User user) {
        try {
            return em.createQuery(
                            "SELECT c FROM Company c WHERE c.user = :user", Company.class)
                    .setParameter("user", user)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Company save(Company company) {
        em.persist(company);
        return company;

    }
}
