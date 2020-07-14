CREATE DATABASE IF NOT EXISTS rehaklinik CHARACTER SET utf8 COLLATE utf8_general_ci;
DROP database rehaklinik;

CREATE TABLE IF NOT EXISTS rehaklinik.positions (
id int (11) NOT NULL AUTO_INCREMENT,
position varchar (50) NOT NULL, 
PRIMARY KEY positions_id_PK (id), 
UNIQUE KEY position_UK (position)
);

CREATE TABLE IF NOT EXISTS rehaklinik.qualification_category(
id int (11) NOT NULL AUTO_INCREMENT,
qualification_category varchar(25), 
PRIMARY KEY qualification_category_id_PK (id) 
);

CREATE TABLE IF NOT EXISTS rehaklinik.employees (
id int (11) NOT NULL AUTO_INCREMENT,
first_name varchar (50) NOT NULL,
second_name varchar (50), 
surname varchar (50) NOT NULL,
login varchar (35) NOT NULL UNIQUE,
password varchar (35) NOT NULL UNIQUE,
date_of_birth Date NOT NULL, 
phone_number varchar (25),
address varchar (60),
position_id int (11) NOT NULL,
qualification_category_id int (11),
PRIMARY KEY employees_id_PK (id), 
FOREIGN KEY position_FK (position_id) REFERENCES rehaklinik.positions(id), 
FOREIGN KEY qualification_category_FK (qualification_category_id) REFERENCES rehaklinik.qualification_category(id), 
UNIQUE KEY login_UK (login), 
UNIQUE KEY password_UK (password)
);





