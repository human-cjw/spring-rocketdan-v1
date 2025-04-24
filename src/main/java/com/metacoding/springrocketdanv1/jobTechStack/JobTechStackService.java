package com.metacoding.springrocketdanv1.jobTechStack;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobTechStackService {
    private final JobTechStackRepository jobTechStackRepository;
    private final EntityManager em;


}
