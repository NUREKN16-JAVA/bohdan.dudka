package ua.nure.kn.dudka.usermanagment.db;

import org.junit.*;

public class DAOFactoryTest {

    @Test
    public void getUserDAOTest () {
        DAOFactory daoFactory = DAOFactory.getInstance();
        Assert.assertNotNull(daoFactory);
        UserDAO userDAO;
        try {
            userDAO = daoFactory.getUserDAO();
            Assert.assertNotNull(userDAO);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
}
