package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.companyTechStack.CompanyTechStack;
import com.metacoding.springrocketdanv1.companyTechStack.CompanyTechStackRepository;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.techStack.TechStackRepository;
import com.metacoding.springrocketdanv1.user.User;
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

    @Transactional(readOnly = true)
    public CompanyResponse.CompanyResponseDTO 기업상세(Integer companyId) {
        Company company = companyRepository.findById(companyId);

        List<TechStack> techStacks = companyTechStackRepository.findByCompanyId(companyId);
        List<String> techStackNames = techStacks.stream()
                .map(TechStack::getName)
                .collect(Collectors.toList());

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
                techStackNames
        );
    }

    @Transactional(readOnly = true)
    public List<Company> 기업리스트() {
        return companyRepository.findAll();
    }

    @Transactional
    public Company 기업등록(CompanyRequest.CompanySaveDTO requestDTO, User sessionUser) {
        // 산업분야 조회 또는 저장
        WorkField workField = workFieldRepository.findByName(requestDTO.getWorkFieldName());
        if (workField == null) {
            workField = workFieldRepository.save(new WorkField(requestDTO.getWorkFieldName()));
        }

        // 기술 스택 조회
        List<TechStack> techStackList = new ArrayList<>();
        if (requestDTO.getTechStack() != null) {
            for (String name : requestDTO.getTechStack()) {
                TechStack ts = techStackRepository.findByName(name);
                if (ts != null) techStackList.add(ts);
            }
        }

        // 회사 + 연관 기술 스택 cascade 저장
        Company company = requestDTO.toEntity(sessionUser, workField, techStackList);
        companyRepository.save(company);

        // 세션에서 넘어온 User가 아니라, DB에서 영속 객체를 다시 가져옴
        User user = em.find(User.class, sessionUser.getId());

        try {
            // userType = 'company' 로 변경
            Field userTypeField = User.class.getDeclaredField("userType");
            userTypeField.setAccessible(true);
            userTypeField.set(user, "company");

            // companyId 세팅
            Field companyIdField = User.class.getDeclaredField("companyId");
            companyIdField.setAccessible(true);
            companyIdField.set(user, company.getId());

        } catch (Exception e) {
            throw new RuntimeException("User 업데이트 실패", e);
        }

        return company;
    }

    @Transactional(readOnly = true)
    public CompanyResponse.UpdateFormDTO 내기업조회(Integer userId) {
        Company company = companyRepository.findByUserId(userId);

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
        dto.setWorkFieldName(company.getWorkField().getName());
        dto.setTechStacks(techStackDTOs);

        return dto;
    }

    @Transactional
    public void 기업수정(CompanyRequest.UpdateDTO dto) {
        Company company = companyRepository.findById(dto.getId());

        WorkField workField = workFieldRepository.findByName(dto.getWorkFieldName());
        if (workField == null) {
            workField = workFieldRepository.save(new WorkField(dto.getWorkFieldName()));
        }

        company.update(dto, workField);

        companyTechStackRepository.deleteByCompanyId(company.getId());

        List<String> techStackList = dto.getTechStack();
        if (techStackList != null) {
            for (String techName : techStackList) {
                TechStack techStack = techStackRepository.findByName(techName);
                CompanyTechStack cts = new CompanyTechStack(company, techStack);
                companyTechStackRepository.save(cts);
            }
        }
    }
}
