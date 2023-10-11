 -- liquibase formatted sql


 -- changeset volkov:2
 CREATE TABLE owner (
 id BIGINT GENERATED  BY DEFAULT AS IDENTITY NOT NULL,
 first_name VARCHAR(50) NOT NULL,
 last_name VARCHAR(50) NOT NULL,
 middle_name VARCHAR(50) NOT NULL
 );
ALTER TABLE owner ADD CONSTRAINT owner_pk PRIMARY KEY (id);