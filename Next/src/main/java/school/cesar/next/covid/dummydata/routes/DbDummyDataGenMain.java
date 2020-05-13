package school.cesar.next.covid.dummydata.routes;

import school.cesar.next.covid.db.ConexaoLocal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;

public class DbDummyDataGenMain {
	public static final long DOIS_MINUTOS = 2*60*1000;
	public static final Integer Pontos = 100001;
	public static void main(String[] args) throws SQLException {
		ConexaoLocal conexaoLocal = new ConexaoLocal();
		Connection connection = conexaoLocal.getconnection();

		String userId = UUID.randomUUID().toString();
		

		PreparedStatement insertPessoa = connection.prepareStatement("Insert into Pessoa (id, situacao_id) values (?,?)");
		insertPessoa.setString(1, userId);
		insertPessoa.setInt(2, 1);//saudável
		insertPessoa.executeUpdate();
		insertPessoa.close();
		
		DbDummyDataProcessor proc = new DbDummyDataProcessor(userId, connection);
		
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2019);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, 0);
		DummyDataGenerator gen = new DummyDataGenerator("pcr-pilar", cal.getTime(), DOIS_MINUTOS, Pontos, proc);
		gen.run();

	}
}
