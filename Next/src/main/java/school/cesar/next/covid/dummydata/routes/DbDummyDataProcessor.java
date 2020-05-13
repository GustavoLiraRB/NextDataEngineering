package school.cesar.next.covid.dummydata.routes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ch.hsr.geohash.GeoHash;

public class DbDummyDataProcessor implements DummyDataProcessor {
	private static final long CINCO_MINUTOS = 5*60*1000;
	private Connection connection;
	private String userId;
	private PreparedStatement insertStatement;


	public DbDummyDataProcessor(String userId, Connection connection) throws SQLException {
		this.userId = userId;
		this.connection = connection;
		this.insertStatement = this.connection.prepareStatement("insert into registrolocalizacao (pessoa_id, lat, lng, hashgeo, data, hashdata, situacao_id) values (?, ?, ?, ?, ?, ?, ?)");

	}
	
	
	public void onData(DummyData data) {
		try {
		this.insertStatement.setString(1, this.userId);
		this.insertStatement.setDouble(2, data.lat);
		this.insertStatement.setDouble(3, data.lng);
		String hashLocal = GeoHash.geoHashStringWithCharacterPrecision(data.lat, data.lng, 10);
		this.insertStatement.setString(4, hashLocal);
		this.insertStatement.setDate(5, new java.sql.Date(data.date.getTime()));
		long hashHorario = data.date.getTime() / CINCO_MINUTOS;
		this.insertStatement.setLong(6, hashHorario);
		this.insertStatement.setInt(7, 1); //Saudavel
		this.insertStatement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
