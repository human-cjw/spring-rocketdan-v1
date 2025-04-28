package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.salaryRange.SalaryRange;
import com.metacoding.springrocketdanv1.salaryRange.SalaryRangeRepository;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import com.metacoding.springrocketdanv1.techStack.TechStackRepository;
import com.metacoding.springrocketdanv1.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    private final SalaryRangeRepository salaryRangeRepository;
    private final TechStackRepository techStackRepository;


    @GetMapping("/")
    public String saveForm(Model model) {
        List<SalaryRange> salaryRanges = salaryRangeRepository.findAll();
        model.addAttribute("salaryRanges", salaryRanges);

        List<TechStack> techStacks = techStackRepository.findAll();
        model.addAttribute("techStacks", techStacks);

        return "job/save-form";
    }

    @PostMapping("/job/save")
    public String save(@ModelAttribute JobRequest.JobSaveDTO requestDTO, HttpSession session) {
        System.out.println("제출된 데이터: " + requestDTO);
        User sessionUser = (User) session.getAttribute("sessionUser");
        Job savedJob = jobService.공고등록(requestDTO, sessionUser);
        return "redirect:/job/" + savedJob.getId();
    }

}
