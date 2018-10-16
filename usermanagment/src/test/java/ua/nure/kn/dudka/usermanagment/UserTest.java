package ua.nure.kn.dudka.usermanagment;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class UserTest {
    private User user;
    private static final long ID = 1L;
    private static final String FIRSTNAME = "Иван";
    private static final String LASTNAME = "Иванов";
    private static final LocalDate DATEOFBIRTH = LocalDate.of(2014, 4, 29);

    @Before
    public void setUp() {
        user = new User(ID, FIRSTNAME, LASTNAME, DATEOFBIRTH);
    }

    @Test
    public void testGetFullName() {
        assertEquals("Иванов, Иван", user.getFullName());
    }

    @Test
    public void testGetAge() {
        assertEquals(2018 - 2014, user.getAge());
    }
}
