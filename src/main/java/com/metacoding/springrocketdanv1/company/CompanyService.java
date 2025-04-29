package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.application.Application;
import com.metacoding.springrocketdanv1.companyTechStack.CompanyTechStack;
import com.metacoding.springrocketdanv1.companyTechStack.CompanyTechStackRepository;
import com.metacoding.springrocketdanv1.job.Job;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.techStack.TechStackRepository;
import com.metacoding.springrocketdanv1.user.User;
import com.metacoding.springrocketdanv1.user.UserResponse;
import com.metacoding.springrocketdanv1.workField.WorkField;
import com.metacoding.springrocketdanv1.workField.WorkFieldRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final WorkFieldRepository workFieldRepository;
    private final CompanyTechStackRepository companyTechStackRepository;
    private final TechStackRepository techStackRepository;

    @PersistenceContext
    private EntityManager em;

    // 기업 상세보기
    @Transactional(readOnly = true)
    public CompanyResponse.CompanyResponseDTO 기업상세(Integer companyId) {
        Company company = companyRepository.findById(companyId);

        List<TechStack> techStacks = companyTechStackRepository.findByCompanyId(companyId);
        List<String> techStackNames = techStacks.stream()
                .map(TechStack::getName)
                .collect(Collectors.toList());

        boolean isOwner = false;

        String workFieldName = workFieldRepository.findNameById(company.getWorkField().getId());
        return new CompanyResponse.CompanyResponseDTO(
                company.getNameKr(),
                company.getNameEn(),
                company.getCeo(),
                company.getBusinessNumber(),
                company.getEmail(),
                company.getPhone(),
                company.getAddress(),
                company.getIntroduction(),
                company.getOneLineIntro(),
                company.getHomepageUrl(),
                company.getLogoImageUrl(),
                company.getInfoImageUrl(),
                company.getContactManager(),
                company.getStartDate(),
                workFieldName,
                techStackNames,
                isOwner
        );
    }

    // 기업 리스트
    @Transactional(readOnly = true)
    public List<Company> 기업리스트() {
        return companyRepository.findAll();
    }

    // 기업 등록
    @Transactional
    public Company 기업등록(CompanyRequest.CompanySaveDTO requestDTO, UserResponse.SessionUserDTO sessionUser) {
        // 산업분야 조회 또는 저장
        WorkField workField = workFieldRepository.findByName(requestDTO.getWorkFieldName());
        if (workField == null) {
            workField = workFieldRepository.save(WorkField.builder().name(requestDTO.getWorkFieldName()).build());
        }

        // 기술 스택 조회
        List<TechStack> techStackList = new ArrayList<>();
        if (requestDTO.getTechStack() != null) {
            for (String name : requestDTO.getTechStack()) {
                TechStack ts = techStackRepository.findByName(name);
                if (ts != null) {
                    techStackList.add(ts);
                }
            }
        }

        // 회사 + 연관 기술 스택 cascade 저장
        Company company = requestDTO.toEntity(sessionUser, workField, techStackList);
        companyRepository.save(company);

        // 세션에서 넘어온 User가 아니라, DB에서 영속 객체를 다시 가져옴
        User user = em.find(User.class, sessionUser.getId());

        try {
            // userType, companyId 수정
            Field userTypeField = User.class.getDeclaredField("userType");
            userTypeField.setAccessible(true);
            userTypeField.set(user, "company");

            Field companyIdField = User.class.getDeclaredField("companyId");
            companyIdField.setAccessible(true);
            companyIdField.set(user, company.getId());

        } catch (Exception e) {
            throw new RuntimeException("User 업데이트 실패", e);
        }

        return company;
    }

    // 내 기업 조회 (업데이트 폼)
    @Transactional(readOnly = true)
    public CompanyResponse.UpdateFormDTO 내기업조회(Integer userId) {
        Company company = companyRepository.findByUserId(userId);

        // 기술 스택 전체 조회 + 선택 여부 매핑
        List<TechStack> allTechStacks = techStackRepository.findAll();
        List<TechStack> selectedTechStacks = companyTechStackRepository.findByCompanyId(company.getId());

        List<String> selectedNames = selectedTechStacks.stream()
                .map(TechStack::getName)
                .collect(Collectors.toList());

        List<CompanyResponse.TechStackDTO> techStackDTOs = new ArrayList<>();
        for (TechStack ts : allTechStacks) {
            boolean isChecked = selectedNames.contains(ts.getName());
            techStackDTOs.add(new CompanyResponse.TechStackDTO(ts.getName(), isChecked));
        }

        // 산업 분야 전체 조회 + 선택 여부 매핑
        List<WorkField> allWorkFields = workFieldRepository.findAll();
        Integer selectedWorkFieldId = company.getWorkField().getId();

        List<CompanyResponse.WorkFieldDTO> workFieldDTOs = new ArrayList<>();
        for (WorkField wf : allWorkFields) {
            boolean isChecked = wf.getId().equals(selectedWorkFieldId);
            workFieldDTOs.add(new CompanyResponse.WorkFieldDTO(wf.getId(), wf.getName(), isChecked));
        }

        // DTO 조립
        CompanyResponse.UpdateFormDTO dto = new CompanyResponse.UpdateFormDTO();
        dto.setId(company.getId());
        dto.setNameKr(company.getNameKr());
        dto.setNameEn(company.getNameEn());
        dto.setOneLineIntro(company.getOneLineIntro());
        dto.setIntroduction(company.getIntroduction());
        dto.setStartDate(company.getStartDate());
        dto.setBusinessNumber(company.getBusinessNumber());
        dto.setEmail(company.getEmail());
        dto.setContactManager(company.getContactManager());
        dto.setAddress(company.getAddress());
        dto.setTechStacks(techStackDTOs);
        dto.setWorkFields(workFieldDTOs);

        return dto;
    }

    // 기업 수정
    @Transactional
    public void 기업수정(CompanyRequest.UpdateDTO dto) {

        Company company = companyRepository.findById(dto.getId());

        // workFieldId로 조회
        WorkField workField = workFieldRepository.findById(dto.getWorkFieldId());
        if (workField == null) {
            throw new RuntimeException("선택한 산업 분야가 존재하지 않습니다.");
        }

        company.update(dto, workField);

        // 기존 기술 스택 삭제 후 새로 저장
        companyTechStackRepository.deleteByCompanyId(company.getId());

        List<String> techStackList = dto.getTechStack();
        if (techStackList != null) {
            for (String techName : techStackList) {
                TechStack techStack = techStackRepository.findByName(techName);
                if (techStack != null) {
                    CompanyTechStack cts = new CompanyTechStack(company, techStack);
                    companyTechStackRepository.save(cts);
                }
            }
        }
    }

    @Transactional
    public List<CompanyResponse.CompanyManageJobDTO> 기업공고관리(Integer companyId) {
        List<Job> jobList = companyRepository.findJobsByCompanyId(companyId);

        List<CompanyResponse.CompanyManageJobDTO> companyManageJobDTOS = new ArrayList<>();
        for (Job job : jobList) {
            companyManageJobDTOS.add(new CompanyResponse.CompanyManageJobDTO(
                    job.getId(),
                    job.getTitle(),
                    job.getCareerLevel(),
                    job.getCreatedAt().toLocalDateTime(),
                    job.getJobGroup().getName()
            ));
        }
        return companyManageJobDTOS;
    }

    @Transactional
    public CompanyResponse.CompanyManageResumePageDTO 지원자조회(Integer jobId) {
        List<Application> applications = companyRepository.findApplicationsByJobId(jobId);

        if (applications.isEmpty()) {
            return new CompanyResponse.CompanyManageResumePageDTO(
                    jobId,
                    "지원자가 없습니다.",
                    new ArrayList<>()
            );
        }

        // 지원자 전체 리스트 만들기
        List<CompanyResponse.CompanyManageResumeDTO> applicationDTOs = new ArrayList<>();
        for (Application app : applications) {
            applicationDTOs.add(new CompanyResponse.CompanyManageResumeDTO(
                    app.getUser().getUsername(),
                    app.getResume().getTitle(),
                    app.getResume().getCareerLevel(),
                    app.getCreatedAt().toLocalDateTime(),
                    app.getStatus()
            ));
        }

        // 공고(Job) 제목은 첫 번째 지원자의 job에서 가져오기
        Job job = applications.get(0).getJob();

        return new CompanyResponse.CompanyManageResumePageDTO(
                job.getId(),
                job.getTitle(),
                applicationDTOs
        );
    }
}