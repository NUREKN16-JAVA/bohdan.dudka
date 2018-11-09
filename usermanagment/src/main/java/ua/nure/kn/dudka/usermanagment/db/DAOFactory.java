package ua.nure.kn.dudka.usermanagment.db;

import java.io.IOException;
import java.util.Properties;

/**
 * Class which realize factory for DAO. Realized with Singleton
 */
public class DAOFactory {
    private final Properties properties;
    private final static DAOFactory INSTANCE = new DAOFactory();
    private UserDAO userDAO;

    /**
     * Constructor to read connection settings from file
     */
    DAOFactory () {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to get current instance of DAOFactory object
     * @return Instance of DAOFactory to support Singleton
     */
    public static DAOFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Method to setup settings from file and pass them to ConnectionFactoryImpl
     * @return ConnectionFactoryImpl object
     */
    private ConnectionFactory createConnection () {
        String user = properties.getProperty("connection.user");
        String password = properties.getProperty("connection.password");
        String url = properties.getProperty("connection.url");
        String driver = properties.getProperty("connection.driver");

        return new ConnectionFactoryImpl(user, password, url, driver);
    }

    /**
     * Creates new UserDAO object with set upped properties from file
     * @return UserDOA object
     * @throws ReflectiveOperationException
     */
    public UserDAO getUserDAO () throws ReflectiveOperationException {
        try {
            Class UserDOAClass = Class.forName(properties.getProperty("dao.ua.nure.kn.dudka.usermanagment.db.UserDAO"));
            userDAO = (UserDAO) UserDOAClass.newInstance();
            userDAO.setConnectionFactory(createConnection());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new ReflectiveOperationException(e);
        }

        return userDAO;
    }
}
