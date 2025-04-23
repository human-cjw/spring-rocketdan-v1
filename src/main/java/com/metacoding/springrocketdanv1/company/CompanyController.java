package com.metacoding.springrocketdanv1.company;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/companies")
    public String list(HttpServletRequest request) {
        List<Company> companyList = companyService.기업리스트();
        request.setAttribute("companyList", companyList);
        return "company/list";
    }
}
