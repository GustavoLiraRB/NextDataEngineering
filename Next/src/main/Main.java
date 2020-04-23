package main;

import dao.HistoricosituacaoDao;
import model.Historicosituacao;
import model.Pessoa;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Teste banco
		Historicosituacao his = new Historicosituacao();
		his.setPessoa("João");
		
		System.out.println(his + his.getPessoa());
		
		HistoricosituacaoDao dao = new HistoricosituacaoDao();
		dao.salvar(his);
	}

}
