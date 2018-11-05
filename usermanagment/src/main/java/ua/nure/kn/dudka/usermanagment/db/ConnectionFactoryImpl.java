package ua.nure.kn.dudka.usermanagment.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {
    @Override
    public Connection createConnection() throws DataBaseException {
        String driver = "org.hsqldb.jdbcDriver";
        String url = "jdbc:hsqldb:file:db/usermanagment";
        String user = "sa";
        String password = "";

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DataBaseException(e.toString());
        }
    }
}
