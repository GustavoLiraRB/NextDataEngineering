package school.cesar.next.covid.ui;

import ch.hsr.geohash.GeoHash;
import school.cesar.next.covid.db.EntradaPosicaoPessoa;
import school.cesar.next.covid.db.Pessoa;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class EntradaPontoManual {
    private Scanner scanner;
    public EntradaPontoManual(Scanner scanner) {
        this.scanner = scanner;
    }
    public void run() {
        try {
            System.out.println("Entrada de localização da pessoa");
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
            System.out.println("Informe a data em que "+id+" se encontrava na localização.");
            String txtdata = scanner.next();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date date = new java.sql.Date(sdf.parse(txtdata).getTime());
            System.out.println(date);
            System.out.println("Qual a latitude");
            Double lat = scanner.nextDouble();
            System.out.println("Qual a longitude");
            Double lng = scanner.nextDouble();
            String hashgeo = GeoHash.geoHashStringWithCharacterPrecision(lat,lng,10);
            Integer hashdata = date.hashCode();

            new EntradaPosicaoPessoa().adicionarRegistroLocalizacao(pessoa,date, lat,lng,hashgeo, hashdata, situacao_id);
        } catch (ParseException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
