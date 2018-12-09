package ua.nure.kn.dudka.usermanagment.db;

import java.io.IOException;
import java.util.Properties;

/**
 * Class which realize factory for DAO. Realized with Singleton
 */
public abstract class DAOFactory {
    private static final String DAO_FACTORY = "dao.factory";
    protected static Properties properties;
    private  static DAOFactory instance;

    static {
        properties = new Properties();
        try {
            properties.load(DAOFactory.class.getClassLoader().getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor to read connection settings from file
     */
    protected DAOFactory () {}

    /**
     * Method to get current instance of DAOFactory object
     * @return Instance of DAOFactory to support Singleton
     */
    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            try {
                Class factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
                instance = (DAOFactory) factoryClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return instance;
    }

    public static void init (Properties properties) {
        DAOFactory.properties = properties;
        instance = null;
    }

    /**
     * Method to setup settings from file and pass them to ConnectionFactoryImpl
     * @return ConnectionFactoryImpl object
     */
    protected ConnectionFactory createConnection () {
               return new ConnectionFactoryImpl(properties);
    }

    /**
     * Creates new UserDAO object with set upped properties from file
     * @return UserDOA object
     * @throws ReflectiveOperationException
     */
   abstract public UserDAO getUserDAO () throws ReflectiveOperationException;
}
