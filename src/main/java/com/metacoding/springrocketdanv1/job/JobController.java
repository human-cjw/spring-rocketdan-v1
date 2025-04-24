package com.metacoding.springrocketdanv1.job;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

//    @GetMapping("/job")
//    public String list(Model model) {
//        List<JobResponse.DTO> jobllist = jobService.글목록보기();
//        model.addAttribute("model", jobllist);
//        model.addAttribute("nameKr", "한글 이름");
//        return "job/list";
//    }

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
