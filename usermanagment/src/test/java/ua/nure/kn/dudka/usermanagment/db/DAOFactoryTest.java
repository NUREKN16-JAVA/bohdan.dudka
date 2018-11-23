package ua.nure.kn.dudka.usermanagment.db;

import org.junit.*;

/**
 * Test class to test DAOFactory connection
 */
public class DAOFactoryTest {

    /**
     * Test if DAOFactory creates new connection with parameters from file
     */
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
