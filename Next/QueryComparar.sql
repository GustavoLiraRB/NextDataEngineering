SELECT pessoa_id, lat, long, _updated INTO LocalizacaoBackUp
FROM RegistroLocalizacao

SELECT *
	FROM RegistroLocalizacao, LocalizacaoBackUp
	GROUP BY LocalizacaoBackUp.pessoa_id
	WHERE MINUTE(RegistroLocalizacao._updated)-MINUTE(LocalizacaoBackUp._update) <=30 AND 
		RegistroLocalizacao.lat-LocalizacaoBackUp.lat <X AND
		RegistroLocalizacao.long-LocalizacaoBackUp.long <Y AND NOT
		RegistroLocalizacao.pessoa_id=LocalizacaoBackUp.pessoa_id
        ;
;