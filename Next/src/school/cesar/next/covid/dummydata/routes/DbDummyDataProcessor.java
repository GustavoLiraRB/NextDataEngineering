package school.cesar.next.covid.dummydata.routes;

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
		//this.connection = connection;
		//this.insertStatement = this.connection.prepareStatement("insert into HistoricoLocalizacao (pessoa_id, lat, lng, hashlocal, data, hashdata, situacao) values (?, ?, ?, ?, ?, ?, ?)");
		
	}
	
	
	public void onData(DummyData data) {
//		try {
//		this.insertStatement.setString(1, this.userId);
//		this.insertStatement.setDouble(2, data.lat);
//		this.insertStatement.setDouble(3, data.lng);
//		String hashLocal = GeoHash.geoHashStringWithCharacterPrecision(data.lat, data.lng, 10);
//		this.insertStatement.setString(4, hashLocal);
//		this.insertStatement.setDate(5, new java.sql.Date(data.date.getTime()));
		long hashHorario = data.date.getTime() / CINCO_MINUTOS;
		String stringhashHorario = String.valueOf(hashHorario);
//		this.insertStatement.setLong(6, hashHorario);
//		this.insertStatement.setInt(7, 0); //Saudavel
//		this.insertStatement.executeUpdate();
//		
//
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//		
		SituacaoDao daoS = new SituacaoDao();
		Situacao situacao = new Situacao();
		situacao = (Situacao) daoS.encontrar(1);
		PessoaDao daoP = new PessoaDao();
		Pessoa pessoa = new Pessoa();
		pessoa = (Pessoa) daoP.encontrar(userId);
		
		String hashLocal = GeoHash.geoHashStringWithCharacterPrecision(data.lat, data.lng, 10);
		Registrolocalizacao registro = new Registrolocalizacao(new java.sql.Date(data.date.getTime()), stringhashHorario, hashLocal, data.lat, data.lng, pessoa, situacao);
		
		RegistrolocalizacaoDao daoR = new RegistrolocalizacaoDao<>();
		daoR.salvar(registro);
		
		
	}

}
