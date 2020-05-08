package school.cesar.next.covid.dummydata.routes;

import java.math.BigInteger;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
import java.util.Date;

import ch.hsr.geohash.GeoHash;
import dao.PessoaDao;
import dao.RegistrolocalizacaoDao;
import dao.SituacaoDao;
import model.Pessoa;
import model.Registrolocalizacao;
import model.Situacao;

public class DbDummyDataProcessor implements DummyDataProcessor {
	private static final long CINCO_MINUTOS = 5*60*1000;
	//private Connection connection;
	private String userId;
	//private PreparedStatement insertStatement;


	public DbDummyDataProcessor(String userId) {
		this.userId = userId;
	}
	
	
	public void onData(DummyData data) {


		BigInteger hashHorario = BigInteger.valueOf(data.date.getTime() / CINCO_MINUTOS);

		SituacaoDao daoS = new SituacaoDao();
		Situacao situacao = new Situacao();
		situacao = (Situacao) daoS.encontrar(1);
		
		String hashLocal = GeoHash.geoHashStringWithCharacterPrecision(data.lat, data.lng, 10);
		Registrolocalizacao registro = new Registrolocalizacao(new java.sql.Date(data.date.getTime()), hashHorario, hashLocal, data.lat, data.lng, userId, situacao);
		
		RegistrolocalizacaoDao daoR = new RegistrolocalizacaoDao<>();
		daoR.salvar(registro);	
		
	}
}
