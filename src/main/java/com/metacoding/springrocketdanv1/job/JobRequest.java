package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.company.Company;
import com.metacoding.springrocketdanv1.jobGroup.JobGroup;
import com.metacoding.springrocketdanv1.jobTechStack.JobTechStack;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.workField.WorkField;
import lombok.Builder;
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
        private Integer companyId; // 일단 mustache에서 받아옴. 나중에 sessionUser 에서 가져올 수 있음
        private List<Integer> techStacks;

        public Job toEntity() {
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

            for (Integer techStackId : techStacks) {
                job.getJobTechStackList().add(
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
}
