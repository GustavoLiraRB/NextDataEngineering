SELECT hashdata FROM registrolocalizacao WHERE a_processar = 1;

/*SELECT hashdata 
    FROM registrolocalizacao 
    WHERE hashdata > any(SELECT hashdata 
                         FROM registrolocalizacao 
                         WHERE a_processar = 1 group by hashdata);*/

#seleciona os ids que foram modificados
Select pessoa_id from registrolocalizacao where a_processar=1;

#seleciona todas as linhas dos ids que foram modificados
Select * from registrolocalizacao where pessoa_id in (Select pessoa_id from registrolocalizacao where a_processar=1);

/*For pessoa_id in (Select pessoa_id from registrolocalizacao where a_processar=1)
If hashdata > SELECT hashdata FROM registrolocalizacao WHERE a_processar = 1 and pessoa_id=pessoa_id
Then set situacao_id=2
ELSE situcao_id=situacao_id;*/

DELIMITER $$
Create procedure atualizacaoRegistro()
	BEGIN
    DECLARE Pessoa varchar(255);
    DECLARE Hdata INT;
    DECLARE curPessoaId 
		CURSOR FOR 
			Select pessoa_id FROM registrolocalizacao WHERE a_processar=1;
	DECLARE curHashdata
		CURSOR FOR
			SELECT hashdata FROM registrolocalizacao WHERE a_processar = 1;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    
	OPEN curPessoaId;
    OPEN curHashdata;
    
    getPessoaID: LOOP
		FETCH curPessoaId into Pessoa;
        FETCH curHashdata into DataCruzamento; 
        
        Select hashdata from registrolocalizacao where pessoaID=Pessoa;
        IF hashdata > DataCruzamento THEN 
        Select * from registrolocaliacao where pessoa_id=Pessoa and hashdata=hashdata;
        SET situacao_id=2;
        END IF;
        
    END LOOP getPessoaID;
	
	/*DECLARE ids = (pessoa_id in (Select pessoa_id FROM registrolocalizacao WHERE a_processar=1);
		WHILE (pessoa_id in (Select pessoa_id FROM registrolocalizacao WHERE a_processar=1)) DO
			IF (hashdata > (SELECT hashdata FROM registrolocalizacao WHERE a_processar = 1)) THEN
				SET situacao_id=2;
			END IF;
		END WHILE;*/
	
    CLOSE curPessoaId;
    CLOSE curHashdata;
END$$
DELIMITER ;


#exemplo cursor
/*DELIMITER $$
CREATE PROCEDURE createEmailList (
	INOUT emailList varchar(4000)
)
BEGIN
	DECLARE finished INTEGER DEFAULT 0;
	DECLARE emailAddress varchar(100) DEFAULT "";

	-- declare cursor for employee email
	DEClARE curEmail 
		CURSOR FOR 
			SELECT email FROM employees;

	-- declare NOT FOUND handler
	DECLARE CONTINUE HANDLER 
        FOR NOT FOUND SET finished = 1;

	OPEN curEmail;

	getEmail: LOOP
		FETCH curEmail INTO emailAddress;
		IF finished = 1 THEN 
			LEAVE getEmail;
		END IF;
		-- build email list
		SET emailList = CONCAT(emailAddress,";",emailList);
	END LOOP getEmail;
	CLOSE curEmail;

END$$
DELIMITER ;*/



#seleciona as linhas das pessoas cuja situação foi modificada mas que contém datas ainda não modificadas
Select * from (Select * from registrolocalizacao where pessoa_id in (Select pessoa_id from registrolocalizacao where a_processar=1)) as tab where a_processar=0;

#seleciona os ids das pessoas cuja situação foi modificada mas que contém datas ainda não modificadas
Select pessoa_id from (Select * from registrolocalizacao where pessoa_id in (Select pessoa_id from registrolocalizacao where a_processar=1)) as tab where a_processar=0;



#seleciona a linha para atualizar
Select * from registrolocalizacao where pessoa_id='7f4078ec-8ae1-11ea-bc55-0242ac130003' and hashdata > 
(select hashdata from registrolocalizacao where pessoa_id='7f4078ec-8ae1-11ea-bc55-0242ac130003' and a_processar=1);


Select pessoa_id from (SELECT pessoa_id 
    FROM registrolocalizacao 
    WHERE hashdata > any(SELECT hashdata 
                         FROM registrolocalizacao 
                         WHERE a_processar = 1 group by hashdata)) as tab
                         Where pessoa_id in (Select pessoa_id from registrolocalizacao where a_processar=1);


Select * from registrolocalizacao where pessoa_id='7f4078ec-8ae1-11ea-bc55-0242ac130003';
Select * from registrolocalizacao where pessoa_id='1ccccf90-8fa4-11ea-bc55-0242ac130003';