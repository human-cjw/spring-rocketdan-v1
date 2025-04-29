package com.metacoding.springrocketdanv1.application;

import com.metacoding.springrocketdanv1.job.Job;
import com.metacoding.springrocketdanv1.resume.Resume;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ApplicationResponse {

    @Data
    public static class ApplyDTO {
        private Integer jobId;
        private String jobTitle;
        private String username;
        private Integer companyId;
        private List<ResumeDTO> resumes = new ArrayList<>();

        public ApplyDTO(Job job, String username, List<Resume> resumes) {
            this.jobId = job.getId();
            this.jobTitle = job.getTitle();
            this.companyId = job.getCompany().getId();
            this.username = username;

            for (Resume resume : resumes) {
                this.resumes.add(new ResumeDTO(resume.getId(), resume.getTitle(), resume.getIsDefault()));
            }
        }

        public static class ResumeDTO {
            private Integer resumeId;
            private String title;
            private boolean isChecked; // 기본이력서를 체크

            public ResumeDTO(Integer resumeId, String title, boolean isChecked) {
                this.resumeId = resumeId;
                this.title = title;
                this.isChecked = isChecked;
            }
        }
    }

    @Data
    public static class ApplyDoneDTO {
        private String title;
        private String deadline;
        private String employmentType;
        private String careerLevel;
        private String updatedAt;
        private String description;

        public ApplyDoneDTO(Job job) {
            this.title = job.getTitle();
            this.deadline = job.getDeadline();
            this.employmentType = job.getEmploymentType();
            this.careerLevel = job.getCareerLevel();
            this.updatedAt = job.getUpdatedAt() != null ? job.getUpdatedAt().toString().substring(0, 10) : null;
            this.description = job.getDescription();
        }
    }
}
