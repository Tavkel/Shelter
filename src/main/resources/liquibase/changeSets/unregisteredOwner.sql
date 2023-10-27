-- liquibase formatted sql


-- changeset zaitcev:7
CREATE TABLE unregisteredOwner (
chat_id BIGINT PRIMARY KEY,
preference SMALLINT NOT NULL
);