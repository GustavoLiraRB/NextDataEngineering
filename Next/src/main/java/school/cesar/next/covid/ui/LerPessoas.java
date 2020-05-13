package school.cesar.next.covid.ui;

import school.cesar.next.covid.db.BuscarPessoas;

import java.sql.SQLException;
import java.util.Scanner;


public class LerPessoas {
    private Scanner scanner;

    public LerPessoas(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run(){
        try {
            System.out.println("pessoas");

            new BuscarPessoas().lerpessoa();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}