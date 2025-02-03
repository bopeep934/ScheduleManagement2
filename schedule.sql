use schedule;

CREATE TABLE schedule
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 식별자',
    writer_id         VARCHAR(100) NOT NULL COMMENT '작성자 구별 식별자',
    writer_name       VARCHAR(100) NOT NULL COMMENT '작성자명',
    password          VARCHAR(100) NOT NULL COMMENT '비밀번호',
    registration_date DATETIME     NOT NULL COMMENT '작성일',
    modification_date DATETIME     NOT NULL COMMENT '수정일',
    todo              text comment '할 일 '
);

CREATE INDEX idx_writer_id ON schedule (writer_id);


CREATE TABLE writer
(
    writer_id         VARCHAR(100) PRIMARY KEY UNIQUE COMMENT '작성자 고유 식별자',
    name              VARCHAR(100) NOT NULL COMMENT '이름',
    email             VARCHAR(100) NOT NULL COMMENT '이메일',
    registration_date DATETIME     NOT NULL COMMENT '등록일',
    modification_date DATETIME     NOT NULL COMMENT '수정일',
    FOREIGN KEY (writer_id) REFERENCES schedule (writer_id)
);
