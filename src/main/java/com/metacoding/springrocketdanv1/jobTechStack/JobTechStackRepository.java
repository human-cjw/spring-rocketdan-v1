package com.metacoding.springrocketdanv1.jobTechStack;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JobTechStackRepository {
    private final EntityManager em;


}
