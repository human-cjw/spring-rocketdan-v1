package com.metacoding.springrocketdanv1.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public ResumeResponse.DetailDTO resumeDetail(Integer id, Integer userId) {
        Resume resume = resumeRepository.findByIdWithCareer(id);

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
        dto.setEnrollmentDate(resume.getEnrollmentDate());
        dto.setGraduationDate(resume.getGraduationDate());
        dto.setIsDefault(resume.getIsDefault().toString());

//        // Lazy로딩 활용
//        dto.setUserId(resume.getUser().getId());
//        dto.setSalaryRangeId(resume.getSalaryRange().getTitle());
//        dto.setJobGroupId(resume.getJobGroup().getTitle());
//
//        // Career 매핑
//        List<ResumeResponse.DetailDTO.CareerDTO> careerDTOs = resume.getCareers().stream().map(c -> {
//            ResumeResponse.DetailDTO.CareerDTO careerDTO = new ResumeResponse.DetailDTO.CareerDTO();
//            careerDTO.setCompanyName(c.getCompanyName());
//            careerDTO.setCareerId(c.getCareerId());
//            careerDTO.setStartedAt(c.getStartedAt());
//            careerDTO.setEndedAt(c.getEndedAt());
//            return careerDTO;
//        }).toList();
//
//        dto.setCareers(careerDTO);

        return dto;
    }


//    public ResumeResponse.DetailDTO 이력서상세보기(Integer id, Integer userId) {
//        Resume resume = resumeRepository.findById(id);
//
//        ResumeResponse.DetailDTO dto = new ResumeResponse.DetailDTO();
//        dto.setId(resume.getId());
//        dto.setTitle(resume.getTitle());
//        dto.setGender(resume.getGender());
//        dto.setSummary(resume.getSummary());
//        dto.setCareerLevel(resume.getCareerLevel());
//        dto.setEducation(resume.getEducation());
//        dto.setBirthdate(resume.getBirthdate());
//        dto.setPortfolioUrl(resume.getPortfolioUrl());
//        dto.setGraduationType(resume.getGraduationType());
//        dto.setMajor(resume.getMajor());
//        dto.setPhone(resume.getPhone());
//
//        dto.setCreatedAt(resume.getCreatedAt());
//
//        return dto;
//    }
}


