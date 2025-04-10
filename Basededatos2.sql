CREATE DATABASE SIGEI;
USE SIGEI;

CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    correo VARCHAR(100),
    contraseña VARCHAR(100),
    rol  varchar(100) NOT NULL
);

CREATE TABLE Estudiantes (
    id_estudiante INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    sexo varchar(100) NOT NULL,
    matricula VARCHAR(20) UNIQUE NOT NULL,
    correo varchar(100),
    Materias VARCHAR(100) NOT NULL,
    telefono VARCHAR(100)
);

CREATE TABLE Materias (
    id_materia INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE Calificaciones (
    id_calificacion INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT NOT NULL,
    id_materia INT NOT NULL,
    calificacion DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (id_estudiante) REFERENCES Estudiantes(id_estudiante) ON DELETE CASCADE,
    FOREIGN KEY (id_materia) REFERENCES Materias(id_materia) ON DELETE CASCADE
);

CREATE TABLE Profesores (
    id_profesor INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    nombre VARCHAR(100),
    especialidad VARCHAR(100),
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE
);



INSERT INTO Usuarios VALUES (null,'Jose','Primerisimoprimerplano@gmail.com','lol','administrador');
SELECT * FROM Usuarios;
SELECT * FROM Estudiantes;
SELECT * FROM Materias;
INSERT INTO Materias Values(null,'Matematicas');



ALTER TABLE Estudiantes
CHANGE COLUMN matrícula matricula VARCHAR(20) UNIQUE NOT NULL;
DELETE FROM Estudiantes WHERE id_estudiante = 1;
RENAME TABLE Carreras TO Materias;
RENAME TABLE Carreras TO Materias;




DROP DATABASE SIGEI
