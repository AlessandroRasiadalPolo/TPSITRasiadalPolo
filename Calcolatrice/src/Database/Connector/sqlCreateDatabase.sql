
CREATE DATABASE IF NOT EXISTS utenti;
USE utenti;


CREATE TABLE IF NOT EXISTS utenti(
Name varchar(40) NOT NULL,
Surname varchar(40) NOT NULL,
Nickname varchar(40) NOT NULL,
Password varchar(40) NOT NULL,
age int NOT NULL,
PRIMARY KEY(Nickname));

CREATE TABLE IF NOT EXISTS cronologia(
    id int NOT NULL AUTO_INCREMENT,
    NicknameUtente varchar(40) NOT NULL,
    equazione varchar(50) NOT NULL,
    PRIMARY KEY(id)
);