-- liquibase formatted sql


-- changeset volkov:6
CREATE TABLE adoption_requests(
id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
owner_id BIGINT,
pet_id BIGINT,
photo BYTEA,
status SMALLINT,
path_to_file TEXT NOT NULL,
additional_info TEXT NOT NULL,
CONSTRAINT adoption_request_pk PRIMARY KEY (id),
CONSTRAINT "FK_adoption_request_owner" FOREIGN KEY (owner_id) REFERENCES owner(id),
CONSTRAINT "FK_adoption_request_pet" FOREIGN KEY (pet_id) REFERENCES pet(id)
);