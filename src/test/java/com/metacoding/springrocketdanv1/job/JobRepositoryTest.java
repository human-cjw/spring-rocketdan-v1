package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.companyTechStack.CompanyTechStack;
import com.metacoding.springrocketdanv1.jobTechStack.JobTechStack;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(JobRepository.class)
@DataJpaTest
public class JobRepositoryTest {
    @Autowired
    private JobRepository jobRepository;

    @Test
    public void findByIdJoinJobTechStack_test() {
        // given
        Integer jobId = 1;

        // when
        Job job = jobRepository.findByIdJoinJobTechStackJoinTechStack(jobId);

        // eye
        List<JobTechStack> jobTechStacks = job.getJobTechStacks();
        for (JobTechStack jobTechStack : jobTechStacks) {
            System.out.println(jobTechStack.getTechStack().getId());
        }
    }
}
