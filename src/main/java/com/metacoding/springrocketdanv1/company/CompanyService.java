package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.companyTechStack.CompanyTechStackRepository;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.techStack.TechStackRepository;
import com.metacoding.springrocketdanv1.user.User;
import com.metacoding.springrocketdanv1.workField.WorkField;
import com.metacoding.springrocketdanv1.workField.WorkFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public CompanyResponse.CompanyResponseDTO 기업상세(Integer companyId) {

        // 1. 회사 정보 조회
        Company company = companyRepository.findById(companyId);

        // 2. 기술 스택 이름만 추출
        List<TechStack> techStacks = companyTechStackRepository.findByCompanyId(companyId);
        List<String> techStackNames = techStacks.stream()
                .map(TechStack::getName)
                .collect(Collectors.toList());

        // 3. 산업 분야 이름 조회
        String workFieldName = workFieldRepository.findNameById(company.getWorkField().getId());

        String startDate = company.getStartDate();

        // 4. DTO로 매핑
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
                startDate,
                workFieldName,
                techStackNames
        );
    }

    public List<Company> 기업리스트() {
        return companyRepository.findAll();
    }

    @Transactional
    public Company 기업등록(CompanyRequest.CompanySaveDTO requestDTO, User sessionUser) {
        // 산업분야
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
        return companyRepository.save(company);
    }
}


