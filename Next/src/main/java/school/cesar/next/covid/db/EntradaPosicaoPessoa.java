package school.cesar.next.covid.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class EntradaPosicaoPessoa {
    private ConexaoLocal conexaoLocal = new ConexaoLocal();
    private Connection connection = conexaoLocal.getconnection();

    public EntradaPosicaoPessoa() throws SQLException{

    }

    public void adicionarRegistroLocalizacao(Pessoa pessoa, Date dataSuspeita, Double lat, Double lng, String hashgeo, Integer hashdata, Integer situacao) {
        String id = pessoa.id;
        Date data = dataSuspeita;
        try{
            PreparedStatement insertRegistroLocalizaca = connection.prepareStatement("insert into NextDataEngineering.RegistroLocalizacao (pessoa_id, Data,lat, lng, hashgeo, hashdata, situacao_id) values (?,?,?,?,?,?,?)");
            insertRegistroLocalizaca.setString(1, id);
            insertRegistroLocalizaca.setDate(2, (java.sql.Date) data);
            insertRegistroLocalizaca.setDouble(3, lat);
            insertRegistroLocalizaca.setDouble(4, lng);
            insertRegistroLocalizaca.setString(5, hashgeo);
            insertRegistroLocalizaca.setInt(6, hashdata);
            insertRegistroLocalizaca.setInt(7, situacao);
            insertRegistroLocalizaca.executeUpdate();
            insertRegistroLocalizaca.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
}
