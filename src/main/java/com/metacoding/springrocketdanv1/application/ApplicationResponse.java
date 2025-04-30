package com.metacoding.springrocketdanv1.application;

import com.metacoding.springrocketdanv1.company.Company;
import com.metacoding.springrocketdanv1.job.Job;
import com.metacoding.springrocketdanv1.resume.Resume;
import com.metacoding.springrocketdanv1.user.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ApplicationResponse {

    @Data
    public static class ApplicationManageDTO {
        private Integer id;
        private String status; // 지원 상태. 접수, 검토, 합격, 탈락
        private String createdAt;
        private JobDTO job;
        private ResumeDTO resume;
        private CompanyDTO company;
        private UserDTO user;


        public ApplicationManageDTO(Integer id, String status, String createdAt, JobDTO job, ResumeDTO resume, CompanyDTO company, UserDTO user) {
            this.id = id;
            this.status = status;
            this.createdAt = createdAt;
            this.job = job;
            this.resume = resume;
            this.company = company;
            this.user = user;
        }

        @Data
        public static class JobDTO {
            private Integer id;
            private String title;
            private String careerLevel;

            public JobDTO(Job job) {
                this.id = job.getId();
                this.title = job.getTitle();
                this.careerLevel = job.getCareerLevel();
            }
        }

        @Data
        public static class ResumeDTO {
            private Integer id;
            private String title;

            public ResumeDTO(Resume resume) {
                this.id = resume.getId();
                this.title = resume.getTitle();
            }
        }

        @Data
        public static class CompanyDTO {
            private Integer id;
            private String nameKr;

            public CompanyDTO(Company company) {
                this.id = company.getId();
                this.nameKr = company.getNameKr();
            }
        }

        @Data
        public static class UserDTO {
            private Integer id;
            private String username;

            public UserDTO(User user) {
                this.id = user.getId();
                this.username = user.getUsername();
            }
        }
    }

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
