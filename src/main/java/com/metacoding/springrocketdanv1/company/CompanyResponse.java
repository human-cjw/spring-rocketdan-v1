package com.metacoding.springrocketdanv1.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CompanyResponse {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class CompanyResponseDTO {
        private String nameKr;
        private String nameEn;
        private String ceo;
        private String businessNumber;
        private String email;
        private String phone;
        private String address;
        private String introduction;
        private String oneLineIntro;
        private String homepageUrl;
        private String logoImageUrl;
        private String infoImageUrl;
        private String contactManager;
        private String startDate;
        private String workFieldName;
        private List<String> techStackList;
        private boolean isOwner;
    }

    @Data
    public static class UpdateFormDTO {
        private Integer id;
        private String nameKr;
        private String nameEn;
        private String oneLineIntro;
        private String introduction;
        private String startDate;
        private String businessNumber;
        private String email;
        private String contactManager;
        private String address;
        private String workFieldName;
        private List<CompanyResponse.TechStackDTO> techStacks;
        private List<CompanyResponse.WorkFieldDTO> workFields;
    }

    @Data
    public static class TechStackDTO {
        private String name;
        private boolean isChecked;

        public TechStackDTO(String name, boolean isChecked) {
            this.name = name;
            this.isChecked = isChecked;
        }
    }

    @Data
    public static class WorkFieldDTO {
        private Integer id;
        private String name;
        private boolean isChecked;

        public WorkFieldDTO(Integer id, String name, boolean isChecked) {
            this.id = id;
            this.name = name;
            this.isChecked = isChecked;
        }
    }

    @Getter
    public static class CompanyManageJobDTO {
        private Integer id;
        private String title;
        private String careerLevel;
        private String createdAt;
        private String jobGroupName;

        public CompanyManageJobDTO(Integer id, String title, String careerLevel, LocalDateTime createdAt, String jobGroupName) {
            this.id = id;
            this.title = title;
            this.careerLevel = careerLevel;
            this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.jobGroupName = jobGroupName;
        }
    }

    @Getter
    public static class CompanyManageResumeDTO {
        private String username;
        private String resumeTitle;
        private String careerLevel;
        private String createdAt;
        private String status;
        private boolean isAccepted; // 합격
        private boolean isRejected; // 탈락
        private boolean isPending;  // 접수 or 검토중

        public CompanyManageResumeDTO(String username, String resumeTitle, String careerLevel, LocalDateTime createdAt, String status) {
            this.username = username;
            this.resumeTitle = resumeTitle;
            this.careerLevel = careerLevel;
            this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.status = status;
            this.isAccepted = "합격".equals(status);
            this.isRejected = "불합격".equals(status);
            this.isPending = "접수".equals(status) || "검토".equals(status);
        }
    }

    @Data
    public static class CompanyManageResumePageDTO {
        private Integer jobId;
        private String jobTitle;
        private List<CompanyManageResumeDTO> applications;

        public CompanyManageResumePageDTO(Integer jobId, String jobTitle, List<CompanyManageResumeDTO> applications) {
            this.jobId = jobId;
            this.jobTitle = jobTitle;
            this.applications = applications;
        }
    }
}
