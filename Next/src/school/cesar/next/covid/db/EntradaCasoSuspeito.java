package school.cesar.next.covid.db;

import java.sql.Connection;
import java.util.Date;

public class EntradaCasoSuspeito {
	private Connection connection;

	public EntradaCasoSuspeito(Connection connection) {
		this.connection = connection;
	}
	
	public void adicionar(Pessoa pessoa, Date dataSuspeita) {
		
	}
}
