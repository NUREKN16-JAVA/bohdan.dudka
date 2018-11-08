package ua.nure.kn.dudka.usermanagment.db;

import ua.nure.kn.dudka.usermanagment.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

class HsqlDBUserDAO implements UserDAO {
    private ConnectionFactory connectionFactory;
    private String INSERT_USER = "INSERT INTO USERS (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
    private String CALL_IDENTITY = "call IDENTITY()";
    private String FIND_ALL_USERS = "SELECT id, firstname, lastname, dateofbirth FROM users";

    HsqlDBUserDAO () {}

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    HsqlDBUserDAO (ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User create(User user) throws DataBaseException {
        Connection connection = connectionFactory.createConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, Date.valueOf(user.getDateOfBirth()));
            int insertedRows = statement.executeUpdate();

            if (insertedRows != 1)
                throw new DataBaseException("Number of inserted rows: " + insertedRows);

            CallableStatement callableStatement = connection.prepareCall(CALL_IDENTITY);
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
        LinkedList<User> result = new LinkedList<>();

        Connection connection = connectionFactory.createConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                Date date = resultSet.getDate(4);
                user.setDateOfBirth(date.toLocalDate());
                result.add(user);
            }

            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }

        return result;
    }
}
