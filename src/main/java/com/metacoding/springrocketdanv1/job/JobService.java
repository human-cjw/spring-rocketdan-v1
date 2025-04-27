package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.jobGroup.JobGroup;
import com.metacoding.springrocketdanv1.jobGroup.JobGroupRepository;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRangeRepository;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRangeResponse;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.techStack.TechStackRepository;
import com.metacoding.springrocketdanv1.workField.WorkField;
import com.metacoding.springrocketdanv1.workField.WorkFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final TechStackRepository techStackRepository;
    private final WorkFieldRepository workFieldRepository;
    private final SalaryRangeRepository salaryRangeRepository;
    private final JobGroupRepository jobGroupRepository;

    public List<JobResponse.DTO> 글목록보기() {
        List<Job> jobs = jobRepository.findAll();  // 모든 Job 조회
        List<JobResponse.DTO> jobDTOs = new ArrayList<>();  // DTO 리스트 생성

        for (Job job : jobs) {
            JobResponse.DTO dto = new JobResponse.DTO();
            dto.setId(job.getId());
            dto.setTitle(job.getTitle());
            dto.setCareerLevel(job.getCareerLevel());

            dto.setNameKr(job.getCompany().getNameKr());
            jobDTOs.add(dto);  // DTO 리스트에 추가
        }

        return jobDTOs;  // 변환된 DTO 리스트 반환
    }

    public JobResponse.DetailDTO 글상세보기(Integer id) {
        // jobId로 Job을 조회합니다.
        Job job = jobRepository.findById(id);


        // 만약 job이 null이라면, 예외를 던지거나 처리할 수 있습니다.
        if (job == null) {
            throw new RuntimeException(id + "번 게시물을 찾을 수 없습니다."); // 예시로 RuntimeException을 던지며 처리
        }

        // SalaryRange 조회
        SalaryRange salaryRange = job.getSalaryRange();
        SalaryRangeResponse.SalaryRangeDTO salaryRangeDTO = null;

        // SalaryRange가 있을 경우, SalaryRangeDTO로 변환
        if (salaryRange != null) {
            salaryRangeDTO = new SalaryRangeResponse.SalaryRangeDTO(
                    salaryRange.getMinSalary(),
                    salaryRange.getMaxSalary()
            );
        }

        // JobDetailDTO 생성
        JobResponse.DetailDTO detailDto = new JobResponse.DetailDTO(
                job.getTitle(),
                job.getDeadline(),
                job.getCareerLevel(),
                job.getCreatedAt(),
                job.getDescription(),
                job.getLocation(),
                job.getEmploymentType(),
                job.getWorkField().getName(),
                job.getCompany().getNameKr(),
                salaryRangeDTO,
                job.getCompany().getId()
        );

        return detailDto;
    }


    public JobResponse.JobSaveDTO 등록보기() {
        List<TechStack> techStacks = techStackRepository.findAll();
        List<WorkField> workFields = workFieldRepository.findAll();
        List<SalaryRange> salaryRanges = salaryRangeRepository.findAll();
        List<JobGroup> jobGroups = jobGroupRepository.findAll();
        return new JobResponse.JobSaveDTO(
                techStacks,
                workFields,
                salaryRanges,
                jobGroups
        );
    }

    @Transactional
    public void 등록하기(JobRequest.JobSaveDTO reqDTO) {
        Job job = reqDTO.toEntity();
        jobRepository.save(job);
    }
}