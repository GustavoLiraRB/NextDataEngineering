package school.cesar.next.covid.db;

import java.sql.*;

public class BuscarPessoas extends ConexaoLocal {
    private ConexaoLocal conexaoLocal = new ConexaoLocal();
    private Connection connection = conexaoLocal.getconnection();

    public BuscarPessoas() throws SQLException {
    }

    public void lerpessoa() {

        try{
            PreparedStatement lerPessoa = connection.prepareStatement("select id from NextDataEngineering.Pessoa LIMIT 50");

            ResultSet rs = lerPessoa.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");

                System.out.println(id);
            }
            lerPessoa.close();

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }


}
