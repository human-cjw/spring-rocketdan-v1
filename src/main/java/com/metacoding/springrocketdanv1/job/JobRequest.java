package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.company.Company;
import com.metacoding.springrocketdanv1.jobGroup.JobGroup;
import com.metacoding.springrocketdanv1.jobTechStack.JobTechStack;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.user.User;
import com.metacoding.springrocketdanv1.workField.WorkField;
import lombok.Data;

import java.util.List;

public class JobRequest {

    @Data
    public static class JobSaveDTO {
        private String title;
        private String description;
        private String introduction;
        private String startDate;
        private String businessNumber;
        private String email;
        private String status;
        private String address;
        private String employmentType;
        private String careerLevel;
        private String location;
        private String workFieldName;  // 업무 분야 이름
        private String jobGroupName;   // 직무 그룹 이름
        private List<String> techStack; // 기술 스택 이름 리스트
        private String salaryRangeId;  // 연봉 범위 ID

        // DTO에서 엔티티로 변환하는 메서드
        public Job toEntity(User user, WorkField workField, List<TechStack> techStackList,
                            Company company, SalaryRange salaryRange, JobGroup jobGroup) {
            Job job = Job.builder()
                    .title(title)
                    .description(description)
                    .employmentType(employmentType)
                    .status(status)
                    .careerLevel(careerLevel)
                    .location(location)
                    .workField(workField)        // 업무 분야
                    .jobGroup(jobGroup)          // 직무 그룹
                    .salaryRange(salaryRange)    // 연봉 범위
                    .company(company)            // 기업
                    .user(user)                  // 사용자 (세션에서 가져온)
                    .build();

            // TechStack 처리 (JobTechStack을 사용해서 연관관계 설정)
            for (TechStack techStack : techStackList) {
                JobTechStack jobTechStack = new JobTechStack(job, techStack);
                job.getCompanyTechStackList().add(jobTechStack);
            }

            return job;
        }
    }
}