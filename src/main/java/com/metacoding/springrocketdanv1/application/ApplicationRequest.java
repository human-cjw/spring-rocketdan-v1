package com.metacoding.springrocketdanv1.application;

import com.metacoding.springrocketdanv1.company.Company;
import com.metacoding.springrocketdanv1.job.Job;
import com.metacoding.springrocketdanv1.resume.Resume;
import com.metacoding.springrocketdanv1.user.User;
import lombok.Data;

public class ApplicationRequest {

    @Data
    public static class SaveDTO {
        private Integer resumeId;
        private Integer companyId;
        private String status;

        public Application toEntity(Integer jobId, Integer userId) {
            return Application.builder()
                    .job(Job.builder().id(jobId).build())
                    .company(Company.builder().id(this.companyId).build())
                    .resume(Resume.builder().id(this.resumeId).build())
                    .user(User.builder().id(userId).build())
                    .status(this.status)
                    .build();
        }
    }
}
