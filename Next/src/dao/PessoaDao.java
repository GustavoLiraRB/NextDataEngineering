package dao;

import model.Historicosituacao;
import model.Pessoa;

public class PessoaDao <A> extends GenericDao<Pessoa, Long> {
    public PessoaDao() {
        super(Pessoa.class);
    }
}
