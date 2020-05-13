
SELECT pessoa_id, hashgeo
	FROM RegistroLocalizacao 
	WHERE situcao_id=3 
	AND _updated >= (DATE_SUB(CURDATE(),INTERVAL 15 DAY);