-- liquibase formatted sql


-- changeset volkov:1
CREATE TABLE pet (
id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
name VARCHAR(50) NOT NULL,
breed VARCHAR(50) NOT NULL,
weight REAL NOT NULL,
date_of_birth TIMESTAMP WITH TIME ZONE NOT NULL,
file_size BIGINT NOT NULL,
media_type VARCHAR(50) NOT NULL,
photo BYTEA NOT NULL,
path_to_file TEXT NOT NULL,
description TEXT NOT NULL,
special_needs TEXT NOT NULL,
owner_id BIGINT NOT NULL,
CONSTRAINT pet_pk PRIMARY KEY (id),
CONSTRAINT "FK_pet_owner" FOREIGN KEY (owner_id) REFERENCES owner(id)
);
