package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.techStack.TechStackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final TechStackRepository techStackRepository;

    public List<TechStack> findAll() {
        return techStackRepository.findAll();
    }

}
