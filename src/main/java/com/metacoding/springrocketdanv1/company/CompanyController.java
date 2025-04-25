package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.techStack.TechStackRepository;
import com.metacoding.springrocketdanv1.user.User;
import com.metacoding.springrocketdanv1.workField.WorkField;
import com.metacoding.springrocketdanv1.workField.WorkFieldRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final WorkFieldRepository workFieldRepository;
    private final TechStackRepository techStackRepository;

    @GetMapping("/company/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
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

    @GetMapping("/company/save-form")
    public String saveForm(Model model) {
        List<WorkField> workFields = workFieldRepository.findAll();
        List<TechStack> techStacks = techStackRepository.findAll();

        model.addAttribute("workFields", workFields);
        model.addAttribute("techStacks", techStacks);

        return "company/save-form";
    }

    @PostMapping("/company/save")
    public String save(@ModelAttribute CompanyRequest.CompanySaveDTO requestDTO, HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Company savedCompany = companyService.기업등록(requestDTO, sessionUser);
        return "redirect:/company/" + savedCompany.getId();
    }
}
