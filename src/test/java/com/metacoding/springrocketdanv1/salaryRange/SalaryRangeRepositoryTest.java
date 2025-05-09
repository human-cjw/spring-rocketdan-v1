package com.metacoding.springrocketdanv1.salaryRange;

import com.metacoding.springrocketdanv1.resumeTechStack.ResumeTechStackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(SalaryRangeRepository.class)
@DataJpaTest
public class SalaryRangeRepositoryTest {
    @Autowired
    private SalaryRangeRepository salaryRangeRepository;
}
