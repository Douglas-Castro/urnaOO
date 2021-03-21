-- 					    CANDIDATOS  
-- 
--               SCRIPT DO TRABALHO FINAL 
--
-- DATA DE CRIACAO ...........: 11/02/2020
-- AUTOR(ES) .................: Douglas Farias
-- BANCO DE DADOS ............: MySQL
-- BANCO DE DADOS(NOME) ......: javaVerao
--
--
-- PROJETO => 01 BASE DE DADOS
--         => 01 TABELA
-- 


CREATE DATABASE IF NOT EXISTS javaVerao;

USE javaVerao;

CREATE TABLE candidato (
	nome    VARCHAR(100) NOT NULL,
	sigla   VARCHAR(10)  NOT NULL ,
	legenda INT(2)       NOT NULL 
);


