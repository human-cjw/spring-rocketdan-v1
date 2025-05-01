package com.metacoding.springrocketdanv1.resumeTechStack;

import com.metacoding.springrocketdanv1.techStack.TechStack;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(ResumeTechStackRepository.class)
@DataJpaTest
public class ResumeTechStackRepositoryTest {
    @Autowired
    private ResumeTechStackRepository resumeTechStackRepository;

    @Test
    public void findAllByResumeIdTest() {
        // given
        Integer resumeId = 1;

        // when
        List<ResumeTechStack> resumeTechStacks = resumeTechStackRepository.findAllByResumeId(resumeId);

        // eye

        for (ResumeTechStack resumeTechStack : resumeTechStacks) {
            System.out.println("결과==================" + resumeTechStacks);
        }
    }
}
