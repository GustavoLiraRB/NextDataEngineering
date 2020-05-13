CREATE DATABASE NextDataEngineering;

CREATE TABLE NextDataEngineering.Situacao(
id             int         NOT NULL,
descricao      varchar(50) NOT NULL,
_created       DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
_updated       DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id)
);

CREATE TABLE NextDataEngineering.Pessoa(
id            varchar(64) NOT NULL,
situacao_id   int         NOT NULL,
_created      DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
_updated      DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id),
FOREIGN KEY (situacao_id) REFERENCES Situacao(id) ON UPDATE CASCADE
);


CREATE TABLE NextDataEngineering.HistoricoSituacao(
pessoa_id      varchar(64) NOT null,
DATA           datetime    NOT NULL,
situacao_id    int         NOT NULL,
_created       DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
_updated       DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (situacao_id) REFERENCES Situacao(id)             ON UPDATE CASCADE,
FOREIGN KEY (pessoa_id)   REFERENCES Pessoa(id)               ON UPDATE cascade
);


CREATE TABLE NextDataEngineering.RegistroLocalizacao (
pessoa_id      varchar(64)   NOT null,
DATA           datetime      NULL,
lat            double        NULL,
lng            double        NULL,
hashgeo        varchar(10)   not null,
hashdata       long          not null,
situacao_id    int           NOT NULL,
_created       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
_updated       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (situacao_id) REFERENCES Situacao(id)             ON UPDATE CASCADE,
FOREIGN KEY (pessoa_id)   REFERENCES Pessoa(id)               ON UPDATE cascade
);

CREATE TABLE NextDataEngineering.LimitesDeslocamento(
pessoa_id      varchar(64)    NOT null,
lat1           double         NULL,
lng1           double         NULL,
raio           double         NULL,
hashgeo        varchar(10)    not null,
FOREIGN KEY (pessoa_id) REFERENCES Pessoa(id) ON UPDATE cascade
);

