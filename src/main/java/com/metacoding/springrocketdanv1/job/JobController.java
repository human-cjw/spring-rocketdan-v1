package com.metacoding.springrocketdanv1.job;


import com.metacoding.springrocketdanv1.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    private final HttpSession session;

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

    @GetMapping("/job/save-form")
    public String saveForm(HttpServletRequest request) {
        JobResponse.JobSaveDTO respDTO = jobService.등록보기();
        request.setAttribute("model", respDTO);
        return "job/save-form";
    }

    @PostMapping("/job/save")
    public String save(JobRequest.JobSaveDTO reqDTO) {
        UserResponse.SessionUserDTO sessionUserDTO = (UserResponse.SessionUserDTO) session.getAttribute("sessionUser");
        jobService.등록하기(reqDTO, sessionUserDTO.getCompanyId());

        return "redirect:/"; // -> 나중에 공고 관리 페이지로 이동
    }

    @GetMapping("/job/{jobId}/update-form")
    public String updateForm(@PathVariable("jobId") Integer jobId,
                             HttpServletRequest request) {
        JobResponse.JobUpdateDTO respDTO = jobService.수정보기(jobId);
        request.setAttribute("model", respDTO);
        return "job/update-form";
    }

    @PostMapping("/job/{jobId}/update")
    public String update(@PathVariable("jobId") Integer jobId,
                         JobRequest.JobUpdateDTO reqDTO) {
        jobService.수정하기(jobId, reqDTO);
        return "redirect:/job/" + jobId;
    }
}