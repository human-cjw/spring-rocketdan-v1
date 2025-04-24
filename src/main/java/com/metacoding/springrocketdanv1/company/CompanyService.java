package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.user.User;
import com.metacoding.springrocketdanv1.workField.WorkField;
import com.metacoding.springrocketdanv1.workField.WorkFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final WorkFieldRepository workFieldRepository;

    public List<Company> 기업리스트() {
        return companyRepository.findAll();
    }

    @Transactional
    public Integer 기업등록(CompanyRequest.CompanySaveDTO requestDTO, User sessionUser) {
        WorkField workField = workFieldRepository.findByName(requestDTO.getWorkFieldName());
        Company company = requestDTO.toEntity(sessionUser, null);
        Company companyPC = companyRepository.save(company);
        return companyPC.getId();
    }
}
