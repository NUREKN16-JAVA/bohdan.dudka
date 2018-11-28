package ua.nure.kn.dudka.usermanagment.db;

public class DaoFactoryImpl extends DAOFactory {
    @Override
    public UserDAO getUserDAO() throws ReflectiveOperationException {
        UserDAO userDAO = null;
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
