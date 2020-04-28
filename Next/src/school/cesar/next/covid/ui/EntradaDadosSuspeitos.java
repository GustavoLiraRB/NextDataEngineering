package school.cesar.next.covid.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import school.cesar.next.covid.db.EntradaCasoSuspeito;
import school.cesar.next.covid.db.Pessoa;

public class EntradaDadosSuspeitos {
	private Scanner scanner;

	public EntradaDadosSuspeitos(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void run() {
		try {
			System.out.println("Entrada de casos suspeitos");
			System.out.println("Informe o id do paciente");
			String id = scanner.next();
			System.out.println("Informe a data de infeccao de " + id);
			Pessoa pessoa = new Pessoa();
			pessoa.id = id;
			String txtdata = scanner.next();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date = sdf.parse(txtdata);
			System.out.println(date);
			new EntradaCasoSuspeito(null).adicionar(pessoa, date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
