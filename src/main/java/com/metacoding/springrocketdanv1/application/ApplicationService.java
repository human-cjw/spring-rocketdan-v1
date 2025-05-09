package com.metacoding.springrocketdanv1.application;

import com.metacoding.springrocketdanv1._core.error.ex.Exception400;
import com.metacoding.springrocketdanv1.job.Job;
import com.metacoding.springrocketdanv1.job.JobRepository;
import com.metacoding.springrocketdanv1.resume.Resume;
import com.metacoding.springrocketdanv1.resume.ResumeRepository;
import com.metacoding.springrocketdanv1.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final ResumeRepository resumeRepository;

    public ApplicationResponse.ProcessDTO2 입사지원현황보기(Integer userId, Integer jobId) {
        Application applicationPS = applicationRepository.findByUserIdAndJobId(userId, jobId);
        return new ApplicationResponse.ProcessDTO2(applicationPS);
    }

    public List<ApplicationResponse.ApplicationManageDTO> 내지원목록보기(Integer userId, String status) {
        List<Application> applications = applicationRepository.findByUserIdStatus(userId, status);

        List<ApplicationResponse.ApplicationManageDTO> applicationManageDTOS = new ArrayList<>();
        for (Application application : applications) {

            ApplicationResponse.ApplicationManageDTO.JobDTO jobDTO = new ApplicationResponse.ApplicationManageDTO
                    .JobDTO(application.getJob());
            ApplicationResponse.ApplicationManageDTO.ResumeDTO resumeDTO = new ApplicationResponse.ApplicationManageDTO
                    .ResumeDTO(application.getResume());
            ApplicationResponse.ApplicationManageDTO.CompanyDTO companyDTO = new ApplicationResponse.ApplicationManageDTO
                    .CompanyDTO(application.getCompany());
            ApplicationResponse.ApplicationManageDTO.UserDTO userDTO = new ApplicationResponse.ApplicationManageDTO
                    .UserDTO(application.getUser());

            String formattedCreatedAt = application.getCreatedAt().toString().substring(0, 16);

            ApplicationResponse.ApplicationManageDTO applicationManageDTO = new ApplicationResponse.ApplicationManageDTO(
                    application.getJob().getId(),
                    application.getStatus(),
                    formattedCreatedAt,
                    jobDTO,
                    resumeDTO,
                    companyDTO,
                    userDTO
            );
            applicationManageDTOS.add(applicationManageDTO);
        }
        return applicationManageDTOS;
    }

    public ApplicationResponse.ApplyDTO 지원보기(Integer jobId, UserResponse.SessionUserDTO user) {

        // 이미 지원한 회사인지 확인
        // 지원 테이블에서 회사 아이디 유저 아이디 같은게 있는지 확인

        // 공고 조회하기
        Job jobPC = jobRepository.findById(jobId);

        Integer companyId = jobPC.getCompany().getId();
        Integer userId = user.getId();

        Application applicationPC = applicationRepository.findByCompanyIdWithUserId(companyId, userId);
        if (applicationPC != null) {
            throw new Exception400("잘못된 요청입니다");
        }

        // 이력서 조회하기
        List<Resume> resumes = resumeRepository.findAllByUserId(user.getId());

        // DTO 만들어 넘기기
        return new ApplicationResponse.ApplyDTO(jobPC, user.getUsername(), resumes);
    }

    @Transactional
    public void 지원하기(Integer jobId, ApplicationRequest.SaveDTO reqDTO, Integer userId) {
        Application applicationPS = applicationRepository.findByJobIdWithUserId(jobId, userId);
        if (applicationPS != null) {
            throw new Exception400("잘못된 요청입니다");
        }
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
