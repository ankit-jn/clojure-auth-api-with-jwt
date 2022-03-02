CREATE DATABASE db_auth;

USE db_auth;

-- DDL statements

CREATE TABLE users
(id INTEGER PRIMARY KEY AUTO_INCREMENT,
 email VARCHAR(50),
 password VARCHAR(200)
 );


CREATE TABLE tokens
(id INTEGER PRIMARY KEY AUTO_INCREMENT,
 user_id int,
 authToken VARCHAR(2000),
 refreshToken VARCHAR(2000),
 issued_at bigint,
 status CHAR(1)
 );

CREATE TABLE roles
(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  role_name varchar(10)
);

CREATE TABLE user_roles (
  user_id int,
  role_id int
);

-- Foreign key constraints statements

ALTER TABLE tokens
ADD CONSTRAINT FK_tokens_1
FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE user_roles
ADD CONSTRAINT FK_user_roles_1
FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE user_roles
ADD CONSTRAINT FK_user_roles_2
FOREIGN KEY (role_id) REFERENCES roles(id);

-- DML statements

INSERT INTO roles
VALUES
(1, 'ADMIN'),
(2, 'USER');
