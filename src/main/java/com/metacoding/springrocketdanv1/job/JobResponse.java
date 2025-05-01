package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.jobGroup.JobGroup;
import com.metacoding.springrocketdanv1.jobGroup.JobGroupResponse;
import com.metacoding.springrocketdanv1.jobTechStack.JobTechStackResponse;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRangeResponse;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.workField.WorkField;
import com.metacoding.springrocketdanv1.workField.WorkFieldResponse;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class JobResponse {

    @Data
    public static class DTO {
        private int id;
        private String title;
        private String careerLevel;
        private String nameKr;
    }

    @Data
    public static class DetailDTO {
        private String title;
        private String deadline;
        private String careerLevel;
        private String createdAt;
        private String description;
        private String location;
        private String employmentType;
        private String workField;
        private String nameKr;
        private SalaryRangeDTO salaryRange;
        private Integer companyId;
        private Integer jobId;
        private String contactManager;
        private String companyPhone;

        private boolean Bookmarked;

        public DetailDTO(String title, String deadline, String careerLevel,
                         Timestamp createdAt, String description, String location,
                         String employmentType, String workField, String nameKr,
                         SalaryRange salaryRange, Integer companyId, Integer jobId,
                         String contactManager, String companyPhone) {
            this.title = title;
            this.deadline = deadline;
            this.careerLevel = careerLevel;
            this.createdAt = createdAt.toString().substring(0, 10);
            this.description = description;
            this.location = location;
            this.employmentType = employmentType;
            this.workField = workField;
            this.nameKr = nameKr;
            this.salaryRange = new SalaryRangeDTO(salaryRange);
            this.companyId = companyId;
            this.jobId = jobId;
            this.contactManager = contactManager;
            this.companyPhone = companyPhone;

        }

        public void setBookmarked(boolean Bookmarked) {
            this.Bookmarked = Bookmarked;
        }

        class SalaryRangeDTO {
            private String label;
            private String minSalary;
            private String maxSalary;

            public SalaryRangeDTO(SalaryRange salaryRange) {
                this.label = salaryRange.getLabel();
                this.minSalary = salaryRange.getMinSalary().toString();
                this.maxSalary = salaryRange.getMaxSalary().toString();
            }
        }
    }

    @Data
    public static class JobSaveDTO {
        private List<TechStack> techStacks;
        private List<WorkField> workFields;
        private List<SalaryRange> salaryRanges;
        private List<JobGroup> jobGroups;

        public JobSaveDTO(
                List<TechStack> techStacks,
                List<WorkField> workFields,
                List<SalaryRange> salaryRanges,
                List<JobGroup> jobGroups
        ) {
            this.techStacks = techStacks;
            this.workFields = workFields;
            this.salaryRanges = salaryRanges;
            this.jobGroups = jobGroups;
        }
    }

    @Data
    public static class JobUpdateDTO {
        private Integer id;
        private String title;
        private String description;
        private String location;
        private String deadline;
        private String status;

        private List<CareerLevel> careerLevels;
        private List<EmploymentType> employmentTypes;
        private List<JobTechStackResponse.JobTechStackUpdateDTO> jobTechStackUpdateDTOS;
        private List<WorkFieldResponse.WorkFieldUpdateDTO> workFieldUpdateDTOS;
        private List<SalaryRangeResponse.SalaryRangeUpdateDTO> salaryRangeUpdateDTOS;
        private List<JobGroupResponse.JobGroupUpdateDTO> jobGroupUpdateDTOS;

        public JobUpdateDTO(
                Integer id,
                String title,
                String description,
                String location,
                String deadline,
                String status,
                List<CareerLevel> careerLevels,
                List<EmploymentType> employmentTypes,
                List<JobTechStackResponse.JobTechStackUpdateDTO> jobTechStackUpdateDTOS,
                List<WorkFieldResponse.WorkFieldUpdateDTO> workFieldUpdateDTOS,
                List<SalaryRangeResponse.SalaryRangeUpdateDTO> salaryRangeUpdateDTOS,
                List<JobGroupResponse.JobGroupUpdateDTO> jobGroupUpdateDTOS
        ) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.location = location;
            this.deadline = deadline;
            this.status = status;
            this.careerLevels = careerLevels;
            this.employmentTypes = employmentTypes;
            this.jobTechStackUpdateDTOS = jobTechStackUpdateDTOS;
            this.workFieldUpdateDTOS = workFieldUpdateDTOS;
            this.salaryRangeUpdateDTOS = salaryRangeUpdateDTOS;
            this.jobGroupUpdateDTOS = jobGroupUpdateDTOS;
        }

        public static class EmploymentType {
            private String value;
            private String name;
            private boolean isSelected;

            public EmploymentType(
                    String value,
                    String name,
                    boolean isSelected
            ) {
                this.value = value;
                this.name = name;
                this.isSelected = isSelected;
            }
        }

        public static class CareerLevel {
            private String value;
            private String name;
            private boolean isSelected;

            public CareerLevel(
                    String value,
                    String name,
                    boolean isSelected
            ) {
                this.value = value;
                this.name = name;
                this.isSelected = isSelected;
            }
        }
    }
}