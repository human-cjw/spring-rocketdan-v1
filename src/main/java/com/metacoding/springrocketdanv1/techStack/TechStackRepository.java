package com.metacoding.springrocketdanv1.techStack;

import com.metacoding.springrocketdanv1.workField.WorkField;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TechStackRepository {
    private final EntityManager em;

}