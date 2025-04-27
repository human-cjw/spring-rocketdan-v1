package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.company.Company;
import com.metacoding.springrocketdanv1.jobGroup.JobGroup;
import com.metacoding.springrocketdanv1.jobTechStack.JobTechStack;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.workField.WorkField;
import lombok.Data;

import java.util.List;

public class JobRequest {

    @Data
    public static class JobSaveDTO {
        private String title;
        private String description;
        private String location;
        private String employmentType;
        private String deadline;
        private String status;
        private Integer jobGroupId;
        private Integer workFieldId;
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
        private String title;
        private String description;
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
