package com.metacoding.springrocketdanv1.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/companies/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        CompanyResponse.CompanyResponseDTO responseDTO = companyService.기업상세(id);
        model.addAttribute("company", responseDTO);
        return "company/detail";
    }
}
