package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.certification.CertificationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Transactional
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void findById_test() {
        Integer id = 1;
        Company company = companyRepository.findById(id);
        if (company == null) {
            System.out.println("company is null!");
        } else {
            System.out.println("company name: " + company.getNameKr());
        }
    }
}
