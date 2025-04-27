package com.metacoding.springrocketdanv1.resume;

import com.metacoding.springrocketdanv1.career.Career;
import com.metacoding.springrocketdanv1.career.CareerRepository;
import com.metacoding.springrocketdanv1.certification.Certification;
import com.metacoding.springrocketdanv1.certification.CertificationRepository;
import com.metacoding.springrocketdanv1.resumeTechStack.ResumeTechStack;
import com.metacoding.springrocketdanv1.resumeTechStack.ResumeTechStackRepository;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.techStack.TechStackRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final CertificationRepository certificationRepository;
    private final ResumeTechStackRepository resumeTechStackRepository;
    private final CareerRepository careerRepository;
    private final TechStackRepository techStackRepository;

    public ResumeResponse.DetailDTO 이력서상세보기(Integer resumeId) {
        Resume resume = resumeRepository.findById(resumeId);
        List<Certification> certifications = certificationRepository.findCertificationsByResumeId(resumeId);
        List<ResumeTechStack> resumeTechStackList = resumeTechStackRepository.findAllByResumeId(resumeId);
        List<Career> careers = careerRepository.findCareersByResumeId(resumeId);

        String careerLevel = resume.getCareerLevel();
        boolean isCareerLevelNewbie = "신입".equals(careerLevel);
        boolean isCareerLevelOld = "경력".equals(careerLevel);

        String gender = resume.getGender();
        boolean isFemale = "여".equals(gender);
        boolean isMale = "남".equals(gender);

        List<TechStack> techStacks = techStackRepository.findAll();

        List<Integer> resumeTechStackIds = new ArrayList<>();
        for (ResumeTechStack rts : resumeTechStackList) {
            resumeTechStackIds.add(rts.getTechStack().getId());
        }

        for (TechStack techStack : techStacks) {
            if (resumeTechStackIds.contains(techStack.getId())) {
                techStack.setIsChecked(true);
            } else {
                techStack.setIsChecked(false);
            }
        }

        // ResumeTechStack -> TechStack 변환

        List<TechStack> resumeTechStacks = new ArrayList<>();
        for (ResumeTechStack rts : resumeTechStackList) {
            resumeTechStacks.add(rts.getTechStack());
        }

        ResumeResponse.DetailDTO detailDTO = new ResumeResponse.DetailDTO(resume, certifications,
                resumeTechStacks, resume.getUser().getEmail(), resume.getUser().getUsername(), careers, isCareerLevelNewbie,
                isCareerLevelOld, isFemale, isMale, techStacks);

        System.out.println(detailDTO.getCertifications());
        System.out.println(detailDTO.getResumeTechStacks());

        return detailDTO;
    }


    public ResumeResponse.DetailDTO 이력서수정하기(Integer resumeId) {
        Resume resume = resumeRepository.findById(resumeId);
        List<Certification> certifications = certificationRepository.findCertificationsByResumeId(resumeId);
        List<ResumeTechStack> resumeTechStackList = resumeTechStackRepository.findAllByResumeId(resumeId);
        List<Career> careers = careerRepository.findCareersByResumeId(resumeId);

        String careerLevel = resume.getCareerLevel();
        boolean isCareerLevelNewbie = "신입".equals(resume.getCareerLevel());
        boolean isCareerLevelOld = "경력".equals(resume.getCareerLevel());

        System.out.println("✅ isCareerLevelNewbie = " + isCareerLevelNewbie);
        System.out.println("✅ isCareerLevelOld = " + isCareerLevelOld);

        String gender = resume.getGender();
        boolean isFemale = "여".equals(gender);
        boolean isMale = "남".equals(gender);
        System.out.println(gender);

        System.out.println("isFemale = " + isFemale);
        System.out.println("isMale = " + isMale);

        List<TechStack> techStacks = techStackRepository.findAll();

        List<Integer> resumeTechStackIds = new ArrayList<>();
        for (ResumeTechStack rts : resumeTechStackList) {
            resumeTechStackIds.add(rts.getTechStack().getId());
        }

        for (TechStack techStack : techStacks) {
            if (resumeTechStackIds.contains(techStack.getId())) {
                techStack.setIsChecked(true);
            } else {
                techStack.setIsChecked(false);
            }
        }

        // ResumeTechStack -> TechStack 변환

        List<TechStack> resumeTechStacks = new ArrayList<>();
        for (ResumeTechStack rts : resumeTechStackList) {
            resumeTechStacks.add(rts.getTechStack());
        }

        System.out.println("유저가 가진 기술" + resumeTechStackIds);
        System.out.println("모든 기술" + techStacks);

        ResumeResponse.DetailDTO detailDTO = new ResumeResponse.DetailDTO(resume, certifications, resumeTechStacks,
                resume.getUser().getEmail(), resume.getUser().getUsername(), careers,
                isCareerLevelNewbie, isCareerLevelOld, isFemale, isMale, techStacks);


        return detailDTO;
    }

    @Transactional
    public void 이력서수정완료(Integer resumeId, ResumeRequest.UpdateDTO requestDTO) {
        Resume resume = resumeRepository.findById(resumeId);

        resume.update(requestDTO);

    }

    @Transactional
    public void 기본이력서설정(Integer resumeId) {
        resumeRepository.updateAllResumeDefaultFalse();

        Resume resume = resumeRepository.findById(resumeId);
        resume.changeDefaultTrue();
    }
}


