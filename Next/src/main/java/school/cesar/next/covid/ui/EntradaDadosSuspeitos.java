package school.cesar.next.covid.ui;

import school.cesar.next.covid.db.EntradaCasoSuspeito;
import school.cesar.next.covid.db.Pessoa;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.UUID;

public class EntradaDadosSuspeitos {
	private Scanner scanner;

	public EntradaDadosSuspeitos(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void run() {
		try {
			System.out.println("Entrada de casos suspeitos");
			//System.out.println("Informe o id do paciente");
			String id = UUID.randomUUID().toString();
			//String id = scanner.next();
			System.out.println("Informe a data de infeccao de " + id);
			Pessoa pessoa = new Pessoa();
			pessoa.id = id;
			String txtdata = scanner.next();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date date = new java.sql.Date(sdf.parse(txtdata).getTime());
			System.out.println(date);
			new EntradaCasoSuspeito().adicionarPessoa(pessoa);
			new EntradaCasoSuspeito().adicionarHistorico(pessoa,date);
		} catch (ParseException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
