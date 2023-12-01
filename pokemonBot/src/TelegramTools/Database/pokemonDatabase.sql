CREATE DATABASE IF NOT EXISTS competitivepokedex;
USE competitivepokedex;


CREATE TABLE IF NOT EXISTS statistiche(
    Id  int NOT NULL PRIMARY KEY,
    Atk int NOT NULL,
    Spe int NOT NULL,
    Def int NOT NULL,
    SAtk int NOT NULL,
    SDef int NOT NULL,
    PS int NOT NULL
);

CREATE TABLE IF NOT EXISTS Strumento(

    Nome varchar(20) NOT NULL PRIMARY KEY,
    Effetto varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Abilità(

    Nome varchar(20) NOT NULL PRIMARY KEY,
    Effetto varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Tipo(
    Nome varchar(20) NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS TipoDebole(
    Nome varchar(20) NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS TipoForte(
    Nome varchar(20) NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS TipoImmune(
    Nome varchar(20) NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS Debolezza(
    Id int NOT NULL PRIMARY KEY,
    TipoDebole varchar(20) NOT NULL,
    TipoPokemon varchar(20) NOT NULL,
    FOREIGN KEY(TipoDebole) REFERENCES Tipo(Nome),
    FOREIGN KEY(TipoPokemon) REFERENCES Tipo(Nome)
);

CREATE TABLE IF NOT EXISTS Forza(
    Id int NOT NULL PRIMARY KEY,
    TipoForte varchar(20) NOT NULL,
    TipoPokemon varchar(20) NOT NULL,
    FOREIGN KEY(TipoForte) REFERENCES Tipo(Nome),
    FOREIGN KEY(TipoPokemon) REFERENCES Tipo(Nome)
);

CREATE TABLE IF NOT EXISTS Immune(
    Id int NOT NULL PRIMARY KEY,
    TipoImmune varchar(20) NOT NULL,
    TipoPokemon varchar(20) NOT NULL,
    FOREIGN KEY(TipoImmune) REFERENCES Tipo(Nome),
    FOREIGN KEY(TipoPokemon) REFERENCES Tipo(Nome)
);

CREATE TABLE IF NOT EXISTS Mossa(
    Nome varchar(20) NOT NULL PRIMARY KEY,
    Tipo varchar(20) NOT NULL,

    FOREIGN KEY(Tipo) REFERENCES Tipo(Nome)

);

CREATE TABLE IF NOT EXISTS Pokemon(
    NomePokemon varchar(20) NOT NULL PRIMARY KEY,
    PokedexId int NOT NULL,
    IdStats int NOT NULL,
    NomeStrumento varchar(20),
    NomeAbilità varchar(20) NOT NULL,
    NomeTipo1 varchar(20) NOT NULL,
    NomeTipo2 varchar(20),
    Generazione varchar(20),
    Mossa1 varchar(20),
    Mossa2 varchar(20),
    Mossa3 varchar(20),
    Mossa4 varchar(20),

    FOREIGN KEY(IdStats) REFERENCES statistiche(Id),
    FOREIGN KEY(NomeStrumento) REFERENCES Strumento(Nome),
    FOREIGN KEY(NomeAbilità) REFERENCES Abilità(Nome),
    FOREIGN KEY(NomeTipo1) REFERENCES Tipo(Nome),
    FOREIGN KEY(NomeTipo2) REFERENCES Tipo(Nome),
    FOREIGN KEY(Mossa1) REFERENCES Mossa(Nome),
    FOREIGN KEY(Mossa2) REFERENCES Mossa(Nome),
    FOREIGN KEY(Mossa3) REFERENCES Mossa(Nome),
    FOREIGN KEY(Mossa4) REFERENCES Mossa(Nome)
);

CREATE TABLE IF NOT EXISTS Impara(
    Id int NOT NULL PRIMARY KEY,
    NomePokemon varchar(20) NOT NULL,
    NomeMossa varchar(20) NOT NULL,

    FOREIGN KEY(NomePokemon) REFERENCES Pokemon(NomePokemon),
    FOREIGN KEY(NomeMossa) REFERENCES Mossa(Nome)
);


