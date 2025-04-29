package com.metacoding.springrocketdanv1.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

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


}
