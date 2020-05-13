package school.cesar.next.covid.ui;

import school.cesar.next.covid.db.AtualizarSituacao;
import school.cesar.next.covid.db.Pessoa;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class EntradaAtualizacaoSituacao {
    private Scanner scanner;
    public EntradaAtualizacaoSituacao(Scanner scanner) {
        this.scanner = scanner;
    }
    public void run() {
        try {
            System.out.println("Atualização da situação da pessoa");
            System.out.println("Qual o id da pessoa");
            String id = scanner.next();
            Pessoa pessoa = new Pessoa();
            pessoa.id = id;
            System.out.println("Informe a situação da pessoa:\n" +
                    "1 - para saudável,\n" +
                    "2 - para suspeito,\n" +
                    "3 - para confirmado,\n" +
                    "4 - para curado.");
            Integer situacao_id = scanner.nextInt();
            System.out.println("Informe a data da atualização da situação de "+id+".");
            String txtdata = scanner.next();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date date = new java.sql.Date(sdf.parse(txtdata).getTime());
            System.out.println(date);


            AtualizarSituacao AtualizarSituacao = new AtualizarSituacao();
            Boolean SituacaoAtualizada = AtualizarSituacao.atualizarSituacao(situacao_id,pessoa);
            if  (SituacaoAtualizada  == true){
                new AtualizarSituacao().atualizarHistorico(date,pessoa,situacao_id);
                System.out.println("A situação da pessoa " + pessoa.id + " foi atualizada");
            }

        } catch (ParseException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
