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
        Application application = reqDTO.toEntity(jobId, userId);

        applicationRepository.save(application);
    }

    public ApplicationResponse.ApplyDoneDTO 지원완료(Integer jobId) {
        // 공고 정보 가져오기
        Job jobPC = jobRepository.findById(jobId);

        // 공고 정보 DTO에 담기
        ApplicationResponse.ApplyDoneDTO respDTO = new ApplicationResponse.ApplyDoneDTO(jobPC);

        return respDTO;
    }
}
