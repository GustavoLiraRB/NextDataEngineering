package dao;

import model.Limitesdeslocamento;
import model.Registrolocalizacao;

public class LimitesdeslocamentoDao <A> extends GenericDao<Limitesdeslocamento, Long> {
    public LimitesdeslocamentoDao() {
        super(Limitesdeslocamento.class);
    }
}
