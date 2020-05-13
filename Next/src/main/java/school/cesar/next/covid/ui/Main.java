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
			System.out.println("3 - para inserir a localização de uma pessoa manualmente");
			System.out.println("4 - para ler o id das pessoas");
            System.out.println("5 - para atualizar a situação de uma pessoa");
			int opcao = sc.nextInt();
			switch (opcao) {
				case 0:
					System.exit(0);
					break;
				case 1:
					new EntradaDadosSuspeitos(sc).run();
					break;
				case 3:
					new EntradaPontoManual(sc).run();
					break;
				case 4:
					new LerPessoas(sc).run();
					break;
                case 5:
                    new EntradaAtualizacaoSituacao(sc).run();
                    break;
			}
		}
	}
}