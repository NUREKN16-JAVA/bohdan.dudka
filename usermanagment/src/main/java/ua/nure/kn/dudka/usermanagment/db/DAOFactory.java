package ua.nure.kn.dudka.usermanagment.db;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class DAOFactory {
    private final Properties properties;

    DAOFactory () {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionFactory createConnection () {
        String user = properties.getProperty("connection.user");
        String password = properties.getProperty("connection.password");
        String url = properties.getProperty("connection.url");
        String driver = properties.getProperty("connection.driver");

        return new ConnectionFactoryImpl(user, password, url, driver);
    }

    public UserDAO getUserDAO () throws ReflectiveOperationException {
        UserDAO result = null;
        try {
            Class UserDOAClass = Class.forName(properties.getProperty("dao.ua.nure.kn.dudka.usermanagment.db.UserDAO"));
            UserDAO userDAO = (UserDAO) UserDOAClass.newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new ReflectiveOperationException(e);
        }

        return result;
    }
}
