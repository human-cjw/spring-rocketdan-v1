package com.metacoding.springrocketdanv1.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
