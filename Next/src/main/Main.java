package main;

import java.util.Scanner;

import school.cesar.next.covid.dummydata.routes.DbDummyDataGenMain;


public class Main {
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 System.out.println("Bem vindo ao Covid-NEXT");
		 while (true) {
			 System.out.println("Tecle:");
			 System.out.println("0 - para sair");
			 System.out.println("1 - para preencher banco com dados simulados");
//			 System.out.println("2 - para exibir detalhes pessoa");
//			 System.out.println("3 - para inseri ponto manual pessoa");
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
				 
			 }
		 }
	}
}
