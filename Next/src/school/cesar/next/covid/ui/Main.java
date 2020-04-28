package school.cesar.next.covid.ui;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 System.out.println("Bem vindo ao Covid-NEXT");
		 while (true) {
			 System.out.println("Tecle:");
			 System.out.println("0 - para sair");
			 System.out.println("1 - para entrada de casos suspeitos");
			 System.out.println("2 - para exibir detalhes pessoa");
			 System.out.println("3 - para inserir ponto manual pessoa");
			 int opcao = sc.nextInt();
			 switch (opcao) {
			 case 0:
				 System.exit(0);
				 break;
			 case 1:
				 new EntradaDadosSuspeitos(sc).run();
				 break;
			 case 2:
				 
			 }
		 }
	}
}
