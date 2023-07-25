CREATE DATABASE departamento_pessoal;

CREATE TABLE `departamento_pessoal`.`pessoa` (
  `id` INT NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `identificador` VARCHAR(14) NOT NULL,
  `tipo_identificador` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `identificador_UNIQUE` (`identificador` ASC) VISIBLE);