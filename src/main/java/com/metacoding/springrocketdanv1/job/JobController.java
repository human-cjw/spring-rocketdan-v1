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
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/")
    public String list(Model models, JobResponse.DTO dto) {
        List<JobResponse.DTO> jobllist = jobService.글목록보기();
        models.addAttribute("models", jobllist);
        models.addAttribute("nameKr", dto.getNameKr());
        return "job/list";
    }

    @GetMapping("/job/{id}")
    public String show(@PathVariable Integer id, Model model) {
        // JobDetail을 조회
        JobResponse.DetailDTO jobDetail = jobService.글상세보기(id);

        // model에 데이터 추가
        model.addAttribute("jobDetail", jobDetail);
        model.addAttribute("nameKr", jobDetail.getNameKr());
        model.addAttribute("salaryRange", jobDetail.getSalaryRange());

        // job/detail 뷰 반환
        return "job/detail";
    }
}
