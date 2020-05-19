package main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.PessoaDao;
import dao.RegistrolocalizacaoDao;
import model.Pessoa;
import school.cesar.next.covid.dummydata.routes.DbDummyDataGenMain;

import javax.persistence.*;
import javax.persistence.EntityManager;

public class Main {

	private static EntityManager entityManager = getEntityManager();

	private static EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("next");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}


	public static void main(String[] args) {
		
		 Scanner sc = new Scanner(System.in);
		 System.out.println("Bem vindo ao Covid-NEXT");
		 while (true) {
			 System.out.println("Tecle:");
			 System.out.println("0 - para sair");
			 System.out.println("1 - para preencher banco com dados simulados (as rotas devem estar no C:)");
			 System.out.println("2 - para marcar pessoa como confirmado");
//			 System.out.println("3 - para inserir ponto manual pessoa");
             System.out.println("4 - para atualizar os registros");
			 int opcao = sc.nextInt();
			 switch (opcao) {
			 case 0:
				 System.exit(0);
				 break;
			 case 1:
				 System.out.println("Selecione a rota");
				 System.out.println("1 - pcr-pilar");
				 System.out.println("2 - bv-pingo-beleleu");
				 System.out.println("3 - 13maio-jbarros");
				 int rota = sc.nextInt();
				 System.out.println("Digite a quantidade de pessoas para rastreamento");
				 int qtdPessoas = sc.nextInt();
				 for (int i = 0; i < qtdPessoas; i++) {
					 DbDummyDataGenMain tc = new DbDummyDataGenMain();
					 tc.rota = rota;
					 Thread t2 = new Thread(tc);
					 t2.setName("Pessoa " + (i+1));
				     t2.start();
				}
				 
				 //new EntradaDadosSuspeitos(sc).run();
				 break;
			 case 2:
				 PessoaDao daoP = new PessoaDao();
				 List<Pessoa> lista = new ArrayList<Pessoa>();
				 lista = daoP.getList();

				 for (int i = 0; i < lista.size(); i++) {
					System.out.println(i+1 + " - Pessoa ID: " + lista.get(i).getPessoaId());
				 }
				 System.out.println("Escolha a pessoa pelo número");
				 int pessoaID = sc.nextInt();
				 Pessoa pessoa = lista.get(pessoaID - 1);
				 
				 System.out.println("Digite a data de confirmação (DD/MM/AAAA)");
				 String dataScanner = sc.next();
//				 String dataScanner = "08/01/2020";
				 DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				 Date data = null;
				 
				 try {
					data = df.parse(dataScanner);
				 } catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				 }
				 
				 System.out.println("Digite quantos dias antes e depois deve ser marcado como contaminado");
				 int dias = sc.nextInt();
//				 int dias = 1;
				 
				 RegistrolocalizacaoDao daoR = new RegistrolocalizacaoDao();
				 boolean resultado = false;
				 if (data != null) {
					 resultado = daoR.marcarInfectado(pessoa, data, dias);
				 }
				 System.out.println("Dados inseridos: " + resultado);
				 break;
			 case 4:
				 
			 	entityManager = getEntityManager();

                 entityManager.getTransaction().begin();
                 StoredProcedureQuery query = entityManager.createStoredProcedureQuery("atualizacaoRegistro");

                 query.execute();

                 entityManager.getTransaction().commit();
                 System.out.println("Registros Atualizados");

                 break;

			 }
		 }
	}


}
