package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.board.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    private final BoardService boardService;

    @GetMapping("/")
    public String list(HttpServletRequest request) {
        request.setAttribute("model", boardService.글목록보기(null, page));
        return "list";
    }
}
