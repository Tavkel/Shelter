 -- liquibase formatted sql


 -- changeset volkov:2
 CREATE TABLE owner (
 id BIGINT GENERATED  BY DEFAULT AS IDENTITY NOT NULL,
 first_name VARCHAR(50) NOT NULL,
 last_name VARCHAR(50) NOT NULL,
 middle_name VARCHAR(50) NOT NULL,
 status SMALLINT NOT NULL,
CONSTRAINT owner_pk PRIMARY KEY (id)
 );

 -- changeset gleb:21
 ALTER TABLE owner
 ADD COLUMN telegram_chat_id BIGINT,
 ADD COLUMN telegram_handle VARCHAR(255),
 ADD COLUMN phone_number BIGINT,
 ADD COLUMN email VARCHAR(255),
 ADD COLUMN address VARCHAR(255),
 ADD COLUMN comment TEXT;
