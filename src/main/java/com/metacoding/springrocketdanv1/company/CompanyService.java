package com.metacoding.springrocketdanv1.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public List<Company> 기업리스트() {
        return companyRepository.findAll();
    }
}
