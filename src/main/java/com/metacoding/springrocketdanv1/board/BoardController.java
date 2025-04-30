package com.metacoding.springrocketdanv1.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

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

    @PostMapping("/board/update/{id}")
    public String verifyPassword(@PathVariable Integer id, @RequestParam String password) {
        // 게시물 조회
        Board board = boardRepository.findById(id);

        // 비밀번호 비교 (암호화 되어있다면 암호화 비교 필요)
        if (board.getPassword().equals(password)) {
            return "redirect:/board/update-form/" + id; // 수정 페이지로 리다이렉트
        } else {

            return "redirect:/board"; // 비밀번호 입력 페이지로 돌아가기
        }
    }

    @GetMapping("/board/update-form/{id}")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) { // form 에서 boardId를 가져와야 함, password 값을 가져와서 해당 보드의 비번과 일치하는지 비교해야함(서비스에서)
        Board board = boardService.업데이트글보기(id);
        request.setAttribute("model", board);
        return "board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") Integer id, BoardRequest.updateDTO reqDTO) { // <- form 에서 boardId와 title, content 가져와야함
        boardService.글수정하기(reqDTO, id);

        return "redirect:/board";
    }

    @PostMapping("/board/delete")
    public String delete(@RequestParam("id") Long id) { // boardId를 가져와서 삭제
        boardService.글삭제하기(id);
        return "redirect:/board";
    }

}
