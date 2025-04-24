package com.metacoding.springrocketdanv1.job;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;


    @GetMapping("/job/save-form")
    public String saveForm(Model model) {
        return "job/save-form"; // templates/job/save-form.mustache
    }

    @PostMapping("/job/save")
    public String save() {
        return "redirect:/";
    }


}
