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

    @GetMapping("/")
    public String list(Model model) {
        List<JobResponse.DTO> jobllist = jobService.글목록보기();
        model.addAttribute("model", jobllist);
        return "list";
    }
}
