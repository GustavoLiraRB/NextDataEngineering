#seleciona os grupos onde existe mais de uma pessoa numa mesma data e local e adiciona uma coluna onde 1 significa que esse grupo contem um confirmado
select hashgeo,hashdata, sum(situacao_id=3)>0 as condicao from registrolocalizacao group by hashgeo,hashdata having count(*) >1 and condicao>0;
select * from registrolocalizacao;
#adiciona uma coluna onde 1 significa que o grupo,mesmo local e mesma data, contem um confirmado
Alter table registrolocalizacao add column condicao int default 0;

#cria uma tabela temporaria direto no mysql procura a condição acima e atualiza a coluna de acordo
UPDATE registrolocalizacao TAB1
INNER JOIN (
select hashgeo,hashdata from registrolocalizacao
group by hashgeo,hashdata having count(*) >1 and sum(situacao_id=3)>0
) TAB2 ON TAB1.hashgeo = TAB2.hashgeo
	And TAB1.hashdata = TAB2.hashdata				      
SET TAB1.condicao = 1;



#Atualiza diretamente a situação da pessoa de acordo com a condição: mesmo lugar e mesma data que uma pessoa confirmada
UPDATE registrolocalizacao TAB1
INNER JOIN (
select hashgeo,hashdata from registrolocalizacao
group by hashgeo,hashdata having count(*) >1 and sum(situacao_id=3)>0
) TAB2 ON TAB1.hashgeo = TAB2.hashgeo
	And TAB1.hashdata = TAB2.hashdata				      
SET TAB1.situacao_id = 2
WHERE TAB1.situacao_id = 1;

