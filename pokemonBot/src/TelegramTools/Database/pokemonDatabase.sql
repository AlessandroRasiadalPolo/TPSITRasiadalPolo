
CREATE DATABASE IF NOT EXISTS competitivepokedex;
USE competitivepokedex;



CREATE TABLE IF NOT EXISTS statistica(
    id INT AUTO_INCREMENT PRIMARY KEY,
    pokemonName varchar(100) NOT NULL,
    Atk int NOT NULL,
    Spe int NOT NULL,
    Def int NOT NULL,
    SAtk int NOT NULL,
    SDef int NOT NULL,
    PS int NOT NULL
);

CREATE TABLE IF NOT EXISTS Strumento(

    Nome varchar(100) NOT NULL PRIMARY KEY,
    Effetto varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Abilità(

    Nome varchar(100) NOT NULL PRIMARY KEY,
    Effetto varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Tipo(
    Nome varchar(100) NOT NULL PRIMARY KEY
);


CREATE TABLE IF NOT EXISTS Soffre(
    Id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    TipoDebole varchar(100) NOT NULL,
    TipoPokemon varchar(100) NOT NULL,
    FOREIGN KEY(TipoDebole) REFERENCES Tipo(Nome),
    FOREIGN KEY(TipoPokemon) REFERENCES Tipo(Nome)
);

CREATE TABLE IF NOT EXISTS Sopraffae(
    Id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    TipoForte varchar(100) NOT NULL,
    TipoPokemon varchar(100) NOT NULL,
    FOREIGN KEY(TipoForte) REFERENCES Tipo(Nome),
    FOREIGN KEY(TipoPokemon) REFERENCES Tipo(Nome)
);

CREATE TABLE IF NOT EXISTS Immune(
    Id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    TipoImmune varchar(100) NOT NULL,
    TipoPokemon varchar(100) NOT NULL,
    FOREIGN KEY(TipoImmune) REFERENCES Tipo(Nome),
    FOREIGN KEY(TipoPokemon) REFERENCES Tipo(Nome)
);

CREATE TABLE IF NOT EXISTS Mossa(
    Nome varchar(100) NOT NULL PRIMARY KEY,
    Pp int NOT NULL,
    Tipo varchar(100) NOT NULL,
    Effetto varchar(100),
    Potenza int,
    Precisione int,
    Priorità int,
    Categoria ENUM ('Special', 'Non-Damaging', 'Physical') NOT NULL,

    FOREIGN KEY(Tipo) REFERENCES Tipo(Nome)

);

CREATE TABLE IF NOT EXISTS Pokemon(
    NomePokemon varchar(100) NOT NULL PRIMARY KEY,
    PokedexId int NOT NULL,
    NomeTipo1 varchar(100) NOT NULL,
    NomeTipo2 varchar(100),
    Generazione varchar(100) NOT NULL,
    Icon varchar(200),


    FOREIGN KEY(NomeTipo1) REFERENCES Tipo(Nome),
    FOREIGN KEY(NomeTipo2) REFERENCES Tipo(Nome)
);

CREATE TABLE IF NOT EXISTS Sviluppa(
    Id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    NomePokemon varchar(100) NOT NULL,
    NomeAbilità varchar(100) NOT NULL,

    FOREIGN KEY(NomePokemon) REFERENCES Pokemon(NomePokemon),
    FOREIGN KEY(NomeAbilità) REFERENCES Abilità(Nome)
);

CREATE TABLE IF NOT EXISTS Impara(
    Id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NomePokemon varchar(100) NOT NULL,
    NomeMossa varchar(100) NOT NULL,

    FOREIGN KEY(NomePokemon) REFERENCES Pokemon(NomePokemon),
    FOREIGN KEY(NomeMossa) REFERENCES Mossa(Nome)
);


