package com.metacoding.springrocketdanv1.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board")
    public String list(Model model) {
        List<BoardResponse.BoardDTO> boardDTOList = boardService.글목록보기();
        model.addAttribute("model", boardDTOList);
        return "board/list2";
    }

    @PostMapping("/board/save")
    public String writeBoard(@ModelAttribute BoardRequest.saveDTO board) {
        boardService.글쓰기(board);
        return "redirect:/board";
    }

    @GetMapping("/board/update-form")
    public String updateForm() { // form 에서 boardId를 가져와야 함, password 값을 가져와서 해당 보드의 비번과 일치하는지 비교해야함(서비스에서)
        //boardService.글수정하기(reqDTO, id)
        return "board/update-form";
    }

    @PostMapping("/board/update")
    public String update() { // <- form 에서 boardId와 title, content 가져와야함
        return "redirect:/board";
    }

    @PostMapping("/board/delete")
    public String delete() { // boardId를 가져와서 삭제
        return "redirect:/board";
    }

}
