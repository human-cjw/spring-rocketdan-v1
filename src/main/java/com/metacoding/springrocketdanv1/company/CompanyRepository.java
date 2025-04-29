package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.application.Application;
import com.metacoding.springrocketdanv1.job.Job;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {
    private final EntityManager em;

    public Company findById(Integer id) {
        return em.find(Company.class, id); // Lazy loading
    }

    public List<Company> findAll() {
        String q = "SELECT c FROM Company c";
        return em.createQuery(q, Company.class).getResultList();
    }

    public Company save(Company company) {
        em.persist(company);
        return company;
    }

    public Company findByUserId(Integer userId) {
        String q = "SELECT c FROM Company c WHERE c.user.id = :userId";
        return em.createQuery(q, Company.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    public List<Job> findJobsByCompanyId(Integer companyId) {
        String q = "SELECT j FROM Job j JOIN FETCH j.jobGroup WHERE j.company.id = :companyId ORDER BY j.createdAt DESC";
        return em.createQuery(q, Job.class)
                .setParameter("companyId", companyId)
                .getResultList();
    }

    public List<Application> findApplicationsByJobId(Integer jobId) {
        String q = """
                    SELECT a
                    FROM Application a
                    JOIN FETCH a.user u
                    JOIN FETCH a.resume r
                    JOIN FETCH r.jobGroup jg
                    WHERE a.job.id = :jobId
                """;
        return em.createQuery(q, Application.class)
                .setParameter("jobId", jobId)
                .getResultList();
    }

}
