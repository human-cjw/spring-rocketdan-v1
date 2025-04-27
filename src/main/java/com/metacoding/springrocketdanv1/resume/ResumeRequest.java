package com.metacoding.springrocketdanv1.resume;

import com.metacoding.springrocketdanv1.career.Career;
import com.metacoding.springrocketdanv1.certification.Certification;
import com.metacoding.springrocketdanv1.techStack.TechStack;
import lombok.Data;

import java.sql.Timestamp;
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
        private String name;
        private String email;
        private List<Certification> certifications; // 다른 테이블에서 가지고 온 것
        private List<TechStack> resumeTechStacks; // 다른 테이블에서 가지고 온 것 (유저가 갖고 있는거)
        private List<Career> careers;

    }
}
