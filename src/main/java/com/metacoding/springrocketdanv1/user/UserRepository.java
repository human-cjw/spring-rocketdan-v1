package com.metacoding.springrocketdanv1.user;

import com.metacoding.springrocketdanv1.resume.Resume;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public User findById(Integer id) {
        Query query = em.createQuery(
                "select u  from User u " +
                        "where u.id = :id", User.class);
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }
}