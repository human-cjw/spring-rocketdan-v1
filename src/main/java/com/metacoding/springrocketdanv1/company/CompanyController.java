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

import java.lang.reflect.Field;
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
    public String list(HttpServletRequest request) {
        List<Company> companyList = companyService.기업리스트();
        request.setAttribute("models", companyList);
        return "company/list";
    }

    @GetMapping("/company/save-form")
    public String saveForm(HttpSession session, Model model) {
        // 세션에 로그인 유저가 없으면 임시로 세팅해준다
        if (session.getAttribute("sessionUser") == null) {
            User testUser = new User();
            try {
                Field field = User.class.getDeclaredField("id");
                field.setAccessible(true);
                field.set(testUser, 2); // 존재하는 유저 ID
            } catch (Exception e) {
                throw new RuntimeException("테스트 유저 생성 실패", e);
            }
            session.setAttribute("sessionUser", testUser);
        }

        List<WorkField> workFields = workFieldRepository.findAll();
        List<TechStack> techStacks = techStackRepository.findAll();

        model.addAttribute("workFields", workFields);
        model.addAttribute("techStacks", techStacks);

        return "company/save-form";
    }

//    SELECT * FROM user_tb WHERE id = 2;
//    @GetMapping("/company/save-form")
//    public String saveForm(HttpSession session, Model model) {
//        List<WorkField> workFields = workFieldRepository.findAll();
//        List<TechStack> techStacks = techStackRepository.findAll();
//
//        model.addAttribute("workFields", workFields);
//        model.addAttribute("techStacks", techStacks);
//
//        return "company/save-form";
//    }

    @PostMapping("/company/save")
    public String save(@ModelAttribute CompanyRequest.CompanySaveDTO requestDTO, HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Company savedCompany = companyService.기업등록(requestDTO, sessionUser);
        return "redirect:/company/" + savedCompany.getId();
    }

    @GetMapping("/company/update-form")
    public String updateForm(HttpSession session, Model model) {
        // 2번 유저로 임시 세션 생성
        User sessionUser = new User();
        try {
            Field field = User.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(sessionUser, 2); // 실제 존재하는 유저 ID (예: love)
        } catch (Exception e) {
            throw new RuntimeException("User ID 세팅 실패", e);
        }

        // 세션에 유저 저장
        session.setAttribute("sessionUser", sessionUser);

        // 회사 ID가 아닌 유저 ID로 내 기업 정보 조회
        CompanyResponse.UpdateFormDTO dto = companyService.내기업조회(sessionUser.getId());

        model.addAttribute("model", dto);
        return "company/update-form";
    }

//    @GetMapping("/company/update-form")
//    public String updateForm(HttpSession session, Model model) {
//        // 1. 세션에서 로그인한 사용자 꺼내기
//        User sessionUser = (User) session.getAttribute("sessionUser");
//
//        if (sessionUser == null) {
//            throw new RuntimeException("로그인이 필요합니다."); // 세션 없으면 막기
//        }
//
//        // 2. 유저 ID를 이용해서 내 기업 정보 조회
//        CompanyResponse.UpdateFormDTO dto = companyService.내기업조회(sessionUser.getId());
//
//        model.addAttribute("model", dto);
//        return "company/update-form";
//    }


    @PostMapping("/company/update")
    public String update(@ModelAttribute CompanyRequest.UpdateDTO requestDTO, HttpSession session) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        companyService.기업수정(requestDTO);

        return "redirect:/company/" + requestDTO.getId();
    }
}
