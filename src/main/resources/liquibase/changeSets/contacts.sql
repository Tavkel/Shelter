-- liquibase formatted sql

--changeset volkov:3

CREATE TABLE contact (
owner_id BIGINT NOT NULL,
phone VARCHAR(11) NOT NULL,
telegram_chat_id BIGINT NOT NULL,
email VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL,
comment TEXT NOT NULL,
CONSTRAINT "owner_idPK" PRIMARY KEY (owner_id)
);