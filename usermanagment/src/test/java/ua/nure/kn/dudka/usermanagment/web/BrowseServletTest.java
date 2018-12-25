package ua.nure.kn.dudka.usermanagment.web;

import org.junit.Test;
import ua.nure.kn.dudka.usermanagment.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BrowseServletTest extends MockServletTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }

    @Test
    public void testBrowse() {
        User user = new User(1000L, "John", "Doe", LocalDate.now());
        List list = Collections.singletonList(user);
        getMockUserDao().expectAndReturn("findAll", list);

        doGet();

        Collection sessionCollection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
        assertNotNull("Could not find users list in session!", sessionCollection);
        assertSame(list, sessionCollection);
    }

    @Test
    public void testEdit() {
        User user = new User(1000L, "John", "Doe", LocalDate.now());

        getMockUserDao().expectAndReturn("find", 1000L, user);
        addRequestParameter("editButton", "edit");
        addRequestParameter("id", "1000");
        doPost();
        User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull("Could not find user", user);
        assertSame(user, userInSession);
    }
}
