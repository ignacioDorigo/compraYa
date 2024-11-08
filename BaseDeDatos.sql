CREATE DATABASE ecommerce;

CREATE TABLE usuarios(
	uuid VARCHAR(36) NOT NULL,
    mail VARCHAR(100) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    edad INTEGER NOT NULL,
    alias VARCHAR(50) NOT NULL,
    habilitado VARCHAR(50) NOT NULL,
    PRIMARY KEY (uuid)
);
