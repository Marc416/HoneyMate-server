CREATE TABLE honey_mate_dev.chatting_room
(
    id             BIGINT       NOT NULL AUTO_INCREMENT primary key comment 'id',
    study_key      VARCHAR(100) NOT NULL unique comment '스터디 키',
    title          VARCHAR(100) NOT NULL comment '채팅룸 제목',
    preview_image  VARCHAR(100) NOT NULL comment '이름',
    start_at       TIMESTAMP    NOT NULL comment '채팅룸 오픈시간',
    attendee_limit INT          NOT NULL comment '채팅룸 제한 인원',
    created_at     TIMESTAMP    NOT NULL comment '생성 시간',
    updated_at     TIMESTAMP    NOT NULL comment '수정 시간',
    deleted_at     TIMESTAMP    NULL comment '삭제 시간'
);

CREATE TABLE honey_mate_dev.attendee
(
    account_key      binary(16)   NOT NULL primary key comment 'Account 키',
    chatting_room_id BIGINT       NULL comment '채팅룸 아이디',
    name             VARCHAR(100) NOT NULL comment 'Account 이름',
    email            VARCHAR(100) NOT NULL comment 'Account 이메일',
    profile_image    VARCHAR(255) NOT NULL comment '프로필 이미지',
    created_at       TIMESTAMP    NOT NULL comment '생성 시간',
    updated_at       TIMESTAMP    NOT NULL comment '수정 시간'
);