-- liquibase formatted sql

-- changeset volkov:3

CREATE TABLE contact (
id BIGINT NOT NULL,
phone INT NOT NULL,
telegram_chat_id BIGINT NOT NULL,
email VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL,
comment TEXT NOT NULL,
CONSTRAINT "idPK" PRIMARY KEY (id),
CONSTRAINT "FK_contact_owner" FOREIGN KEY (id) REFERENCES owner(id)
);

--changeset volkov:8
ALTER TABLE contact DROP COLUMN phone;
ALTER TABLE contact ADD
 phone BIGINT NOT NULL;