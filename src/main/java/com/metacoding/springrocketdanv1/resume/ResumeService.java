package com.metacoding.springrocketdanv1.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public Resume 이력서상세보기(Integer id, Integer userId) {
        return resumeRepository.findByUserId(id);
    }
}
