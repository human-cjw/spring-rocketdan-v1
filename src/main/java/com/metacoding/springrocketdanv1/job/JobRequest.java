package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.jobGroup.JobGroup;
import com.metacoding.springrocketdanv1.techStack.TechStack;
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
        private String salaryRangeId;
        private List<TechStack> techStacks;

        public Job toEntity() {
            return Job.builder()
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
                    .build();
        }
    }
}
