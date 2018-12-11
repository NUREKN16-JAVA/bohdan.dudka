package ua.nure.kn.dudka.usermanagment.db;

import com.mockobjects.dynamic.Mock;

public class MockDaoFactory extends DAOFactory {
    private Mock mockDaoFactory;

    MockDaoFactory() {
        mockDaoFactory = new Mock(UserDAO.class);
    }

    public Mock getMockDaoFactory() {
        return mockDaoFactory;
    }

    @Override
    public UserDAO getUserDAO() throws ReflectiveOperationException {
        return (UserDAO) mockDaoFactory.proxy();
    }
}
