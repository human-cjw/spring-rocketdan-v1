package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/company")
    public String list(HttpServletRequest request) {
        List<Company> companyList = companyService.기업리스트();
        request.setAttribute("models", companyList);
        return "company/list";
    }

    @GetMapping("/company/save-form")
    public String saveForm() {
        return "company/save-form";
    }
    
    @PostMapping("/company/save")
    public String save(@ModelAttribute CompanyRequest.CompanySaveDTO requestDTO, HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        companyService.기업등록(requestDTO, sessionUser);

        return "redirect:/company";
    }
}
