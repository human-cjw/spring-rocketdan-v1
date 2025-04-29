package com.metacoding.springrocketdanv1.resume;

import lombok.Data;

import java.util.List;

public class ResumeRequest {

    @Data
    public static class UpdateDTO {
        private String title; // 제목
        private String summary; // 자기소개
        private String portfolioUrl; // 포트폴리오 주소
        private String gender; // 성별
        private String education; // 학력사항
        private String birthdate; // 생년월일
        private String major; // 전공
        private String graduationType; // 졸업, 재학, 휴학
        private String phone; // 연락처
        private String enrollmentDate; // 입학날짜
        private String graduationDate; // 졸업날짜
        private String careerLevel;
        private List<CertificationDTO> certifications;
        private List<Integer> techStacks;
        private List<CareerDTO> careers;
        private Boolean isDefault;
        private List<UserDTO> users;

        @Data
        public static class CertificationDTO {
            private Integer id;
            private String name; // 자격증이름
            private String issuer; // 자격증발급기관
            private String issuedDate; // 발급 날짜
        }

        @Data
        public static class CareerDTO {
            private Integer id;
            private String companyName; // 이전에 다녔던 기업이름
            private String startDate; // 시작일
            private String endDate; // 종료일
        }

        @Data
        public static class ResumeTechStackDTO {
            private Integer techStackId;
        }

        @Data
        public static class UserDTO {
            private String email;
        }
    }


}
