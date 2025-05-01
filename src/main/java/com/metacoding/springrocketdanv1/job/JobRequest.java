package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.company.Company;
import com.metacoding.springrocketdanv1.jobGroup.JobGroup;
import com.metacoding.springrocketdanv1.jobTechStack.JobTechStack;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.workField.WorkField;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

public class JobRequest {

    @Data
    public static class JobSaveDTO {
        @Pattern(regexp = "^[a-zA-Z0-9가-힣()\\[\\]{}\\/!@#$%^&*\\-+=~\\.,]{2,20}$", message = "제목은 최소 2자에서 최대 20자 입니다")
        private String title;
        @Pattern(regexp = "^[a-zA-Z0-9가-힣()\\[\\]{}\\/!@#$%^&*\\-+=~\\.,]{2,300}$", message = "설명은 최소 2자에서 최대 300자 입니다")
        private String description;
        @Pattern(regexp = "^^[가-힣]{2,20}\\s[가-힣]{1,10}\\s[가-힣]{1,20}$", message = "주소는 두 단어만 작성해 주세요. 예)서울특별시 구로구 디지털로")
        private String location;
        @Pattern(regexp = "^(정규직|계약직|인턴|프리랜서)$", message = "정규직|계약직|인턴|프리랜서 중 하나로 입력해 주세요")
        private String employmentType;
        private String deadline;
        private String status;
        private Integer jobGroupId;
        private Integer workFieldId;
        @Pattern(regexp = "^(경력|신입)$", message = "경력|신입 중 하나를 넣어주세요")
        private String careerLevel;
        private Integer salaryRangeId;
        private List<Integer> techStackIds;

        public Job toEntity(Integer companyId) {
            Job job = Job.builder()
                    .title(title)
                    .description(description)
                    .location(location)
                    .employmentType(employmentType)
                    .deadline(deadline)
                    .status(status)
                    .careerLevel(careerLevel)
                    .jobGroup(JobGroup.builder()
                            .id(jobGroupId)
                            .build())
                    .company(Company.builder()
                            .id(companyId)
                            .build())
                    .workField(WorkField.builder()
                            .id(workFieldId)
                            .build())
                    .salaryRange(SalaryRange.builder()
                            .id(salaryRangeId)
                            .build())
                    .build();

            for (Integer techStackId : techStackIds) {
                job.getJobTechStacks().add(
                        JobTechStack.builder()
                                .job(job)
                                .techStack(TechStack.builder()
                                        .id(techStackId)
                                        .build()
                                ).build()
                );
            }

            return job;
        }
    }

    @Data
    public static class JobUpdateDTO {
        @Pattern(regexp = "^[a-zA-Z0-9가-힣()\\[\\]{}\\/!@#$%^&*\\-+=~\\.,]{2,20}$", message = "제목은 최소 2자에서 최대 20자 입니다")
        private String title;
        @Pattern(regexp = "^[a-zA-Z0-9가-힣()\\[\\]{}\\/!@#$%^&*\\-+=~\\.,]{2,300}$", message = "설명은 최소 2자에서 최대 300자 입니다")
        private String description;
        @Pattern(regexp = "^[가-힣]{2,20}\\s[가-힣]{1,10}\\s[가-힣]{1,20}$", message = "주소는 세 단어만 작성해 주세요. 예)서울특별시 구로구 디지털로")
        private String location;
        private String employmentType;
        private String deadline;
        private String status;
        private Integer jobGroupId;
        private Integer workFieldId;
        private String careerLevel;
        private Integer salaryRangeId;
        private List<Integer> techStackIds;
    }
}
