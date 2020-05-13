#pessoa confirmadas
Select Count(situacao_id) as saudavel From Pessoa where situacao_id = 1;
Select Count(situacao_id) as suspeito From Pessoa where situacao_id = 2;
Select Count(situacao_id) as confirmado From Pessoa where situacao_id = 3;
Select Count(situacao_id) as curado From Pessoa where situacao_id = 4;

#mes abril - numero de casos
Select Count(situacao_id) as saudavel From historicosituacao where situacao_id = 1 and Data >= "2020-03-31" and Data <= "2020-05-01";
Select Count(situacao_id) as suspeito From historicosituacao where situacao_id = 2 and Data >= "2020-03-31" and Data <= "2020-05-01";
Select Count(situacao_id) as confirmado From historicosituacao where situacao_id = 3 and Data >= "2020-03-31" and Data <= "2020-05-01";
Select Count(situacao_id) as curado From historicosituacao where situacao_id = 4 and Data >= "2020-03-31" and Data <= "2020-05-01";