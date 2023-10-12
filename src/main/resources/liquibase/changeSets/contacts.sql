-- liquibase formatted sql

--changeset volkov:3

CREATE TABLE contact (
id BIGINT NOT NULL,
owner_id BIGINT NOT NULL,
phone INT NOT NULL,
telegram_chat_id BIGINT NOT NULL,
email VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL,
comment TEXT NOT NULL,
CONSTRAINT "idPK" PRIMARY KEY (owner_id),
CONSTRAINT "FK_contact_owner" FOREIGN KEY (id) REFERENCES owner(id)
);
