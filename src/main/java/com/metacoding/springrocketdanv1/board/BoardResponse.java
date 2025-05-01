package com.metacoding.springrocketdanv1.board;

import lombok.Data;

public class BoardResponse {
    @Data
    public static class BoardDTO {
        private int boardId;
        private String content;
        private String title;

        public BoardDTO(Board board) {
            this.boardId = board.getId();
            this.content = board.getContent();
            this.title = board.getTitle();
        }


    }
}
