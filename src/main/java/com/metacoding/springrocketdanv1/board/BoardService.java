package com.metacoding.springrocketdanv1.board;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public ResponseEntity<Map<String, String>> verifyPassword(@PathVariable Integer id, @RequestParam String password) {
        // 게시물 조회
        Board board = boardRepository.findById(id);

        Map<String, String> response = new HashMap<>();

        // 비밀번호 비교 (암호화 되어있다면 암호화 비교 필요)
        if (board.getPassword().equals(password)) {
            response.put("status", "success");
            response.put("message", "비밀번호가 맞습니다.");
        } else {
            response.put("status", "error");
            response.put("message", "비밀번호가 틀렸습니다.");
        }

        return ResponseEntity.ok(response);
    }

    public List<BoardResponse.BoardDTO> 글목록보기() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardResponse.BoardDTO> dtoList = new ArrayList<>();

        for (Board board : boardList) {
            BoardResponse.BoardDTO dto = new BoardResponse.BoardDTO(board);
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Transactional
    public void 글쓰기(BoardRequest.saveDTO boardDTO) {
        // BoardRequest.saveDTO 객체를 Board 엔티티 객체로 변환
        Board board = new Board(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getPassword());

        // Board 객체를 레파지토리로 저장
        boardRepository.save(board);
    }

    @Transactional
    public boolean 글수정하기(BoardRequest.updateDTO reqDTO, Integer boardId) {
        Board boardPS = boardRepository.findById(boardId);

        boardPS.update(reqDTO.getTitle(), reqDTO.getContent());
        return true;
    }


    public Board 업데이트글보기(int id) {
        Board boardPS = boardRepository.findById(id);
        return boardPS;
    }


    @Transactional
    public void 글삭제하기(Long id) {
        boardRepository.deleteById(id);
    }
}
