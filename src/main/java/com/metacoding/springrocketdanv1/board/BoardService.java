package com.metacoding.springrocketdanv1.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> 글목록보기() {
        List<Board> boardList = boardRepository.findAll(); // 바로 리파지토리 위임가능, 일관성
        return boardList;
    }
}
