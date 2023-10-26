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

 --changeset zaitcev:21
 ALTER TABLE
 ADD COLUMN preference SMALLINT;

