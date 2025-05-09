package com.metacoding.springrocketdanv1.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(ApplicationRepository.class)
@DataJpaTest
public class ApplicationRepositoryTest {
    @Autowired
    private ApplicationRepository applicationRepository;
}
