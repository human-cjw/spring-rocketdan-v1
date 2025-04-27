package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {
    private final EntityManager em;

    public void save(Company company) {
        em.persist(company);
    }

    public Company findById(Integer id) {
        return em.find(Company.class, id);
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
}
