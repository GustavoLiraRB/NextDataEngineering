insert into NextDataEngineering.Situacao(id, descricao)
Values (1, "saudável");

insert into NextDataEngineering.Situacao(id, descricao)
Values (2, "suspeito");

insert into NextDataEngineering.Situacao(id, descricao)
Values (3, "confirmado");

insert into NextDataEngineering.Situacao(id, descricao)
Values (4, "curado");

insert into NextDataEngineering.Pessoa (id, Pessoa, situacao_id)
Values (UUID(), "Ricardo", 1);

insert into NextDataEngineering.HistoricoSituacao (pessoa_id, Data, situacao_id)
Values ("fe60e973-8020-11ea-a785-00ad52ab6669", current_date(), 1);
