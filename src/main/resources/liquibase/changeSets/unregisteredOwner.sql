-- liquibase formatted sql


-- changeset zaitcev:7
CREATE TABLE unregistered_owner (
chat_id BIGINT PRIMARY KEY,
preference SMALLINT NOT NULL
);