DELIMITER $$
CREATE PROCEDURE AtualizarRegistrosFuturos(in pessoa varchar(255), in dataCruzamento int)
BEGIN

DECLARE datasFuturas int;
DECLARE done boolean;

DECLARE curDatasFuturas CURSOR FOR
	Select hashdata from registrolocalizacao where pessoa_id = pessoa and hashdata > dataCruzamento and pessoa_id in (
            Select pessoa_id from registrolocalizacao where a_processar=1);

DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

	OPEN curDatasFuturas;
    
    forDatasFuturas: WHILE done = FALSE DO
		FETCH curDatasFuturas into datasFuturas;
            
		UPDATE registrolocalizacao
		SET situacao_id = 2
		WHERE pessoa_id = pessoa AND hashdata=datasFuturas;
			
            
	END WHILE forDatasFuturas;
    
	CLOSE curDatasFuturas;



END $$
DELIMITER ;



DELIMITER $$
CREATE PROCEDURE verificarPessoasAlteradas()
BEGIN

DECLARE pessoa varchar(255);
DECLARE dataCruzamento int;
DECLARE done boolean;

DECLARE curPessoasAlteradas CURSOR FOR
	SELECT pessoa_id,hashdata from registrolocalizacao where a_processar=1;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    
    OPEN curPessoasAlteradas;
    
    forPessoasAlteradas: WHILE done = FALSE DO
    
		FETCH curPessoasAlteradas into pessoa,dataCruzamento;
		CALL AtualizarRegistrosFuturos(pessoa,dataCruzamento);
        
    END WHILE forPessoasAlteradas;
    
    CLOSE curPessoasAlteradas;


END $$
DELIMITER ;



#Select * from registrolocalizacao where pessoa_id='7f4078ec-8ae1-11ea-bc55-0242ac130003';
#SELECT hashdata from registrolocalizacao where a_processar=1 and pessoa_id='7f4078ec-8ae1-11ea-bc55-0242ac130003';

#IF (Select hashdata from registrolocalizacao where pessoa_id = '7f4078ec-8ae1-11ea-bc55-0242ac130003' and pessoa_id in (
            #Select pessoa_id from registrolocalizacao where a_processar=1)) > (SELECT hashdata from registrolocalizacao where a_processar=1 and pessoa_id='7f4078ec-8ae1-11ea-bc55-0242ac130003') THEN
            #UPDATE registrolocalizacao SET a_processar = 1 WHERE hashdata= and pessoa_id='7f4078ec-8ae1-11ea-bc55-0242ac130003';
            
            

#Select hashdata from registrolocalizacao where pessoa_id = '7f4078ec-8ae1-11ea-bc55-0242ac130003' and hashdata > 5294191 and pessoa_id in (
            #Select pessoa_id from registrolocalizacao where a_processar=1);
            
#UPDATE registrolocalizacao
		#SET situacao_id = 2
		#WHERE pessoa_id = '7f4078ec-8ae1-11ea-bc55-0242ac130003' AND hashdata=5294479;

#Select * from registrolocalizacao;



