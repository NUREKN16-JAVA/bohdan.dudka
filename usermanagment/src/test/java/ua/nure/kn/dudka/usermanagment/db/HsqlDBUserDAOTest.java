package ua.nure.kn.dudka.usermanagment.db;

import org.junit.*;
import static org.junit.Assert.*;
import ua.nure.kn.dudka.usermanagment.User;

import java.time.LocalDate;

public class HsqlDBUserDAOTest {
    private HsqlDBUserDAO dao;
    private final Long ID = 1L;
    private final String FIRSTNAME = "Иван";
    private final String LASTNAME = "Иванов";


    @Before
    public void setUp() throws Exception {
        dao = new HsqlDBUserDAO();
    }

    @Test
    public void testCreate() {
        try {
            LocalDate date = LocalDate.now().withYear(1998);
            User user = new User();
            user.setFirstName(FIRSTNAME);
            user.setLastName(LASTNAME);
            user.setDateOfBirth(date);
            assertNull(user.getId());
            user = dao.create(user);
            assertNotNull(user);
            assertNotNull(user.getId());
        } catch (DataBaseException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
}
