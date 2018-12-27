package ua.nure.kn.dudka.usermanagment.db;

import ua.nure.kn.dudka.usermanagment.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockUserDAO implements UserDAO {
    private Long id = 0L;
    private Map<Long, User> users = new HashMap<>();

    @Override
    public User create(User user) throws DataBaseException {
        Long currentId = ++id;
        user.setId(currentId);
        users.put(currentId, user);
        return user;
    }

    @Override
    public void update(User user) throws DataBaseException {
        Long currentId = user.getId();
        users.remove(currentId);
        users.put(currentId, user);
    }

    @Override
    public void delete(User user) throws DataBaseException {
        Long currentId = user.getId();
        users.remove(currentId);
    }

    @Override
    public User find(Long id) throws DataBaseException {
        return users.get(id);
    }

    @Override
    public Collection find(String firstName, String lastName) throws DataBaseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection findAll() throws DataBaseException {
        return users.values();
    }

    @Override
    public void setConnectionFactory(ConnectionFactory connectionFactory) {

    }
}
