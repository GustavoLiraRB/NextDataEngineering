package school.cesar.next.covid.db;

import java.sql.*;
import java.util.Date;

public class EntradaCasoSuspeito{
	private ConexaoLocal conexaoLocal = new ConexaoLocal();
	private Connection connection = conexaoLocal.getconnection();

	public EntradaCasoSuspeito() throws SQLException {
	}

	public void adicionarPessoa(Pessoa pessoa) {
		String id = pessoa.id;
		try{
			PreparedStatement insertPessoa = connection.prepareStatement("insert into NextDataEngineering.Pessoa (id, situacao_id) values (?,?)");
			insertPessoa.setString(1, id);
			insertPessoa.setInt(2, 2);
			insertPessoa.executeUpdate();
			insertPessoa.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}

	public void adicionarHistorico(Pessoa pessoa, Date dataSuspeita) {
		String id = pessoa.id;
		Date data = dataSuspeita;
		try{
			PreparedStatement insertHistorico = connection.prepareStatement("insert into NextDataEngineering.HistoricoSituacao (pessoa_id, Data, situacao_id) values (?,?,?)");
			insertHistorico.setString(1, id);
			insertHistorico.setDate(2, (java.sql.Date) data);
			insertHistorico.setInt(3, 2);
			insertHistorico.executeUpdate();
			insertHistorico.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
}

