CREATE TABLE jogadores (
    id INT IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    grupo VARCHAR(15),
    codinome VARCHAR(20)
);
