package com.metacoding.springrocketdanv1.resume;

import com.metacoding.springrocketdanv1.career.Career;
import com.metacoding.springrocketdanv1.career.CareerRepository;
import com.metacoding.springrocketdanv1.certification.Certification;
import com.metacoding.springrocketdanv1.certification.CertificationRepository;
import com.metacoding.springrocketdanv1.resumeTechStack.ResumeTechStackRepository;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final CertificationRepository certificationRepository;
    private final ResumeTechStackRepository resumeTechStackRepository;
    private final CareerRepository careerRepository;

    public ResumeResponse.DetailDTO 이력서상세보기(Integer resumeId, Integer userId) {
        Resume resume = resumeRepository.findById(resumeId);
        List<Certification> certifications = certificationRepository.findCertificationsByResumeId(resumeId);
        List<TechStack> resumeTechStacks = resumeTechStackRepository.findAllByResumeId(resumeId);
        List<Career> careers = careerRepository.findCareersByResumeId(resumeId);

        ResumeResponse.DetailDTO detailDTO = new ResumeResponse.DetailDTO(resume, certifications,
                resumeTechStacks, resume.getUser().getEmail(), resume.getUser().getUsername(), careers, userId);
        
        return detailDTO;
    }

    public ResumeResponse.ResumeListDTO 이력서목록보기(Integer userId, boolean isDefault) {
        List<Resume> resumes = resumeRepository.findAllByUserId(userId, isDefault);

        return new ResumeResponse.ResumeListDTO(resumes, isDefault);
    }
}


