package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.company.Company;
import com.metacoding.springrocketdanv1.company.CompanyRepository;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.techStack.TechStackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final TechStackRepository techStackRepository;
    private final CompanyRepository companyRepository;


    public List<TechStack> findAll() {
        return techStackRepository.findAll();
    }

    public void process(JobRequest.saveDTO dto) {
        Company company = new Company();
        company.setIntroduction(dto.getIntroduction());
        companyRepository.save(company);

        Job job = new Job();
        job.setTitle(dto.getTitle());
        job.setCompany(company); // 관계 설정
        jobRepository.save(job);
    }
}
