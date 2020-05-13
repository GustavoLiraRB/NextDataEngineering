DELIMITER $$
Create procedure atualizacaoRegistro(in pessoa varchar(255), in data int)
BEGIN
DECLARE PessoaVerificar varchar(255);
DECLARE DataVerificar int; 
DECLARE done BOOLEAN;

DECLARE curAtualizar
		CURSOR FOR
			Select pessoa_id, hashdata from registrolocalizacao where pessoa_id in (
            Select pessoa_id from registrolocalizacao where a_processar=1);#todas as datas dos ids anteriores
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;	  
  
		OPEN curAtualizar;
          
		
        verificarDatas:  While done = FALSE DO
            FETCH curAtualizar  into DataVerificar,PessoaVerificar ;  
            
            IF DataVerificar > data 
            and PessoaVerificar=pessoa 
            THEN 
			Update registrolocalizacao
			SET situacao_id=2
			WHERE pessoa_id=PessoaVerificar and hashdata=DataVerificar;
        
        END IF;
        END WHILE verificarDatas;
        
        
CLOSE curAtualizar;

END$$
DELIMITER ;




DELIMITER $$
Create procedure pessoas_a_processar()
	BEGIN
    
    DECLARE PessoaSuspeita varchar(255);
    DECLARE DataCruzamento INT;
    DECLARE done BOOLEAN;
    
	DECLARE curPessoaSuspeita 
		CURSOR FOR 
			Select hashdata FROM registrolocalizacao WHERE a_processar=1;#pessoas que necessitam ser mudadas e data de cruzmento
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

	
 
    
  
    OPEN curPessoaSuspeita;
    
    pessoas_a_processar:  WHILE done = FALSE  DO 
		
        FETCH curPessoaSuspeita into PessoaSuspeita, DataCruzamento;
        CALL atualizacaoRegistro(PessoaSuspeita, DataCruzamento);
       
                     
    END WHILE pessoas_a_processar;
	
    
    CLOSE curPessoaSuspeita;
  
END$$
DELIMITER ;




