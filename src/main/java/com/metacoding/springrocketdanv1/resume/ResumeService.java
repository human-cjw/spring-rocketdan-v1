package com.metacoding.springrocketdanv1.resume;

import com.metacoding.springrocketdanv1.career.Career;
import com.metacoding.springrocketdanv1.career.CareerRepository;
import com.metacoding.springrocketdanv1.certification.Certification;
import com.metacoding.springrocketdanv1.certification.CertificationRepository;
import com.metacoding.springrocketdanv1.resumeTechStack.ResumeTechStack;
import com.metacoding.springrocketdanv1.resumeTechStack.ResumeTechStackRepository;
import com.metacoding.springrocketdanv1.resumeTechStack.ResumeTechStackResponse;
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

        List<TechStack> techStacks = techStackRepository.findAll();
        List<Integer> resumeTechStackIds = new ArrayList<>();
        for (ResumeTechStack rts : resumeTechStackList) {
            resumeTechStackIds.add(rts.getTechStack().getId());
        }

        List<ResumeTechStackResponse.ResumeTechStackResponseDTO> resumeTechStackResponseDTOS = new ArrayList<>();
        for (TechStack techStack : techStacks) {
            resumeTechStackResponseDTOS.add(new ResumeTechStackResponse.ResumeTechStackResponseDTO(
                    techStack.getId(),
                    techStack.getName(),
                    resumeTechStackIds.contains(techStack.getId())));
        }

        // ResumeTechStack -> TechStack 변환

        List<TechStack> resumeTechStacks = new ArrayList<>();
        for (ResumeTechStack rts : resumeTechStackList) {
            resumeTechStacks.add(rts.getTechStack());
        }

        List<ResumeResponse.GraduationTypeDTO> graduationTypeDTOs = new ArrayList<>();
        graduationTypeDTOs.add(new ResumeResponse.GraduationTypeDTO("졸업", "졸업".equals(resume.getGraduationType())));
        graduationTypeDTOs.add(new ResumeResponse.GraduationTypeDTO("재학", "재학".equals(resume.getGraduationType())));
        graduationTypeDTOs.add(new ResumeResponse.GraduationTypeDTO("휴학", "휴학".equals(resume.getGraduationType())));
        graduationTypeDTOs.add(new ResumeResponse.GraduationTypeDTO("졸업 예정", "졸업 예정".equals(resume.getGraduationType())));

        List<ResumeResponse.CareerLevelTypeDTO> careerLevelTypeDTOS = List.of(
                new ResumeResponse.CareerLevelTypeDTO("신입", "신입".equals(resume.getCareerLevel())),

        )
    }


    public ResumeResponse.DetailDTO 이력서수정하기(Integer resumeId) {
        Resume resume = resumeRepository.findById(resumeId);
        List<Certification> certifications = certificationRepository.findCertificationsByResumeId(resumeId);
        List<ResumeTechStack> resumeTechStackList = resumeTechStackRepository.findAllByResumeId(resumeId);
        List<Career> careers = careerRepository.findCareersByResumeId(resumeId);

        String careerLevel = resume.getCareerLevel();
        boolean isCareerLevelNewbie = "신입".equals(resume.getCareerLevel());
        boolean isCareerLevelOld = "경력".equals(resume.getCareerLevel());

//        System.out.println("✅ isCareerLevelNewbie = " + isCareerLevelNewbie);
//        System.out.println("✅ isCareerLevelOld = " + isCareerLevelOld);

        String gender = resume.getGender();
        boolean isFemale = "여".equals(gender);
        boolean isMale = "남".equals(gender);

//        System.out.println(gender);
//        System.out.println("isFemale = " + isFemale);
//        System.out.println("isMale = " + isMale);

        List<TechStack> techStacks = techStackRepository.findAll();

        List<Integer> resumeTechStackIds = new ArrayList<>();
        for (ResumeTechStack rts : resumeTechStackList) {
            resumeTechStackIds.add(rts.getTechStack().getId());
        }

        List<ResumeTechStackResponse.ResumeTechStackResponseDTO> resumeTechStackResponseDTOS = new ArrayList<>();
        for (TechStack techStack : techStacks) {
            resumeTechStackResponseDTOS.add(new ResumeTechStackResponse.ResumeTechStackResponseDTO(techStack.getId(),
                    techStack.getName(), resumeTechStackIds.contains(techStack.getId())));
        }

        // ResumeTechStack -> TechStack 변환

        List<TechStack> resumeTechStacks = new ArrayList<>();
        for (ResumeTechStack rts : resumeTechStackList) {
            resumeTechStacks.add(rts.getTechStack());
        }

//        System.out.println("유저가 가진 기술" + resumeTechStackIds);
//        System.out.println("모든 기술" + techStacks);

        List<ResumeResponse.GraduationTypeDTO> graduationTypeDTOs = new ArrayList<>();
        graduationTypeDTOs.add(new ResumeResponse.GraduationTypeDTO("졸업", "졸업".equals(resume.getGraduationType())));
        graduationTypeDTOs.add(new ResumeResponse.GraduationTypeDTO("재학", "재학".equals(resume.getGraduationType())));
        graduationTypeDTOs.add(new ResumeResponse.GraduationTypeDTO("휴학", "휴학".equals(resume.getGraduationType())));
        graduationTypeDTOs.add(new ResumeResponse.GraduationTypeDTO("졸업 예정", "졸업 예정".equals(resume.getGraduationType())));

        ResumeResponse.DetailDTO detailDTO = new ResumeResponse.DetailDTO(resume, certifications, resumeTechStacks,
                resume.getUser().getEmail(), resume.getUser().getUsername(), careers,
                isCareerLevelNewbie, isCareerLevelOld, isFemale, isMale, resumeTechStackResponseDTOS, graduationTypeDTOs);


        return detailDTO;
    }

    @Transactional
    public void 이력서수정완료(Integer resumeId, ResumeRequest.UpdateDTO requestDTO) {
        Resume resume = resumeRepository.findById(resumeId);

        // 1. 기본 Resume 수정
        resume.update(requestDTO);

        // 2. 기존 Career, Certification, ResumeTechStack 삭제
        careerRepository.deleteByResumeId(resumeId);
        certificationRepository.deleteByResumeId(resumeId);
        resumeTechStackRepository.deleteByResumeId(resumeId);

        // 3. 깊은 복사해서 새 Career 리스트 만들기
        List<Career> careers = new ArrayList<>();
        if (requestDTO.getCareers() != null) {
            for (ResumeRequest.UpdateDTO.CareerDTO careerDTO : requestDTO.getCareers()) {
                Career career = Career.builder()
                        .companyName(careerDTO.getCompanyName())
                        .startDate(careerDTO.getStartDate())
                        .endDate(careerDTO.getEndDate())
                        .resume(resume)
                        .build();
                careers.add(career);
            }
        }

        // 4. 깊은 복사해서 새 Certification 리스트 만들기
        List<Certification> certifications = new ArrayList<>();
        if (requestDTO.getCertifications() != null) {
            for (ResumeRequest.UpdateDTO.CertificationDTO certificationDTO : requestDTO.getCertifications()) {
                Certification certification = Certification.builder()
                        .name(certificationDTO.getName())
                        .issuer(certificationDTO.getIssuer())
                        .issuedDate(certificationDTO.getIssuedDate())
                        .resume(resume)
                        .build();
                certifications.add(certification);
            }
        }
        // 5. 깊은 복사해서 새 ResumeTechStack 리스트 만들기
        List<ResumeTechStack> resumeTechStacks = new ArrayList<>();
        if (requestDTO.getResumeTechStacks() != null) {
            for (ResumeRequest.UpdateDTO.ResumeTechStackDTO resumeTechStackDTO : requestDTO.getResumeTechStacks()) {
                TechStack techStack = techStackRepository.findById(resumeTechStackDTO.getTechStackId()); // id로 찾기
                ResumeTechStack resumeTechStack = ResumeTechStack.builder()
                        .techStack(techStack)
                        .resume(resume)
                        .build();
                resumeTechStacks.add(resumeTechStack);
            }
        }

        // 6. 각각 저장하기
        for (Career career : careers) {
            careerRepository.save(career);
        }
        for (Certification certification : certifications) {
            certificationRepository.save(certification);
        }
        for (ResumeTechStack resumeTechStack : resumeTechStacks) {
            resumeTechStackRepository.save(resumeTechStack);
        }
    }

    @Transactional
    public void 기본이력서설정(Integer resumeId) {
        // 1. 선택한 이력서 불러오기
        Resume selectedResume = resumeRepository.findById(resumeId);
        Integer userId = selectedResume.getUser().getId();

        // 2. 해당 유저의 모든 이력서를 가져오기
        List<Resume> resumeList = resumeRepository.findAllByUserId(userId);

        // 3. 모두 isDefault = false로 만들기
        for (Resume resume : resumeList) {
            resume.setIsDefault(false); // isDefault = false
        }

        // 4. 선택한 이력서만 isDefault = true
        selectedResume.changeDefaultTrue();
    }
}


