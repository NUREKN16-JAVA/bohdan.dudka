package ua.nure.kn.dudka.usermanagment.db;

import ua.nure.kn.dudka.usermanagment.User;

import java.sql.*;
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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users(firstname, lastname, dateofbirth) VALUES (?, ?, ?)");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, Date.valueOf(user.getDateOfBirth()));
            int insertedRows = statement.executeUpdate();

            if (insertedRows != 1)
                throw new DataBaseException("Number of inserted rows: " + insertedRows);

            CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
            ResultSet keys = callableStatement.executeQuery();

            if (keys.next()) {
                user.setId(keys.getLong(1));
            }


            connection.close();
            statement.close();
            callableStatement.close();
            keys.close();
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
