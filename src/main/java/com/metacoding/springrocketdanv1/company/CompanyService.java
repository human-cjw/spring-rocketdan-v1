package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.companyTechStack.CompanyTechStack;
import com.metacoding.springrocketdanv1.companyTechStack.CompanyTechStackRepository;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.techStack.TechStackRepository;
import com.metacoding.springrocketdanv1.workField.WorkFieldRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyTechStackRepository companyTechStackRepository;
    private final WorkFieldRepository workFieldRepository;

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

        // 4. DTO로 매핑
        return new CompanyResponse.CompanyResponseDTO(
                company.getNameKr(),
                company.getCeo(),
                company.getEmail(),
                company.getAddress(),
                company.getHomepageUrl(),
                company.getIntroduction(),
                company.getOneLineIntro(),
                company.getLogoImageUrl(),
                company.getInfoImageUrl(),
                company.getContactManager(),
                workFieldName,
                techStackNames
        );
    }
}