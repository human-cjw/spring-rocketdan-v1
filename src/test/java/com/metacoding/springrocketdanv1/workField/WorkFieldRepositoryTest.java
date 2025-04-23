package com.metacoding.springrocketdanv1.workField;

import com.metacoding.springrocketdanv1.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(WorkFieldRepository.class)
@DataJpaTest
public class WorkFieldRepositoryTest {
    @Autowired
    private WorkFieldRepository workFieldRepository;
}
