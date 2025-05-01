package com.metacoding.springrocketdanv1.board;

import lombok.Data;

public class BoardRequest {
    @Data

    public static class saveDTO {
        private String title;
        private String content;
        private String password;

    }

    @Data
    public static class updateDTO {
        private String title;
        private String content;
    }
}
