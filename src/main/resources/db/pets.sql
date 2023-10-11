-- liquibase formatted sql


-- changelog volkov:1
CREATE TABLE pet (
id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
name VARCHAR(20) NOT NULL,
breed VARCHAR(50) NOT NULL,
weight REAL NOT NULL,
age INTEGER NOT NULL,
photo LONGBLOB NOT NULL,
path_to_file TEXT NOT NULL,
description TEXT NOT NULL,
special_needs TEXT NOT NULL
 );