-- =============================================
-- WorkField 더미 데이터
-- =============================================
INSERT INTO work_field_tb (name)
VALUES ('IT/소프트웨어'),
       ('금융/보험'),
       ('판매/유통'),
       ('제조/생산'),
       ('의료/제약'),
       ('교육'),
       ('미디어/엔터테인먼트'),
       ('서비스'),
       ('건설/부동산'),
       ('공공/사회기관');

-- =============================================
-- User 더미 데이터
-- =============================================
INSERT INTO user_tb (username,
                     password,
                     email,
                     file_url,
                     user_type,
                     created_at)
VALUES ('ssar', '1234', 'ssar@example.com', null, 'user', NOW()),
       ('cos', '1234', 'cos@example.com', null, 'user', NOW()),
       ('love', '1234', 'love@example.com', null, 'company', NOW()),
       ('haha', '1234', 'haha@example.com', null, 'company', NOW());

-- =============================================
-- Company 더미 데이터
-- =============================================
INSERT INTO company_tb (name_kr,
                        name_en,
                        ceo,
                        business_number,
                        email,
                        phone,
                        address,
                        introduction,
                        one_line_intro,
                        homepage_url,
                        logo_image_url,
                        info_image_url,
                        contact_manager,
                        created_at,
                        user_id,
                        work_field_id,
                        start_date)
VALUES ('네이버',
        'Naver',
        '이해진',
        '1234567890',
        'naver@naver.com',
        '02-1234-5678',
        '경기도 성남시 분당구',
        '네이버는 대한민국 최대 검색 포털입니다. 다양한 서비스를 통해 사용자들에게 가치를 제공하고 있습니다.',
        '네이버 - 대한민국 대표 포털',
        'https://www.naver.com',
        null,
        null,
        '김담당',
        NOW(),
        3,
        1,
        '2000-01-01'),
       ('카카오',
        'Kakao',
        '홍은택',
        '0987654321',
        'kakao@kakao.com',
        '02-8765-4321',
        '제주특별자치도 제주시',
        '카카오는 대한민국 대표 IT 기업입니다. 메신저, 게임, 금융 등 다양한 서비스를 제공하고 있습니다.',
        '카카오 - 대한민국 대표 IT 기업',
        'https://www.kakao.com',
        null,
        null,
        '이담당',
        NOW(),
        4,
        1,
        '2005-10-15');

-- =============================================
-- SalaryRange 더미 데이터
-- =============================================
INSERT INTO salary_range_tb (min_salary,
                             max_salary,
                             label)
VALUES (3000, 4000, '3000-4000'),
       (4000, 5000, '4000-5000'),
       (5000, 6000, '5000-6000'),
       (6000, 7000, '6000-7000'),
       (7000, 8000, '7000-8000');

-- =============================================
-- JobGroup 더미 데이터
-- =============================================
INSERT INTO job_group_tb (name)
VALUES ('백엔드 개발자'),
       ('프론트엔드 개발자'),
       ('풀스택 개발자'),
       ('데이터 엔지니어'),
       ('DevOps 엔지니어');

-- =============================================
-- TechStack 더미 데이터
-- =============================================
INSERT INTO tech_stack_tb (name)
VALUES
    -- 백엔드
    ('Java'),
    ('Spring Boot'),
    ('Spring Security'),
    ('JPA'),
    ('MySQL'),
    ('PostgreSQL'),
    ('Redis'),
    ('MongoDB'),
    -- 프론트엔드
    ('JavaScript'),
    ('TypeScript'),
    ('React'),
    ('Vue.js'),
    ('Next.js'),
    ('HTML/CSS'),
    -- DevOps
    ('Docker'),
    ('Kubernetes'),
    ('AWS'),
    ('GCP'),
    ('Jenkins'),
    -- 데이터
    ('Python'),
    ('TensorFlow'),
    ('PyTorch'),
    ('Hadoop'),
    ('Spark');

-- =============================================
-- Resume 더미 데이터
-- =============================================
INSERT INTO resume_tb (title,
                       summary,
                       gender,
                       career_level,
                       education,
                       birthdate,
                       major,
                       graduation_type,
                       phone,
                       portfolio_url,
                       created_at,
                       user_id,
                       salary_range_id,
                       job_group_id,
                       enrollment_date,
                       graduation_date,
                       is_default)
VALUES ('백엔드 개발자 이력서',
        'Spring Boot와 Java를 주로 사용하는 백엔드 개발자입니다. RESTful API 설계와 구현에 능숙하며, 데이터베이스 설계와 최적화에도 관심이 많습니다.',
        '남',
        '경력',
        '부산대학교',
        '1995-01-01',
        '컴퓨터공학',
        '졸업',
        '010-1234-5678',
        'https://github.com/ssar/portfolio',
        NOW(),
        1,
        3,
        1,
        '2020-03-01',
        '2024-02-01',
        true),
       ('프론트엔드 개발자 이력서',
        'React와 TypeScript를 주로 사용하는 프론트엔드 개발자입니다. 사용자 경험을 중시하며, 웹 접근성과 반응형 디자인에 관심이 많습니다.',
        '여',
        '신입',
        '서울대학교',
        '1996-02-02',
        '소프트웨어공학',
        '졸업',
        '010-2345-6789',
        'https://github.com/cos/portfolio',
        NOW(),
        2,
        2,
        2,
        '2014-03-01',
        '2020-02-01',
        true);

-- =============================================
-- Job 더미 데이터
-- =============================================
INSERT INTO job_tb (title,
                    description,
                    location,
                    employment_type,
                    deadline,
                    status,
                    career_level,
                    created_at,
                    company_id,
                    salary_range_id,
                    work_field_id)
VALUES ('Spring Boot 기반 백엔드 엔지니어',
        'Spring Boot와 Java를 사용한 백엔드 개발자 모집합니다. RESTful API 설계와 구현 경험이 필요합니다.',
        '경기도 성남시 분당구',
        '정규직',
        '2025-10-10',
        'OPEN',
        '경력',
        NOW(),
        1,
        3,
        1),
       ('React & TypeScript 프론트엔드 엔지니어',
        'React와 TypeScript를 사용한 프론트엔드 개발자 모집합니다. 웹 접근성과 반응형 디자인 경험이 필요합니다.',
        '제주특별자치도 제주시',
        '정규직',
        '2025-05-25',
        'OPEN',
        '경력',
        NOW(),
        2,
        2,
        1),
       ('모바일 중심 소프트웨어 엔지니어 (신입 가능)',
        '모바일 앱 개발 경험이 있는 소프트웨어 엔지니어를 모집합니다.',
        '경기도 수원시',
        '정규직',
        '2025-06-03',
        'OPEN',
        '신입',
        NOW(),
        1,
        4,
        4);

-- =============================================
-- ResumeTechStack 더미 데이터
-- =============================================
INSERT INTO resume_tech_stack_tb (resume_id, tech_stack_id)
VALUES
    -- ssar의 백엔드 개발자 이력서 기술 스택
    (1, 1),  -- Java
    (1, 2),  -- Spring Boot
    (1, 3),  -- Spring Security
    (1, 4),  -- JPA
    (1, 5),  -- MySQL
    (1, 16), -- Docker
    (1, 17), -- Kubernetes
    (1, 18), -- AWS

    -- cos의 프론트엔드 개발자 이력서 기술 스택
    (2, 9),  -- JavaScript
    (2, 10), -- TypeScript
    (2, 11), -- React
    (2, 13), -- Next.js
    (2, 14), -- HTML/CSS
    (2, 16), -- Docker
    (2, 18);
-- AWS

-- =============================================
-- CompanyTechStack 더미 데이터
-- =============================================
INSERT INTO company_tech_stack_tb (company_id, tech_stack_id)
VALUES
    -- 네이버(1번 회사)의 주요 기술 스택
    (1, 1),  -- Java
    (1, 2),  -- Spring Boot
    (1, 4),  -- JPA
    (1, 5),  -- MySQL
    (1, 16), -- Docker
    (1, 18), -- AWS
    (1, 17), -- Kubernetes
    (1, 22), -- Hadoop
    (1, 23), -- Spark

    -- 카카오(2번 회사)의 주요 기술 스택
    (2, 9),  -- JavaScript
    (2, 10), -- TypeScript
    (2, 11), -- React
    (2, 13), -- Next.js
    (2, 14), -- HTML/CSS
    (2, 16), -- Docker
    (2, 18), -- AWS
    (2, 1),  -- Java
    (2, 2),  -- Spring Boot
    (2, 5);
-- MySQL

-- =============================================
-- JobTechStack 더미 데이터
-- =============================================
INSERT INTO job_tech_stack_tb (job_id, tech_stack_id)
VALUES
    -- 1번 공고(백엔드 개발자) 기술스택
    (1, 1),  -- Java
    (1, 2),  -- Spring Boot
    (1, 4),  -- JPA
    (1, 5),  -- MySQL
    (1, 16), -- Docker
    (1, 18), -- AWS

    -- 2번 공고(프론트엔드 개발자) 기술스택
    (2, 9),  -- JavaScript
    (2, 10), -- TypeScript
    (2, 11), -- React
    (2, 13), -- Next.js
    (2, 14), -- HTML/CSS
    (2, 16), -- Docker
    (2, 18), -- AWS

    -- 3번 공고(DevOps 엔지니어) 기술스택
    (3, 16), -- Docker
    (3, 17), -- Kubernetes
    (3, 18), -- AWS
    (3, 19), -- Terraform (예시)
    (3, 20);
-- Jenkins (예시)

-- =============================================
-- Career 더미 데이터
-- =============================================
INSERT INTO career_tb (company_name,
                       start_date,
                       end_date,
                       created_at,
                       job_group_id,
                       resume_id)
VALUES ('네이버',
        '2020-01-01',
        '2022-01-01',
        NOW(),
        1, -- 백엔드 개발자
        1 -- ssar의 백엔드 개발자 이력서
       ),
       ('카카오',
        '2022-02-01',
        '2023-02-01',
        NOW(),
        1, -- 백엔드 개발자
        1 -- ssar의 백엔드 개발자 이력서
       ),
       ('네이버',
        '2020-01-01',
        '2022-01-01',
        NOW(),
        2, -- 프론트엔드 개발자
        2 -- cos의 프론트엔드 개발자 이력서
       ),
       ('카카오',
        '2022-02-01',
        '2023-02-01',
        NOW(),
        2, -- 프론트엔드 개발자
        2 -- cos의 프론트엔드 개발자 이력서
       );

-- =============================================
-- Certification 더미 데이터
-- =============================================
INSERT INTO certification_tb (name,
                              issuer,
                              issued_date,
                              created_at,
                              resume_id)
VALUES ('정보처리기사',
        '한국산업인력공단',
        '2020-01-01',
        NOW(),
        1 -- ssar의 백엔드 개발자 이력서
       ),
       ('SQLD',
        '한국데이터산업진흥원',
        '2020-02-01',
        NOW(),
        1 -- ssar의 백엔드 개발자 이력서
       ),
       ('AWS Certified Solutions Architect',
        'Amazon Web Services',
        '2020-03-01',
        NOW(),
        1 -- ssar의 백엔드 개발자 이력서
       ),
       ('정보처리기사',
        '한국산업인력공단',
        '2020-01-01',
        NOW(),
        2 -- cos의 프론트엔드 개발자 이력서
       ),
       ('웹디자인기능사',
        '한국산업인력공단',
        '2020-02-01',
        NOW(),
        2 -- cos의 프론트엔드 개발자 이력서
       );

-- =============================================
-- Board 더미 데이터
-- =============================================
INSERT INTO board_tb (title, content, created_at, user_id)
VALUES ('첫 번째 게시글', '이것은 첫 번째 게시글의 내용입니다.', NOW(), 1),
       ('스프링 프로젝트 질문', '스프링 관련 질문이 있습니다. 답변 부탁드려요!', NOW(), 2),
       ('부산 개발자 모임 안내', '부산에서 열리는 개발자 모임에 초대합니다.', NOW(), 3),
       ('자바 공부 방법', '효과적인 자바 공부 방법을 공유합니다.', NOW(), 1),
       ('프로젝트 팀원 모집', '함께 프로젝트할 팀원을 모집합니다.', NOW(), 2);

-- =============================================
-- Love 더미 데이터 (작성자 본인 제외)
-- =============================================
INSERT INTO love_tb (user_id, board_id)
VALUES (2, 1), -- 2번 유저가 1번 게시글(작성자 1) 좋아요
       (1, 3), -- 1번 유저가 3번 게시글(작성자 3) 좋아요
       (2, 3);
-- 2번 유저가 3번 게시글(작성자 3) 좋아요

-- =============================================
-- Reply 더미 데이터
-- =============================================
INSERT INTO reply_tb (content, created_at, user_id, board_id)
VALUES ('첫 번째 게시글 감사합니다!', NOW(), 2, 1), -- 2번 유저가 1번 게시글(작성자 1)에 댓글
       ('좋은 정보네요!', NOW(), 3, 1),        -- 3번 유저가 1번 게시글(작성자 1)에 댓글
       ('저도 궁금했는데 답변 기다립니다.', NOW(), 1, 2);
-- 1번 유저가 2번 게시글(작성자 2)에 댓글

-- =============================================
-- JobBookmark 더미 데이터
-- =============================================
INSERT INTO job_bookmark_tb (user_id, job_id)
VALUES
    -- ssar(1번 유저)의 북마크
    (1, 1), -- 백엔드 개발자(1번 공고)
    (1, 2), -- 프론트엔드 개발자(2번 공고)

    -- cos(2번 유저)의 북마크
    (2, 2), -- 프론트엔드 개발자(2번 공고)
    (2, 3);
-- 소프트웨어 엔지니어(3번 공고)

-- =============================================
-- ResumeBookmark 더미 데이터
-- =============================================
INSERT INTO resume_bookmark_tb (resume_id, company_id)
VALUES
    -- 네이버(1번 회사)가 ssar(1번 유저)의 백엔드 개발자 이력서를 북마크
    (1, 1),
    -- 카카오(2번 회사)가 cos(2번 유저)의 프론트엔드 개발자 이력서를 북마크
    (2, 2),
    -- 삼성전자(3번 회사)가 ssar(1번 유저)의 백엔드 개발자 이력서를 북마크
    (2, 1);

-- =============================================
-- Application 더미 데이터
-- =============================================
INSERT INTO application_tb (id, status, user_id, company_id, resume_id, job_id, created_at)
VALUES (1, '접수', 1, 1, 1, 1, NOW()),
       (2, '검토', 2, 2, 2, 2, NOW());