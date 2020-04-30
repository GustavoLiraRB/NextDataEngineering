package dao;

import java.util.Date;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import model.Pessoa;
import model.Registrolocalizacao;
import model.Situacao;

public class RegistrolocalizacaoDao <A> extends GenericDao<Registrolocalizacao, Long> {
    public RegistrolocalizacaoDao() {
        super(Registrolocalizacao.class);
    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean marcarInfectado (Pessoa pessoa, Date data, int dias) {
		Date dataIncio = new Date(data.getTime() - (1000 * 60 * 60 * 24 * dias));
		Date dataFim = new Date(data.getTime() + (1000 * 60 * 60 * 24 * dias));
		SituacaoDao daoS = new SituacaoDao();
		Situacao situacao = (Situacao) daoS.encontrar(0);
		
		Query query = entityManager.createQuery("UPDATE Registrolocalizacao SET situacao = :situacao " +
			      "WHERE pessoa = :pessoa AND data BETWEEN :inicio AND :fim");
		
		query.setParameter("situacao", situacao);
		query.setParameter("pessoa", pessoa);
		query.setParameter("inicio", dataIncio, TemporalType.DATE);
		query.setParameter("fim", dataFim, TemporalType.DATE);
		query.executeUpdate();
//		int updateCount = query.executeUpdate();
		
//		System.out.println(updateCount + " registros modificados");
//		
		boolean resultado = false;
//		if (updateCount > 0) {
//			resultado = true;
//		}
	    
		System.out.println("Dados modificados: " + resultado);
	    return resultado;
	}
    
}
