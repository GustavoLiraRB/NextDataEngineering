#schema
DROP DATABASE nextdataengineeringteste;#comentar esta linha se o database não ja tiver criada

CREATE DATABASE nextdataengineeringteste;

Use nextdataengineeringteste;

#tabelas

CREATE TABLE situacao (
  ID             int          NOT NULL,
  DESCRICAO      varchar(255) DEFAULT NULL,
  _created       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  _updated       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE pessoa (
  ID             varchar(255) NOT NULL,
  SITUACAO_ID    int          DEFAULT NULL,
  _created       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  _updated       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (ID),
  FOREIGN KEY (SITUACAO_ID) REFERENCES Situacao(id) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE historicosituacao (
  ID             int          AUTO_INCREMENT,
  DATA           datetime     DEFAULT NULL,
  HASHDATA       int          DEFAULT NULL,
  PESSOA_ID      varchar(255) DEFAULT NULL,
  SITUACAO_ID    int(11)      DEFAULT NULL,
  _created       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  _updated       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (ID),
  FOREIGN KEY (SITUACAO_ID) REFERENCES Situacao(id) ON UPDATE CASCADE,
  FOREIGN KEY (PESSOA_ID)   REFERENCES Pessoa(id)   ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE registrolocalizacao (
  ID             int           AUTO_INCREMENT,
  DATA           datetime      DEFAULT NULL,
  HASHDATA       int           DEFAULT NULL,
  HASHGEO        varchar(255)  DEFAULT NULL,
  LAT            double        DEFAULT NULL,
  LNG            double        DEFAULT NULL,
  PESSOA_ID      varchar(255)  DEFAULT NULL,
  SITUACAO_ID    int(11)       DEFAULT NULL,
  _created       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  _updated       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (ID),
  FOREIGN KEY (SITUACAO_ID) REFERENCES Situacao(id) ON UPDATE CASCADE,
  FOREIGN KEY (PESSOA_ID)   REFERENCES Pessoa(id)   ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE limitesdeslocamento (
  ID             int               AUTO_INCREMENT,
  HASHGEO        varchar(255)      DEFAULT NULL,
  LAT1           double            DEFAULT NULL,
  LNG1           double            DEFAULT NULL,
  RAIO           double            DEFAULT NULL,
  PESSOA_ID      varchar(255)      DEFAULT NULL,
  _created       DATETIME          NOT NULL DEFAULT CURRENT_TIMESTAMP,
  _updated       DATETIME          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (ID),
  FOREIGN KEY (PESSOA_ID)   REFERENCES Pessoa(id)   ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



#valores da tabela situação

insert into situacao(id, descricao)
Values (1, "saudável");

insert into situacao(id, descricao)
Values (2, "suspeito");

insert into situacao(id, descricao)
Values (3, "confirmado");

insert into situacao(id, descricao)
Values (4, "curado");


#índices

CREATE INDEX idx_situacao ON situacao(ID);

CREATE INDEX idx_pessoa ON pessoa(ID);

CREATE INDEX idx_geolocalizacao ON registrolocalizacao(HASHGEO);

CREATE INDEX idx_hashdata ON registrolocalizacao(HASHDATA);

CREATE INDEX idx_data ON historicosituacao(Data);

CREATE INDEX idx_geolocalizacao ON limitesdeslocamento(HASHGEO);

#Grupo 1 Mesmo local, mesma data - local Cesar School - data Thu Apr 30 11:39:00
#Tipo 1 - Saudavél
#Pessoa 1 - tem 3 datas, um dia depois(que também precisa ser atualizada) e um dia antes que deve permanecer igual
#7f4078ec-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('7f4078ec-8ae1-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkyduy',-8.059337,-34.872461,'7f4078ec-8ae1-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID) #+1dia
Values(STR_TO_DATE("2020,05,01 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294479, '7nx4nkyduy',-8.059337,-34.872461,'7f4078ec-8ae1-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID) #-1dia
Values(STR_TO_DATE("2020,04,29 11,39,00", "%Y,%m,%d %h,%i,%s"), 5293903, '7nx4nkyduy',-8.059337,-34.872461,'7f4078ec-8ae1-11ea-bc55-0242ac130003',1);


insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'7f4078ec-8ae1-11ea-bc55-0242ac130003',1);

#Pessoa 2
#7f407aea-8ae1-11ea-bc55-0242ac130003

insert into Pessoa (ID, situacao_id)
values('7f407aea-8ae1-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkyduy',-8.059337,-34.872461,'7f407aea-8ae1-11ea-bc55-0242ac130003',1);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'7f407aea-8ae1-11ea-bc55-0242ac130003',1);

#Pessoa 3
#7f407be4-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('7f407be4-8ae1-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkyduy',-8.059337,-34.872461,'7f407be4-8ae1-11ea-bc55-0242ac130003',1);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'7f407be4-8ae1-11ea-bc55-0242ac130003',1);


#Pessoa 4
#7f407e46-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('7f407e46-8ae1-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkyduy',-8.059337,-34.872461,'7f407e46-8ae1-11ea-bc55-0242ac130003',1);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'7f407e46-8ae1-11ea-bc55-0242ac130003',1);



#Tipo 2 - suspeito
#Pessoa1
#7f407f18-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('7f407f18-8ae1-11ea-bc55-0242ac130003',2);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkyduy',-8.059337,-34.872461,'7f407f18-8ae1-11ea-bc55-0242ac130003',2);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'7f407f18-8ae1-11ea-bc55-0242ac130003',2);

#Pessoa2
#a8c170cc-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('a8c170cc-8ae1-11ea-bc55-0242ac130003',2);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkyduy',-8.059337,-34.872461,'a8c170cc-8ae1-11ea-bc55-0242ac130003',2);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'a8c170cc-8ae1-11ea-bc55-0242ac130003',2);

#Pessoa3
#a8c1737e-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('a8c1737e-8ae1-11ea-bc55-0242ac130003',2);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkyduy',-8.059337,-34.872461,'a8c1737e-8ae1-11ea-bc55-0242ac130003',2);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'a8c1737e-8ae1-11ea-bc55-0242ac130003',2);

#Pessoa4
#a8c17482-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('a8c17482-8ae1-11ea-bc55-0242ac130003',2);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkyduy',-8.059337,-34.872461,'a8c17482-8ae1-11ea-bc55-0242ac130003',2);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'a8c17482-8ae1-11ea-bc55-0242ac130003',2);

#Tipo 3 - Confirmado
#Pessoa1
#a8c175f4-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('a8c175f4-8ae1-11ea-bc55-0242ac130003',3);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkyduy',-8.059337,-34.872461,'a8c175f4-8ae1-11ea-bc55-0242ac130003',3);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'a8c175f4-8ae1-11ea-bc55-0242ac130003',3);

#Pessoa2
#d2a35ed2-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('d2a35ed2-8ae1-11ea-bc55-0242ac130003',3);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkyduy',-8.059337,-34.872461,'d2a35ed2-8ae1-11ea-bc55-0242ac130003',3);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'d2a35ed2-8ae1-11ea-bc55-0242ac130003',3);

#Tipo 4 - Curado
#Pessoa1
#a8c17734-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('a8c17734-8ae1-11ea-bc55-0242ac130003',4);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkyduy',-8.059337,-34.872461,'a8c17734-8ae1-11ea-bc55-0242ac130003',4);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'a8c17734-8ae1-11ea-bc55-0242ac130003',4);

#Pessoa2
#ba283846-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('ba283846-8ae1-11ea-bc55-0242ac130003',4);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkyduy',-8.059337,-34.872461,'ba283846-8ae1-11ea-bc55-0242ac130003',4);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'ba283846-8ae1-11ea-bc55-0242ac130003',4);

#Pessoa3
#ba283a4e-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('ba283a4e-8ae1-11ea-bc55-0242ac130003',4);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkyduy',-8.059337,-34.872461,'ba283a4e-8ae1-11ea-bc55-0242ac130003',4);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'ba283a4e-8ae1-11ea-bc55-0242ac130003',4);

#Pessoa4
#ba283ba2-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('ba283ba2-8ae1-11ea-bc55-0242ac130003',4);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkyduy',-8.059337,-34.872461,'ba283ba2-8ae1-11ea-bc55-0242ac130003',4);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'ba283ba2-8ae1-11ea-bc55-0242ac130003',4);


#Grupo 2 = Mesmo local, data diferente - local Cesar School

#Tipo 1 - Saudável

#Pessoa1 - 2020/04/30 11:44 - 5294192 (+5min da data anterior)
#ba283d96-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('ba283d96-8ae1-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,44,00", "%Y,%m,%d %h,%i,%s"), 5294192, '7nx4nkyduy',-8.059337,-34.872461,'ba283d96-8ae1-11ea-bc55-0242ac130003',1);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,44,00", "%Y,%m,%d %h,%i,%s"),5294192,'ba283d96-8ae1-11ea-bc55-0242ac130003',1);


#Pessoa2 - 2020/04/30 11:34 - 5294190 (-5min da data anterior)
#ba283e72-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('ba283e72-8ae1-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,34,00", "%Y,%m,%d %h,%i,%s"), 5294190, '7nx4nkyduy',-8.059337,-34.872461,'ba283e72-8ae1-11ea-bc55-0242ac130003',1);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,34,00", "%Y,%m,%d %h,%i,%s"),5294190,'ba283e72-8ae1-11ea-bc55-0242ac130003',1);

#Pessoa3 - 2020/04/29 11:39 - 5293903 (mesma hora -1 dia)
#cd1ac824-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('cd1ac824-8ae1-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,29 11,39,00", "%Y,%m,%d %h,%i,%s"), 5293903, '7nx4nkyduy',-8.059337,-34.872461,'cd1ac824-8ae1-11ea-bc55-0242ac130003',1);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,29 11,39,00", "%Y,%m,%d %h,%i,%s"),5293903,'cd1ac824-8ae1-11ea-bc55-0242ac130003',1);

#Pessoa4 - 2020/05/01 11:39 - 5294479 (mesma hora +1 dia)
#cd1aca40-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('cd1aca40-8ae1-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,05,01 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294479, '7nx4nkyduy',-8.059337,-34.872461,'cd1aca40-8ae1-11ea-bc55-0242ac130003',1);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,05,01 11,39,00", "%Y,%m,%d %h,%i,%s"),5294479,'cd1aca40-8ae1-11ea-bc55-0242ac130003',1);

#Tipo 2 - Suspeito

#Pessoa1 - 2020/04/30 11:44 - 5294192 (+5min da data anterior)
#cd1acb3a-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('cd1acb3a-8ae1-11ea-bc55-0242ac130003',2);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,44,00", "%Y,%m,%d %h,%i,%s"), 5294192, '7nx4nkyduy',-8.059337,-34.872461,'cd1acb3a-8ae1-11ea-bc55-0242ac130003',2);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,44,00", "%Y,%m,%d %h,%i,%s"),5294192,'cd1acb3a-8ae1-11ea-bc55-0242ac130003',2);

#Pessoa2 - 2020/04/30 11:34 - 5294190 (-5min da data anterior)
#cd1acc16-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('cd1acc16-8ae1-11ea-bc55-0242ac130003',2);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,34,00", "%Y,%m,%d %h,%i,%s"), 5294190, '7nx4nkyduy',-8.059337,-34.872461,'cd1acc16-8ae1-11ea-bc55-0242ac130003',2);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,34,00", "%Y,%m,%d %h,%i,%s"),5294190,'cd1acc16-8ae1-11ea-bc55-0242ac130003',2);

#Pessoa3 - 2020/04/29 11:39 - 5293903 (mesma hora -1 dia)
#cd1ad9ea-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('cd1ad9ea-8ae1-11ea-bc55-0242ac130003',2);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,29 11,39,00", "%Y,%m,%d %h,%i,%s"), 5293903, '7nx4nkyduy',-8.059337,-34.872461,'cd1ad9ea-8ae1-11ea-bc55-0242ac130003',2);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,29 11,39,00", "%Y,%m,%d %h,%i,%s"),5293903,'cd1ad9ea-8ae1-11ea-bc55-0242ac130003',2);

#Pessoa4 - 2020/05/01 11:39 - 5294479 (mesma hora +1 dia)
#d2a35928-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('d2a35928-8ae1-11ea-bc55-0242ac130003',2);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,05,01 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294479, '7nx4nkyduy',-8.059337,-34.872461,'d2a35928-8ae1-11ea-bc55-0242ac130003',2);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,05,01 11,39,00", "%Y,%m,%d %h,%i,%s"),5294479,'d2a35928-8ae1-11ea-bc55-0242ac130003',2);


#Tipo 3 - Confirmado

#Pessoa1 - 2020/03/30 11:39 (-1mês) - 5285263
#d2a35c34-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('d2a35c34-8ae1-11ea-bc55-0242ac130003',3);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,03,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5285263, '7nx4nkyduy',-8.059337,-34.872461,'d2a35c34-8ae1-11ea-bc55-0242ac130003',3);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,03,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5285263,'d2a35c34-8ae1-11ea-bc55-0242ac130003',3);

#Pessoa2 - 2020/05/30 11:39 (+1mês) - 5302831
#d2a35d2e-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('d2a35d2e-8ae1-11ea-bc55-0242ac130003',3);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,05,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5302831, '7nx4nkyduy',-8.059337,-34.872461,'d2a35d2e-8ae1-11ea-bc55-0242ac130003',3);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,05,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5302831,'d2a35d2e-8ae1-11ea-bc55-0242ac130003',3);




#Tipo 4 - Curado

#Pessoa1 - 2020/04/30 11:44 - 5294192 (+5min da data anterior)
#e88d5054-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('e88d5054-8ae1-11ea-bc55-0242ac130003',4);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,44,00", "%Y,%m,%d %h,%i,%s"), 5294192, '7nx4nkyduy',-8.059337,-34.872461,'e88d5054-8ae1-11ea-bc55-0242ac130003',4);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,44,00", "%Y,%m,%d %h,%i,%s"),5294192,'e88d5054-8ae1-11ea-bc55-0242ac130003',4);

#Pessoa2 - 2020/04/30 11:34 - 5294190 (-5min da data anterior)
#e88d5568-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('e88d5568-8ae1-11ea-bc55-0242ac130003',4);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,34,00", "%Y,%m,%d %h,%i,%s"), 5294190, '7nx4nkyduy',-8.059337,-34.872461,'e88d5568-8ae1-11ea-bc55-0242ac130003',4);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,34,00", "%Y,%m,%d %h,%i,%s"),5294190,'e88d5568-8ae1-11ea-bc55-0242ac130003',4);


#Pessoa3 - 2020/04/29 11:39 - 5293903 (mesma hora -1 dia)
#e88d5680-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('e88d5680-8ae1-11ea-bc55-0242ac130003',4);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,29 11,39,00", "%Y,%m,%d %h,%i,%s"), 5293903, '7nx4nkyduy',-8.059337,-34.872461,'e88d5680-8ae1-11ea-bc55-0242ac130003',4);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,29 11,39,00", "%Y,%m,%d %h,%i,%s"),5293903,'e88d5680-8ae1-11ea-bc55-0242ac130003',4);

#Pessoa4 - 2020/05/01 11:39 - 5294479 (mesma hora +1 dia)
#e88d575c-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('e88d575c-8ae1-11ea-bc55-0242ac130003',4);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,05,01 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294479, '7nx4nkyduy',-8.059337,-34.872461,'e88d575c-8ae1-11ea-bc55-0242ac130003',4);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,05,01 11,39,00", "%Y,%m,%d %h,%i,%s"),5294479,'e88d575c-8ae1-11ea-bc55-0242ac130003',4);


#Grupo 3 local diferente, data igual - data Thu Apr 30 11:39:00 - 5294191
#Tipo 1 - Saudável - local -8.069763 , -34.877062 Cais Santa Rita - 7nx4n6gq1x
#Pessoa1
#fbe86576-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('fbe86576-8ae1-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4n6gq1x',-8.069763,-34.877062,'fbe86576-8ae1-11ea-bc55-0242ac130003',1);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'fbe86576-8ae1-11ea-bc55-0242ac130003',1);

#Tipo 2 - Suspeito - local -8.053977, -34.872365 Perfeitura Recife - 7nx4nmydnv
#Pessoa1
#0f24a456-8ae2-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('0f24a456-8ae2-11ea-bc55-0242ac130003',2);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nmydnv',-8.053977,-34.872365,'0f24a456-8ae2-11ea-bc55-0242ac130003',2);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'0f24a456-8ae2-11ea-bc55-0242ac130003',2);

#Tipo 3 - Confirmado - local -8.062887, -34.888623 Mercado da Boa Vista - 7nx4nh4yk0
#Pessoa1
#22d39048-8ae2-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('22d39048-8ae2-11ea-bc55-0242ac130003',3);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nh4yk0',-8.062887,-34.888623,'22d39048-8ae2-11ea-bc55-0242ac130003',3);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'22d39048-8ae2-11ea-bc55-0242ac130003',3);

#Tipo 4 - Curado - local -8.059551, -34.877614 Palácio das princesas - 7nx4nkfcsw
#Pessoa1
#0f24a8c0-8ae2-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('0f24a8c0-8ae2-11ea-bc55-0242ac130003',4);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx4nkfcsw',-8.059551,-34.877614,'0f24a8c0-8ae2-11ea-bc55-0242ac130003',4);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'0f24a8c0-8ae2-11ea-bc55-0242ac130003',4);


#Grupo 4 - Outro mesmo local, data igual - local -8.117611, -34.893051 Praia de Boa Viagem - 7nx1vfpxkz - data Thu Apr 30 11:39:00 - 5294191

#Tipo 1 - Saudável
#Pessoa1
#e88d5824-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('e88d5824-8ae1-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx1vfpxkz',-8.117611,-34.893051,'e88d5824-8ae1-11ea-bc55-0242ac130003',1);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'e88d5824-8ae1-11ea-bc55-0242ac130003',1);

#Pessoa2
#fbe861b6-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('fbe861b6-8ae1-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx1vfpxkz',-8.117611,-34.893051,'fbe861b6-8ae1-11ea-bc55-0242ac130003',1);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'fbe861b6-8ae1-11ea-bc55-0242ac130003',1);


#Pessoa3
#fbe863dc-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('fbe863dc-8ae1-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx1vfpxkz',-8.117611,-34.893051,'fbe863dc-8ae1-11ea-bc55-0242ac130003',1);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'fbe863dc-8ae1-11ea-bc55-0242ac130003',1);


#Pessoa4
#0f24a23a-8ae2-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('0f24a23a-8ae2-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx1vfpxkz',-8.117611,-34.893051,'0f24a23a-8ae2-11ea-bc55-0242ac130003',1);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'0f24a23a-8ae2-11ea-bc55-0242ac130003',1);



#Tipo 2 - Suspeito
#Pessoa1
#fbe8665c-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('fbe8665c-8ae1-11ea-bc55-0242ac130003',2);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx1vfpxkz',-8.117611,-34.893051,'fbe8665c-8ae1-11ea-bc55-0242ac130003',2);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'fbe8665c-8ae1-11ea-bc55-0242ac130003',2);


#Pessoa2
#fbe8672e-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('fbe8672e-8ae1-11ea-bc55-0242ac130003',2);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx1vfpxkz',-8.117611,-34.893051,'fbe8672e-8ae1-11ea-bc55-0242ac130003',2);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'fbe8672e-8ae1-11ea-bc55-0242ac130003',2);

#Remover a próxima pessoa para testar se um grupo de pessoas que não contém alguém contaminada muda
#Tipo 3
#Pessoa1
#d2a35e0a-8ae1-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('d2a35e0a-8ae1-11ea-bc55-0242ac13000',3);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx1vfpxkz',-8.117611,-34.893051,'d2a35e0a-8ae1-11ea-bc55-0242ac13000',3);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'d2a35e0a-8ae1-11ea-bc55-0242ac13000',3);

#Tipo 4 - Curado 
#Pessoa1
#0f24a550-8ae2-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('0f24a550-8ae2-11ea-bc55-0242ac130003',4);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx1vfpxkz',-8.117611,-34.893051,'0f24a550-8ae2-11ea-bc55-0242ac130003',4);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'0f24a550-8ae2-11ea-bc55-0242ac130003',4);

#Pessoa2
#0f24a618-8ae2-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('0f24a618-8ae2-11ea-bc55-0242ac130003',4);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"), 5294191, '7nx1vfpxkz',-8.117611,-34.893051,'0f24a618-8ae2-11ea-bc55-0242ac130003',4);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,04,30 11,39,00", "%Y,%m,%d %h,%i,%s"),5294191,'0f24a618-8ae2-11ea-bc55-0242ac130003',4);


#Grupo 5 - Outro mesmo local, e outra data igual - local -8.013586, -34.849738 Alto da Sé da Olinda - 7nx4qvpjqp - data Wed May 06 11:39:00 - 5295919

#Tipo 1 - Saudável
#Pessoa1
#1ccccf90-8fa4-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('1ccccf90-8fa4-11ea-bc55-0242ac130003',1);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,05,06 11,39,00", "%Y,%m,%d %h,%i,%s"), 5295919, '7nx4qvpjqp',-8.013586,-34.849738,'1ccccf90-8fa4-11ea-bc55-0242ac130003',1);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,05,06 11,39,00", "%Y,%m,%d %h,%i,%s"),5295919,'1ccccf90-8fa4-11ea-bc55-0242ac130003',1);


#Tipo 3 - Confirmado
#Pessoa1
#1cccd2b0-8fa4-11ea-bc55-0242ac130003
insert into Pessoa (ID, situacao_id)
values('1cccd2b0-8fa4-11ea-bc55-0242ac130003',3);

insert into registrolocalizacao(DATA,HASHDATA,HASHGEO,LAT,LNG,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,05,06 11,39,00", "%Y,%m,%d %h,%i,%s"), 5295919, '7nx4qvpjqp',-8.013586,-34.849738,'1cccd2b0-8fa4-11ea-bc55-0242ac130003',3);

insert into historicosituacao(DATA,HASHDATA,PESSOA_ID,SITUACAO_ID)
Values(STR_TO_DATE("2020,05,06 11,39,00", "%Y,%m,%d %h,%i,%s"),5295919,'1cccd2b0-8fa4-11ea-bc55-0242ac130003',3);




select * from registrolocalizacao;
/*Gabarito(este gabarito não foi atualizado para o novo grupo de teste)
6 locais distintos,
7 datas distintas,
5 datas se repetem,
2 locais se repetem,
37 entradas onde a data tem mais que um valor repetido,
35 entradas onde o local tem mais que um valor repetido,
33 entradas onde existe um par data, local identico,
Total 41 entradas.*/

