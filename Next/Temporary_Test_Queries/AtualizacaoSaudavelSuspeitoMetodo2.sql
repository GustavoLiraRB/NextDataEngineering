Start Transaction;


Alter table registrolocalizacao add column a_processar int default 0;


UPDATE registrolocalizacao TAB1
INNER JOIN (
select hashgeo,hashdata from registrolocalizacao
group by hashgeo,hashdata having count(*) >1 and sum(situacao_id=3)>0
) TAB2 ON TAB1.hashgeo = TAB2.hashgeo
	And TAB1.hashdata = TAB2.hashdata				      
SET TAB1.situacao_id = 2, tab1.a_processar = 1
WHERE TAB1.situacao_id = 1;


Update Pessoa p 
Inner Join registrolocalizacao rl on p.id = rl.pessoa_id
Set p.situacao_id = 2
where rl.a_processar=1;


Insert INTO HistoricoSituacao (Data, hashdata, pessoa_id, situacao_id)
Select Data, hashdata, pessoa_id, situacao_id
From registrolocalizacao rl
Where rl.a_processar=1;

CALL pessoas_a_processar();


Alter table registrolocalizacao drop column a_processar;


Commit;

Call propagacao();

Call teste();
select * from registrolocalizacao;