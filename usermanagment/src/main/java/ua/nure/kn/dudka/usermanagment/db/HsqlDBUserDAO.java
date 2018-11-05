package ua.nure.kn.dudka.usermanagment.db;

import ua.nure.kn.dudka.usermanagment.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

public class HsqlDBUserDAO implements UserDAO {
    private ConnectionFactory connectionFactory;

    HsqlDBUserDAO () {}

    HsqlDBUserDAO (ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User create(User user) throws DataBaseException {
        Connection connection = connectionFactory.createConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users(firstname, lastname, dateofbirth) VALUE (?, ?, ?)");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, Date.valueOf(user.getDateOfBirth()));
            int insertedRows = statement.executeUpdate();

            if (insertedRows != 1)
                throw new DataBaseException("Number of inserted rows: " + insertedRows);

        } catch (SQLException | DataBaseException e) {
            throw new DataBaseException(e.toString());
        }
        return user;
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
