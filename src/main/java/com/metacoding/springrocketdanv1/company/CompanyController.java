package com.metacoding.springrocketdanv1.company;

import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.techStack.TechStackRepository;
import com.metacoding.springrocketdanv1.user.UserResponse;
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
    private final CompanyRepository companyRepository;


    @GetMapping("/company/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        CompanyResponse.CompanyResponseDTO responseDTO = companyService.기업상세(id);
        model.addAttribute("model", responseDTO);
        return "company/detail";
    }

    @GetMapping("/company")
    public String list(HttpServletRequest request, HttpSession session, Model model) {
        List<Company> companyList = companyService.기업리스트();
        request.setAttribute("models", companyList);

        UserResponse.SessionUserDTO sessionUser = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");
        if (sessionUser != null) {
            model.addAttribute("isCompany", "company".equals(sessionUser.getUserType()));
        } else {
            model.addAttribute("isCompany", false);
        }

        return "company/list";
    }

    @GetMapping("/company/save-form")
    public String saveForm(HttpSession session, Model model) {
        UserResponse.SessionUserDTO sessionUser = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            // 로그인 안했으면 로그인 페이지로 리다이렉트
            return "redirect:/login-form";
        }

        List<WorkField> workFields = workFieldRepository.findAll();
        List<TechStack> techStacks = techStackRepository.findAll();

        model.addAttribute("workFields", workFields);
        model.addAttribute("techStacks", techStacks);

        return "company/save-form";
    }

    @PostMapping("/company/save")
    public String save(@ModelAttribute CompanyRequest.CompanySaveDTO requestDTO, HttpSession session) {
        UserResponse.SessionUserDTO sessionUser = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");
        Company savedCompany = companyService.기업등록(requestDTO, sessionUser);
        return "redirect:/company/" + savedCompany.getId();
    }

    @GetMapping("/company/update-form")
    public String updateForm(HttpSession session, Model model) {
        // 1. 세션에서 로그인한 사용자 꺼내기
        UserResponse.SessionUserDTO sessionUser = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            throw new RuntimeException("로그인이 필요합니다."); // 세션 없으면 막기
        }

        // 2. 세션에 유저 저장
        session.setAttribute("sessionUser", sessionUser);

        // 3. 유저 ID를 이용해서 내 기업 정보 조회
        CompanyResponse.UpdateFormDTO dto = companyService.내기업조회(sessionUser.getId());

        model.addAttribute("model", dto);
        return "company/update-form";
    }


    @PostMapping("/company/update")
    public String update(@ModelAttribute CompanyRequest.UpdateDTO requestDTO, HttpSession session) {

        UserResponse.SessionUserDTO sessionUser = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");

        companyService.기업수정(requestDTO);

        return "redirect:/company/" + requestDTO.getId();
    }
}
