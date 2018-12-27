package ua.nure.kn.dudka.usermanagment.db;

import ua.nure.kn.dudka.usermanagment.User;
import ua.nure.kn.dudka.usermanagment.db.exception.DataBaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Represents main DAO logic to work with DB
 */
class HsqlDBUserDAO implements UserDAO {
    private static final String FIND_BY_FIRS_AND_LAST = "SELECT id, firstname, lastname, dateofbirth FROM USERS WHERE firstname = ? AND lastname = ?";
    private final String UPDATE_USER = "UPDATE USERS SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
    private final String INSERT_USER = "INSERT INTO USERS (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
    private final String FIND_ALL_USERS = "SELECT id, firstname, lastname, dateofbirth FROM users";
    private final String FIND_BY_ID = "SELECT * FROM USERS WHERE id = ?";
    private final String DELETE_USER = "DELETE FROM USERS WHERE id = ?";
    private final String CALL_IDENTITY = "call IDENTITY()";
    private ConnectionFactory connectionFactory;

    HsqlDBUserDAO() {
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    HsqlDBUserDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * Creates a new row in DB
     * @param user
     * @return created user
     * @throws DataBaseException
     */
    @Override
    public User create(User user) throws DataBaseException {
        try {
            Connection connection = connectionFactory.createConnection();
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

    /**
     * Updates user from DB.
     * Looking for user to update by ID
     * @param user
     * @throws DataBaseException
     */
    @Override
    public void update(User user) throws DataBaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, Date.valueOf(user.getDateOfBirth()));
            preparedStatement.setLong(4, user.getId());

            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) throw new DataBaseException("Number of inserted rows: " + insertedRows);

            connection.close();
            preparedStatement.close();
        } catch (DataBaseException | SQLException e) {
            throw new DataBaseException(e.toString());
        }
    }

    /**
     * Removes user from DB.
     * Looking for user to remove by ID
     * @param user
     * @throws DataBaseException
     */
    @Override
    public void delete(User user) throws DataBaseException {
        try {
            Connection connection = connectionFactory.createConnection();

            PreparedStatement statement = connection.prepareStatement(DELETE_USER);
            statement.setLong(1, user.getId());

            int removedRows = statement.executeUpdate();

            if (removedRows != 1) throw new DataBaseException("Number of removed rows: " + removedRows);

            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Find user by ID
     * @param id Long
     * @return User user
     * @throws DataBaseException
     */
    @Override
    public User find(Long id) throws DataBaseException {
        User user = null;
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet usersResultSet = statement.executeQuery();

            user = null;
            while (usersResultSet.next()) {
                user = new User();
                user.setId(usersResultSet.getLong(1));
                user.setFirstName(usersResultSet.getString(2));
                user.setLastName(usersResultSet.getString(3));
                Date date = usersResultSet.getDate(4);
                user.setDateOfBirth(date.toLocalDate());
            }

            connection.close();
            statement.close();
            usersResultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public Collection find(String firstName, String lastName) throws DataBaseException {
        Collection<User> result = new ArrayList<>();

        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_FIRS_AND_LAST);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet usersResultSet = statement.executeQuery();

            
            while (usersResultSet.next()) {
                User user = new User();
                user.setId(usersResultSet.getLong(1));
                user.setFirstName(usersResultSet.getString(2));
                user.setLastName(usersResultSet.getString(3));
                Date date = usersResultSet.getDate(4);
                user.setDateOfBirth(date.toLocalDate());
                result.add(user);
            }

            connection.close();
            statement.close();
            usersResultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Finds all users in DB
     * @return Collection of users
     * @throws DataBaseException
     */
    @Override
    public Collection findAll() throws DataBaseException {
        LinkedList<User> result = new LinkedList<>();

        try {
            Connection connection = connectionFactory.createConnection();
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