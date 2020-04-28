package dao;

import model.Historicosituacao;
import model.Registrolocalizacao;

public class RegistrolocalizacaoDao <A> extends GenericDao<Registrolocalizacao, Long> {
    public RegistrolocalizacaoDao() {
        super(Registrolocalizacao.class);
    }
}
