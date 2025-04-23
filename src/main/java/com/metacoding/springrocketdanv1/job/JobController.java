package com.metacoding.springrocketdanv1.job;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

//    @GetMapping("/")
//    public String list(Model model) {
//        List<JobResponse.DTO> jobllist = jobService.글목록보기();
//        model.addAttribute("model", jobllist);
//        model.addAttribute("nameKr", "한글 이름");
//        return "job/list";
//    }

    @GetMapping("/")
    public String show(Model model) {
        List<JobResponse.DetailDTO> jobDetail = jobService.글상세보기();
        model.addAttribute("model", jobDetail);
        model.addAttribute("nameKr", "한글 이름");
        model.addAttribute("salaryRange", jobDetail.get(0).getSalaryRange());

        return "job/detail";
    }
}
