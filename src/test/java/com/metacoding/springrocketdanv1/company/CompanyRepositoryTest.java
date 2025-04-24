package com.metacoding.springrocketdanv1.company;

<<<<<<< HEAD
import com.metacoding.springrocketdanv1.certification.CertificationRepository;
import jakarta.transaction.Transactional;
=======
>>>>>>> master
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

<<<<<<< HEAD
@SpringBootTest
@Transactional
=======
import java.util.List;

@Import(CompanyRepository.class)
@DataJpaTest
>>>>>>> master
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
<<<<<<< HEAD
    public void findById_test() {
        Integer id = 1;
        Company company = companyRepository.findById(id);
        if (company == null) {
            System.out.println("company is null!");
        } else {
            System.out.println("company name: " + company.getNameKr());
=======
    public void findAll_test() {
        List<Company> companyList = companyRepository.findAll();

        for (Company company : companyList) {
            System.out.print(company.getId() + ", ");
            System.out.print(company.getNameKr() + ", ");
            System.out.print(company.getUser().getUsername());
            System.out.println();
>>>>>>> master
        }
    }
}
