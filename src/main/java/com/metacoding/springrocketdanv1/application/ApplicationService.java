package com.metacoding.springrocketdanv1.application;

import com.metacoding.springrocketdanv1.job.Job;
import com.metacoding.springrocketdanv1.job.JobRepository;
import com.metacoding.springrocketdanv1.resume.Resume;
import com.metacoding.springrocketdanv1.resume.ResumeRepository;
import com.metacoding.springrocketdanv1.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final ResumeRepository resumeRepository;

    public ApplicationResponse.ApplyDTO 지원보기(Integer jobId, UserResponse.SessionUserDTO user) {
        // 공고 조회하기
        Job jobPC = jobRepository.findById(jobId);

        // 이력서 조회하기
        List<Resume> resumes = resumeRepository.findAllByUserId(user.getId());

        // DTO 만들어 넘기기
        return new ApplicationResponse.ApplyDTO(jobPC, user.getUsername(), resumes);
    }

    @Transactional
    public void 지원하기(Integer jobId, ApplicationRequest.SaveDTO reqDTO, Integer userId) {

//        Job job = jobRepository.findById(jobId);
//        Company company = job.getCompany();
//        Resume resume = resumeRepository.findById(reqDTO.getResumeId());
//        User user = resume.getUser();

//        Application application = reqDTO.toEntity(job, company, resume, user);

        Application application = reqDTO.toEntity(jobId, userId);

        applicationRepository.save(application);
    }
}
