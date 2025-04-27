package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.company.Company;
import com.metacoding.springrocketdanv1.company.CompanyRepository;
import com.metacoding.springrocketdanv1.job.Job;
import com.metacoding.springrocketdanv1.job.JobRepository;
import com.metacoding.springrocketdanv1.job.JobRequest;
import com.metacoding.springrocketdanv1.jobGroup.JobGroup;
import com.metacoding.springrocketdanv1.jobGroup.JobGroupRepository;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRangeRepository;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.techStack.TechStackRepository;
import com.metacoding.springrocketdanv1.user.User;
import com.metacoding.springrocketdanv1.workField.WorkField;
import com.metacoding.springrocketdanv1.workField.WorkFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final TechStackRepository techStackRepository;
    private final CompanyRepository companyRepository;
    private final WorkFieldRepository workFieldRepository;
    private final JobGroupRepository jobGroupRepository;
    private final SalaryRangeRepository salaryRangeRepository;

    public Job 공고등록(JobRequest.JobSaveDTO requestDTO, User sessionUser) {
        // 1. WorkField 찾기 or 생성
        WorkField workField = workFieldRepository.findByName(requestDTO.getWorkFieldName());
        if (workField == null) {
            workField = workFieldRepository.save(new WorkField(requestDTO.getWorkFieldName()));
        }

        // 2. JobGroup 찾기 or 생성
        JobGroup jobGroup = jobGroupRepository.findByName(requestDTO.getJobGroupName());
        if (jobGroup == null) {
            jobGroup = jobGroupRepository.save(new JobGroup(requestDTO.getJobGroupName()));
        }

        // 3. SalaryRange 찾기
        Integer salaryRangeId = Integer.parseInt(requestDTO.getSalaryRangeId()); // ★ String을 Integer로 변환
        SalaryRange salaryRange = salaryRangeRepository.findById(salaryRangeId);
        if (salaryRange == null) {
            throw new RuntimeException("SalaryRange not found");
        }

        // 4. Company 찾기
        Company company = companyRepository.findByUser(sessionUser);
        if (company == null) {
            throw new RuntimeException("Company not found");
        }

        // 5. TechStack 리스트 조회
        List<TechStack> techStackList = new ArrayList<>();
        if (requestDTO.getTechStack() != null) {
            for (String name : requestDTO.getTechStack()) {
                TechStack ts = techStackRepository.findByName(name);
                if (ts != null) techStackList.add(ts);
            }
        }

        // 6. 엔티티 변환 (★ 중요!)
        Job job = requestDTO.toEntity(sessionUser, workField, techStackList, company, salaryRange, jobGroup);

        // 7. 저장
        return jobRepository.save(job);
    }
}