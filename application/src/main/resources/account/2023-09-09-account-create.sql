CREATE TABLE account
(
    id            BIGINT       NOT NULL AUTO_INCREMENT unique comment 'index',
    account_key   binary(16)   NOT NULL primary key comment 'UUID 키',
    email         VARCHAR(100) NOT NULL unique comment 'primary 이메일',
    name          VARCHAR(100) NOT NULL comment '이름',
    profile_image VARCHAR(255) NOT NULL comment '프로필 이미지',
    created_at    TIMESTAMP    NOT NULL comment '생성 시간',
    modified_at   TIMESTAMP    NOT NULL comment '수정 시간',
    deleted_at    TIMESTAMP    NULL comment '삭제 시간'
);