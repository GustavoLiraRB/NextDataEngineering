CREATE INDEX idx_situacao ON NextDataEngineering.Situacao(id);

CREATE INDEX idx_pessoa ON NextDataEngineering.Pessoa(id);

CREATE INDEX idx_geolocalizacao ON NextDataEngineering.RegistroLocalizacao(hashgeo);

CREATE INDEX idx_data ON NextDataEngineering.HistoricoSituacao(Data);

CREATE INDEX idx_geolocalizacao ON NextDataEngineering.LimitesDeslocamento(hashgeo);


