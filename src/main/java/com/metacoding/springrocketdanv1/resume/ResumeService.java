package com.metacoding.springrocketdanv1.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public ResumeResponse.DetailDTO 이력서상세보기(Integer id, Integer userId) {
        Resume resume = resumeRepository.findById(userId);

        ResumeResponse.DetailDTO dto = new ResumeResponse.DetailDTO();
        dto.setId(resume.getId());
        dto.setTitle(resume.getTitle());
        dto.setGender(resume.getGender());

        dto.setSummary(resume.getSummary());
        dto.setCareerLevel(resume.getCareerLevel());
        dto.setEducation(resume.getEducation());
        dto.setBirthdate(resume.getBirthdate());
        dto.setPortfolioUrl(resume.getPortfolioUrl());
        dto.setGraduationType(resume.getGraduationType());
        dto.setMajor(resume.getMajor());
        dto.setPhone(resume.getPhone());

        dto.setCreatedAt(resume.getCreatedAt());

        return dto;
    }
}


