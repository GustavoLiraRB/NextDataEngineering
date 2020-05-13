package school.cesar.next.covid.db;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConexaoBanco {
    public abstract Connection getconnection() throws SQLException;
}