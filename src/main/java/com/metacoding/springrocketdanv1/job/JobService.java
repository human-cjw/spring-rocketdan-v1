package com.metacoding.springrocketdanv1.job;

import com.metacoding.springrocketdanv1.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final BoardRepository boardRepository;

    public void 글목록보기(Integer userId) {
        List<Board> boards = boardRepository.findAll(page);
        return new BoardResponse.DTO;
    }
}
