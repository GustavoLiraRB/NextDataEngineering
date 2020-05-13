package school.cesar.next.covid.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoLocal extends ConexaoBanco {
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/NextDataEngineering?serverTimezone=UTC";
    private String user = "root";
    private String password = "inserirsenhalocal";

    public ConexaoLocal(){
       this.connection = connection;

    }

    @Override
    public Connection getconnection() throws SQLException {
        return this.connection= DriverManager.getConnection(url,user,password);
    }


}
