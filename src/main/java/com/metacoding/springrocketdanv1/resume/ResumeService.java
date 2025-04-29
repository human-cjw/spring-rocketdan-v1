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
import com.metacoding.springrocketdanv1.user.User;
import com.metacoding.springrocketdanv1.user.UserRepository;
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
    private final UserRepository userRepository;

    public ResumeResponse.DetailDTO 이력서상세보기(Integer resumeId, Integer userId) {
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

        List<ResumeResponse.GraduationTypeDTO> graduationTypeDTOs = List.of(
                new ResumeResponse.GraduationTypeDTO("졸업", "졸업".equals(resume.getGraduationType())),
                new ResumeResponse.GraduationTypeDTO("재학", "재학".equals(resume.getGraduationType())),
                new ResumeResponse.GraduationTypeDTO("휴학", "휴학".equals(resume.getGraduationType())),
                new ResumeResponse.GraduationTypeDTO("졸업 예정", "졸업 예정".equals(resume.getGraduationType()))
        );

        List<ResumeResponse.CareerLevelTypeDTO> careerLevelTypeDTOs = List.of(
                new ResumeResponse.CareerLevelTypeDTO("신입", "신입".equals(resume.getCareerLevel())),
                new ResumeResponse.CareerLevelTypeDTO("경력", "경력".equals(resume.getCareerLevel()))
        );

        List<ResumeResponse.GenderTypeDTO> genderTypeDTOs = List.of(
                new ResumeResponse.GenderTypeDTO("남", "남".equals(resume.getGender())),
                new ResumeResponse.GenderTypeDTO("여", "여".equals(resume.getGender()))
        );

        return new ResumeResponse.DetailDTO(
                resume,
                certifications,
                resumeTechStacks,
                resume.getUser().getEmail(),
                resume.getUser().getUsername(),
                careers,
                resumeTechStackResponseDTOS,
                graduationTypeDTOs,
                careerLevelTypeDTOs,
                genderTypeDTOs,
                userId
        );
    }


    public ResumeResponse.UpdateDTO 이력서수정보기(Integer resumeId) {
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

        List<ResumeResponse.GraduationTypeDTO> graduationTypeDTOs = List.of(
                new ResumeResponse.GraduationTypeDTO("졸업", "졸업".equals(resume.getGraduationType())),
                new ResumeResponse.GraduationTypeDTO("재학", "재학".equals(resume.getGraduationType())),
                new ResumeResponse.GraduationTypeDTO("휴학", "휴학".equals(resume.getGraduationType())),
                new ResumeResponse.GraduationTypeDTO("졸업 예정", "졸업 예정".equals(resume.getGraduationType()))
        );

        List<ResumeResponse.CareerLevelTypeDTO> careerLevelTypeDTOs = List.of(
                new ResumeResponse.CareerLevelTypeDTO("신입", "신입".equals(resume.getCareerLevel())),
                new ResumeResponse.CareerLevelTypeDTO("경력", "경력".equals(resume.getCareerLevel()))
        );

        List<ResumeResponse.GenderTypeDTO> genderTypeDTOs = List.of(
                new ResumeResponse.GenderTypeDTO("남", "남".equals(resume.getGender())),
                new ResumeResponse.GenderTypeDTO("여", "여".equals(resume.getGender()))
        );

        return new ResumeResponse.UpdateDTO(
                resume,
                certifications,
                resumeTechStacks,
                resume.getUser().getEmail(),
                resume.getUser().getUsername(),
                careers,
                resumeTechStackResponseDTOS,
                graduationTypeDTOs,
                careerLevelTypeDTOs,
                genderTypeDTOs
        );
    }


    @Transactional
    public void 이력서수정하기(Integer resumeId, ResumeRequest.UpdateDTO requestDTO) {
        Resume resume = resumeRepository.findById(resumeId);

        // 1. 기본 Resume 수정
        resume.update(requestDTO);

        // 나머지 다른 이력서 전부 가져와 isDefault = false로 변경

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

        // 7. 깊은 복사해서 User 이메일만 수정
        if (requestDTO.getUsers() != null) {
            for (ResumeRequest.UpdateDTO.UserDTO userDTO : requestDTO.getUsers()) {
                User user = resume.getUser();
                User userEntity = User.builder()
                        .email(userDTO.getEmail()) // email만 변경
                        .build();

                userRepository.save(userEntity);
            }
        }
    }

    public ResumeResponse.ResumeListDTO 이력서목록보기(Integer userId, boolean isDefault) {
        List<Resume> resumes = resumeRepository.findAllByUserId(userId, isDefault);

        return new ResumeResponse.ResumeListDTO(resumes, isDefault);
    }
}


