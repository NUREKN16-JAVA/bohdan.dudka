package ua.nure.kn.dudka.usermanagment.db;

import ua.nure.kn.dudka.usermanagment.User;

import java.util.Collection;

/**
 * Realize main logic for DAO
 */
public interface UserDAO {
    User create (User user) throws DataBaseException;
    void update (User user) throws DataBaseException;
    void delete (User user) throws DataBaseException;
    User find (Long id) throws DataBaseException;
    Collection findAll () throws DataBaseException;
    void setConnectionFactory(ConnectionFactory connectionFactory);
}
