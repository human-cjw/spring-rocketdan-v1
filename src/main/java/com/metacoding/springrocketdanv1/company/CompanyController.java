package com.metacoding.springrocketdanv1.company;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;


    @GetMapping("/company/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        CompanyResponse.CompanyResponseDTO responseDTO = companyService.기업상세(id);
        model.addAttribute("model", responseDTO);
        return "company/detail";


    }

    @GetMapping("/company")
    public String list(HttpServletRequest request) {
        List<Company> companyList = companyService.기업리스트();
        request.setAttribute("models", companyList);
        return "company/list";
    }

}
