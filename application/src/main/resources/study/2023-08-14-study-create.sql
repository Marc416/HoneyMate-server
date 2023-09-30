CREATE DATABASE honey_mate_dev char set utf8mb4 collate utf8mb4_unicode_ci;

CREATE TABLE honey_mate_dev.study
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    entity_key         binary(16)   NOT NULL unique,
    title              VARCHAR(100) NOT NULL,
    subtitle           VARCHAR(255),
    description        TEXT,
    location           VARCHAR(100) NOT NULL,
    running_time       VARCHAR(50)  NOT NULL,
    running_period     VARCHAR(50)  NOT NULL,
    number_of_recruits INTEGER      NOT NULL,
    created_at         TIMESTAMP    NOT NULL,
    modified_at        TIMESTAMP    NOT NULL
);


create table honey_mate_dev.study_code
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    study_id     bigint       null,
    display_name varchar(255) null,
    expired_at   datetime     null,
    created_at   datetime     null,
    deleted_at   datetime     null,
    constraint FK_STUDYCODE_ON_STUDY
        foreign key (study_id) references study (id)
);