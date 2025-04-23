package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final BoardRepository boardRepository;

    public List<JobResponse.DTO> 글목록보기() {
        List<Job> jobs = jobRepository.findAll();  // 모든 Job 조회
        List<JobResponse.DTO> jobDTOs = new ArrayList<>();  // DTO 리스트 생성

        for (Job job : jobs) {
            JobResponse.DTO dto = new JobResponse.DTO();
            dto.setTitle(job.getTitle());
            dto.setCareerLevel(job.getCareerLevel());

            dto.setNameKr(job.getCompany().getNameKr());
            jobDTOs.add(dto);  // DTO 리스트에 추가
        }

        return jobDTOs;  // 변환된 DTO 리스트 반환
    }

    public List<JobResponse.DetailDTO> 글상세보기() {
        List<Job> jobs = jobRepository.findAll();  // 모든 Job 조회
        List<JobResponse.DetailDTO> jobDTOs = new ArrayList<>();  // DTO 리스트 생성

        for (Job job : jobs) {
            JobResponse.DetailDTO detailDto = new JobResponse.DetailDTO();
            detailDto.setTitle(job.getTitle());
            detailDto.setDeadline(job.getDeadline());
            detailDto.setCareerLevel(job.getCareerLevel());
            detailDto.setCreatedAt(job.getCreatedAt());
            detailDto.setDescription(job.getDescription());
            detailDto.setLocation(job.getLocation());
            detailDto.setEmploymentType(job.getEmploymentType());
            detailDto.setEmploymentType(job.getEmploymentType());
            detailDto.setWorkField(job.getWorkField());


            detailDto.setNameKr(job.getCompany().getNameKr());
            jobDTOs.add(detailDto);  // DTO 리스트에 추가
        }

        return jobDTOs;  // 변환된 DTO 리스트 반환
    }


}
