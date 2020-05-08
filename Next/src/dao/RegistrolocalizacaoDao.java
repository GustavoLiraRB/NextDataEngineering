package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
		Situacao situacaoSaudavel = (Situacao) daoS.encontrar(0);
		Situacao situacaoSuspeito = (Situacao) daoS.encontrar(2);
		Situacao situacaoConfirmado = (Situacao) daoS.encontrar(1);
		
		String transmissaoId = UUID.randomUUID().toString();
		
	    entityManager.getTransaction().begin();

	    /*Query 1 marca como confirmado todos os registros de localização da pessoa
	      selecionada no periodo de dias estipulado antes e depois da confirmação atribuindo um id de transmissão.*/
	    System.out.println("Query 1...");
		Query query = entityManager.createQuery("UPDATE Registrolocalizacao SET situacao = :situacaoC, transmissaoId = :transmissaoId " +
			      "WHERE pessoaId = :pessoaId AND data BETWEEN :inicio AND :fim");
		
		query.setParameter("situacaoC", situacaoConfirmado);
		query.setParameter("transmissaoId", transmissaoId);
		query.setParameter("pessoaId", pessoa.getPessoaId());
		query.setParameter("inicio", dataIncio, TemporalType.DATE);
		query.setParameter("fim", dataFim, TemporalType.DATE);
		int updateCount = query.executeUpdate();
		System.out.println(updateCount + " registros modificados para confirmado");
	    entityManager.getTransaction().commit();
	    
	    entityManager.getTransaction().begin();
	    
	    //Query 2 seleciona todos pares (local, momento) do paciente confirmado da query 1.
	    System.out.println("Query 2...");
		query = entityManager.createQuery("SELECT DISTINCT CONCAT(:a, c.hashgeo, :a, ', ', c.hashdata)  FROM Registrolocalizacao c WHERE c.transmissaoId = :transmissaoId");
		
		query.setParameter("transmissaoId", transmissaoId);
		query.setParameter("a", "'");
		List<String> results = query.getResultList();
		System.out.println("Lista com: " + results.size());
		
		List<String> listatemp = new ArrayList<>();
		for (int i = 0; i < results.size(); i=i+5000){
			Stream<String> stream1 = results.stream();
			listatemp.clear();
			listatemp = stream1.skip(i).limit(5000).collect(Collectors.toList());
			
			/*Query 3 marca como suspeito todas as pessoas que estavam em algum par (local, momento) do paciente confirmado
			  atribuindo o mesmo id de transmissão.*/
		    System.out.println("Query 3..." + i);
			query = entityManager.createQuery("UPDATE Registrolocalizacao SET situacao = :situacaoSu, transmissaoId = :transmissaoId " +
				      "WHERE (hashgeo, hashdata) IN " + "((" + String.join("), (", listatemp) + ")) AND situacao = :situacaoSa" );
		    
			query.setParameter("transmissaoId", transmissaoId);
			query.setParameter("situacaoSu", situacaoSuspeito);
			query.setParameter("situacaoSa", situacaoSaudavel);
			
			updateCount = updateCount + query.executeUpdate();
		}
		
	    entityManager.getTransaction().commit();
		
	    entityManager.getTransaction().begin();
	    
	    //Query 4 seleciona todos registroslocalização marcados como suspeitos e com o id da transmissão.
	    System.out.println("Query 4...");
	    query = entityManager.createQuery("SELECT c  FROM Registrolocalizacao c WHERE c.transmissaoId = :transmissaoId AND c.situacao = :situacaoSu ORDER BY c.hashdata ASC");
	    
		query.setParameter("transmissaoId", transmissaoId);
		query.setParameter("situacaoSu", situacaoSuspeito);
		List<Registrolocalizacao> resultsSus = query.getResultList();
		System.out.println("Reg pontos" + resultsSus.size());
		
		//Filtragem da lista para selecionar para cada pessoa da lista apenas o primeiro e último encontro com o paciente confirmado.
		List<Registrolocalizacao> regSuspeitosUnic1 = new ArrayList<>();
		regSuspeitosUnic1.add(resultsSus.get(0));
		for (int i = 0; i < resultsSus.size(); i++) {
			for (int j = 0; j < regSuspeitosUnic1.size(); j++) {
				if (resultsSus.get(i).getPessoaId().equals(regSuspeitosUnic1.get(j).getPessoaId())) {
					j = regSuspeitosUnic1.size();
				}else if (j == regSuspeitosUnic1.size()-1) {
					regSuspeitosUnic1.add(resultsSus.get(i));
				}
			}
		}
		
		List<Registrolocalizacao> regSuspeitosUnic2 = new ArrayList<>();
		regSuspeitosUnic2.add(resultsSus.get(resultsSus.size()-1));
		for (int i = resultsSus.size(); i > 0; i--) {
			for (int j = 0; j < regSuspeitosUnic2.size(); j++) {
				if (resultsSus.get(i-1).getPessoaId().equals(regSuspeitosUnic2.get(j).getPessoaId())) {
					j = regSuspeitosUnic2.size();
				}else if (j == regSuspeitosUnic2.size()-1) {
					regSuspeitosUnic2.add(resultsSus.get(i-1));
				}
			}
		}
		
		regSuspeitosUnic1.addAll(regSuspeitosUnic2);
		System.out.println("regSuspeitosUnic: " + regSuspeitosUnic1.size());

		/*Cria uma lista de pares (pessoa, momento) a partir do primeiro e último encontro de cada pessoa com o paciente e
		  replica o momento dos encontros adiante até completar os dias estipulados.*/
		List<String> regSuspeitos = new ArrayList<>();
		int deltaHashData = dias * 288;		
		for (int i = 0; i < regSuspeitosUnic1.size(); i++) {
			for (int j = 0; j < deltaHashData; j++) {
				regSuspeitos.add("'" + regSuspeitosUnic1.get(i).getPessoaId() + "', " + (regSuspeitosUnic1.get(i).getHashdata().intValue() + j + 1));
			}
		}
		
	    System.out.println("Reg suspeitos: " + regSuspeitos.size());
	    
		List<String> listatemp1 = new ArrayList<>();
		for (int i = 0; i < regSuspeitos.size(); i=i+5000){
			Stream<String> stream1 = regSuspeitos.stream();
			listatemp1.clear();
			listatemp1 = stream1.skip(i).limit(5000).collect(Collectors.toList());
			//Query 5 marca todos os registros com pares (pessoa, momento) para suspeito e adiciona o id da transmissão.
		    System.out.println("Query 5..." + i);
			query = entityManager.createQuery("UPDATE Registrolocalizacao c SET c.situacao = :situacaoSu, c.transmissaoId = :transmissaoId " +
				      "WHERE (c.pessoaId, c.hashdata) IN " + "((" + String.join("), (", listatemp1) + ")) AND c.situacao = :situacaoSa");
			
			query.setParameter("transmissaoId", transmissaoId);
			query.setParameter("situacaoSu", situacaoSuspeito);
			query.setParameter("situacaoSa", situacaoSaudavel);
			
			updateCount = updateCount + query.executeUpdate();
		}

	    entityManager.getTransaction().commit();
	    
		System.out.println(updateCount + " registros modificados");
		
		boolean resultado = false;
		if (updateCount > 0) {
			resultado = true;
		}
	    
		System.out.println("Dados modificados: " + resultado);
	    return resultado;
	}
}
