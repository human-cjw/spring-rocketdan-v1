package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.board.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobRepository {
    private final EntityManager em;

    public List<Board> findAll(Integer userId) {
        String sql = "select b from Board b where b.userId = :userId";
        TypedQuery<Board> query = em.createQuery(sql, Board.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
