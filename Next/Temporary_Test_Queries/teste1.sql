DELIMITER $$
Create procedure percorrerPessoas(in pessoa_id varchar(255), in hashdata int)
BEGIN

DECLARE Datacruzamento int; 
DECLARE done BOOLEAN;
DECLARE curPessoaSuspeita 
		CURSOR FOR 
			Select hashdata FROM registrolocalizacao WHERE a_processar=1;#pessoas que necessitam ser mudadas e data de cruzmento
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
            
		OPEN curPessoaSuspeita;
        FETCH curPessoaSuspeita  into Datacruzamento ;   
		
        percorrerPessoas:  While done = FALSE DO
            FETCH curPessoaSuspeita  into Datacruzamento ;  
            
            IF hashdata > DataCruzamento  THEN 
        
			Update registrolocalizacao
			SET situacao_id=2
			WHERE pessoa_id=pessoa_id and hashdata=hashdata;
        
        END IF;
        END WHILE percorrerPessoas;
        
        
CLOSE curPessoaSuspeita;

END$$
DELIMITER ;




DELIMITER $$
Create procedure teste()
	BEGIN
    
    DECLARE PessoaVerificar varchar(255);
    DECLARE DataVerificar INT;
    DECLARE done BOOLEAN;
    
    
	DECLARE curVerificar
		CURSOR FOR
			Select pessoa_id, hashdata from registrolocalizacao where pessoa_id in (
            Select pessoa_id from registrolocalizacao where a_processar=1);#todas as datas dos ids anteriores
	
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
  
    OPEN curVerificar;
    
    atualizarSitucacao:  WHILE done = FALSE  DO 
		
        FETCH curVerificar into PessoaVerificar, DataVerificar;
        CALL percorrerPessoas(PessoaVerificar, DataVerificar);
       
                     
    END WHILE atualizarSitucacao;
	
    
    CLOSE curVerificar;
  
END$$
DELIMITER ;