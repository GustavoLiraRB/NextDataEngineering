package dao;

import model.Historicosituacao;

public class HistoricosituacaoDao <A> extends GenericDao<Historicosituacao, Long> {
    public HistoricosituacaoDao() {
        super(Historicosituacao.class);
    }
}
