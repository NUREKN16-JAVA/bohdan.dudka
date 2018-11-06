package ua.nure.kn.dudka.usermanagment.db;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.*;
import static org.junit.Assert.*;
import ua.nure.kn.dudka.usermanagment.User;

import java.time.LocalDate;
import java.util.Collection;

public class HsqlDBUserDAOTest extends DatabaseTestCase {
    private ConnectionFactory connectionFactory;
    private HsqlDBUserDAO dao;
    private final Long ID = 1L;
    private final String FIRSTNAME = "Иван";
    private final String LASTNAME = "Иванов";
    private final LocalDate DATEOFBIRTH = LocalDate.of(1998, 10, 20);


    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        connectionFactory = new ConnectionFactoryImpl();
        return  new DatabaseConnection(connectionFactory.createConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
        return dataSet;
    }

    @Before
    public void setUp() throws Exception {
        connectionFactory = new ConnectionFactoryImpl();
        dao = new HsqlDBUserDAO(connectionFactory);
    }

    @Test
    public void testCreate() {
        try {
            User user = new User();
            user.setFirstName(FIRSTNAME);
            user.setLastName(LASTNAME);
            user.setDateOfBirth(DATEOFBIRTH);
            assertNull(user.getId());
            user = dao.create(user);
            assertNotNull(user);
            assertNotNull(user.getId());
        } catch (DataBaseException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAll() {
        try{
            int ExpectedCollectionSize = 2;
            Collection AllUsers = dao.findAll();
            assertNotNull("Collection is null", AllUsers);
            assertEquals("Collection size.", ExpectedCollectionSize, AllUsers.size());
        } catch (DataBaseException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
