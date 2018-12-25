package ua.nure.kn.dudka.usermanagment.web;

import org.junit.Test;
import ua.nure.kn.dudka.usermanagment.User;

public class TestDetailsServlet extends MockServletTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(DetailsServlet.class);
    }

    @Test
    public void testDetails() {
        addRequestParameter("cancelButton", "cancel");
        User user = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNull("User is in session", user);
    }
}
