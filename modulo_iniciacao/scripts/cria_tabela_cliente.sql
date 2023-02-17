CREATE TABLE Cliente (
     codigo INT AUTO_INCREMENT  NOT NULL,
     nome   VARCHAR(50)         NULL,
     idade  INT                 NULL,
     sexo   CHAR(1)             NULL,
     profissao VARCHAR(50)      NULL,
     PRIMARY KEY (codigo)
);