package ua.nure.kn.dudka.usermanagment.db;

import ua.nure.kn.dudka.usermanagment.User;

import java.util.Collection;

public class HsqlDBUserDAO implements UserDAO {
    @Override
    public User create(User user) throws DataBaseException {
        return null;
    }

    @Override
    public void update(User user) throws DataBaseException {

    }

    @Override
    public void delete(User user) throws DataBaseException {

    }

    @Override
    public User find(Long id) throws DataBaseException {
        return null;
    }

    @Override
    public Collection findAll() throws DataBaseException {
        return null;
    }
}