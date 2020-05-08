package school.cesar.next.covid.dummydata.routes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import dao.PessoaDao;
import dao.SituacaoDao;
import model.Pessoa;
import model.Situacao;

public class DbDummyDataGenMain extends Thread{
	public static final long DOIS_MINUTOS = 2*60*1000;
	public int rota;
	public void run() {
		//Connection connection = null; //conectar de verdade aqui
		String userId = UUID.randomUUID().toString();
		System.out.println();

		PessoaDao daoP = new PessoaDao();
		Pessoa pessoa = new Pessoa();
		pessoa.setPessoaId(userId);

		daoP.salvar(pessoa);
		
		String stringRota = "";
		switch (rota) {
		case 1:
			stringRota = "pcr-pilar";
			break;
		case 2:
			stringRota = "bv-pingo-beleleu";
			break;
		case 3:
			stringRota = "13maio-jbarros";
			break;
		}
		
//		SituacaoDao daoS = new SituacaoDao();
//		Situacao situacao = new Situacao();
//		situacao.setId(0);
//		situacao.setDescricao("Saudável");

//		daoS.salvar(situacao);
		
//		PreparedStatement insertPessoa = connection.prepareStatement("Insert into Pessoa (id) values (?)");		
//		insertPessoa.setString(1, userId);
//		insertPessoa.executeUpdate();
		
		
		DbDummyDataProcessor proc = new DbDummyDataProcessor(userId);
		
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2020);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		cal.set(Calendar.MONTH, 0);
		DummyDataGenerator gen = new DummyDataGenerator(stringRota, cal.getTime(), DOIS_MINUTOS, 131400l, proc);
		gen.run();
	}
}
