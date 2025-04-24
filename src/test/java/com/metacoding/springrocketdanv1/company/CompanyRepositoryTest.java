package com.metacoding.springrocketdanv1.company;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(CompanyRepository.class)
@DataJpaTest
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void findAll_test() {
        List<Company> companyList = companyRepository.findAll();

        for (Company company : companyList) {
            System.out.print(company.getId() + ", ");
            System.out.print(company.getNameKr() + ", ");
            System.out.print(company.getUser().getUsername());
            System.out.println();
        }
    }
}
