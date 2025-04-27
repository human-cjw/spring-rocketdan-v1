package com.metacoding.springrocketdanv1.resume;

import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

@Import(ResumeRepository.class)
@DataJpaTest
public class ResumeRepositoryTest {
    @Autowired
    private ResumeRepository resumeRepository;

    @Test
    public void findById_test() {
        //given
        Integer user = 1;

        // when
        Resume resume = resumeRepository.findById(user);

        // eye
        System.out.println("출력 : " + resume);
    }

    @Test
    public void findAll_test() {


        List<Resume> resumes = resumeRepository.findAll();

        System.out.println("출력 : " + resumes);
    }
}


