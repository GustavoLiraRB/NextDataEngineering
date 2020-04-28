package dao;

import model.Pessoa;
import model.Situacao;

public class SituacaoDao <A> extends GenericDao<Situacao, Long> {
    public SituacaoDao() {
        super(Situacao.class);
    }
}
