#Cria uma subtabela temporária com grupos onde existe mais de uma pessoa numa mesma data e local e contém pelo menos uma pessoa contaminada

#Drop table temp;
Create table temp(
select * from registrolocalizacao where (hashgeo,hashdata) in (
select hashgeo,hashdata from registrolocalizacao group by hashgeo,hashdata having count(*) >1 AND MAX(CASE situacao_id WHEN 3 THEN 1 ELSE 0 END)));

#Atualiza os saudáveis para suspeitos dentro da condição imposta anteriormente
SET SQL_SAFE_UPDATES = 0;
update temp set situacao_id=2 where situacao_id=1;
SET SQL_SAFE_UPDATES = 1;

#Atualiza a tabela registrolocalizacao nos ids das pessoas identificados anteriormente
update
  registrolocalizacao rl inner join temp t
  on rl.pessoa_id = t.pessoa_id
set
  rl.situacao_id = t.situacao_id;
  
  Drop table temp;
  