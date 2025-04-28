package com.metacoding.springrocketdanv1.board;

public class BoardResponse {
    public static class BoardDTO {
        private String title;
        private String content;

        public BoardDTO(Board board) {
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }

}
