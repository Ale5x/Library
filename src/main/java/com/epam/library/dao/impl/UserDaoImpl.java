package com.epam.library.dao.impl;

import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.DaoException;
import com.epam.library.dao.UserDao;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.UserMapper;
import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
import com.epam.library.entity.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class {@link UserDaoImpl} is an implementation of the 'UserDao' interface.
 *
 * @author Alexander Pishchala
 */

public class UserDaoImpl extends DaoHelper implements UserDao {

    private final static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private final static String ADD_USER_QUERY = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s) " +
                    "VALUES(?, ?, ?, ?, ?, (SELECT %s FROM %s WHERE %s=?), (SELECT %s FROM %s " +
                    "WHERE %s=?));", TableName.USER,
            ColumnName.USER_PASSWORD, ColumnName.USER_FIRST_NAME, ColumnName.USER_LAST_NAME,
            ColumnName.USER_COUNT_VIOLATIONS, ColumnName.USER_EMAIL,
            ColumnName.USER_ID_ROLE, ColumnName.USER_ID_STATUS, ColumnName.ROLE_ID_ROLE,
            TableName.ROLE, ColumnName.ROLE_ROLE,  ColumnName.USER_STATUS_ID_STATUS, TableName.USER_STATUS, ColumnName.USER_STATUS_STATUS);

    private final static String UPDATE_USER_QUERY = String.format("UPDATE %s set %s=(SELECT %s FROM %s WHERE %s=?), " +
                    " %s=(SELECT %s FROM %s WHERE %s=?), %s=?, %s=?, %s=?, " +
                    "%s=? WHERE %s=?;", TableName.USER, ColumnName.USER_ID_ROLE, ColumnName.ROLE_ID_ROLE,
            TableName.ROLE, ColumnName.ROLE_ROLE, ColumnName.USER_STATUS_ID_STATUS, ColumnName.USER_STATUS_ID_STATUS, TableName.USER_STATUS,
            ColumnName.USER_STATUS_STATUS, ColumnName.USER_FIRST_NAME, ColumnName.USER_LAST_NAME,
            ColumnName.USER_EMAIL, ColumnName.USER_COUNT_VIOLATIONS,
            ColumnName.USER_ID_USERS);

    private final static String UPDATE_PASSWORD_QUERY = String.format("UPDATE %s SET %s=? WHERE %s=?;",
            TableName.USER, ColumnName.USER_PASSWORD, ColumnName.USER_ID_USERS);

    private final static String GET_USER_BY_ID_QUERY = String.format("SELECT * FROM %s NATURAL JOIN %s " +
            "NATURAL JOIN %s WHERE %s=?;", TableName.USER, TableName.ROLE, TableName.USER_STATUS,
            ColumnName.USER_ID_USERS);

    private final static String GET_USER_BY_EMAIL_QUERY = String.format("SELECT * FROM %s NATURAL JOIN %s NATURAL" +
            " JOIN %s WHERE %s=?", TableName.USER, TableName.ROLE, TableName.USER_STATUS,
            ColumnName.USER_EMAIL);

    private final static String GET_USER_BY_EMAIL_PASSWORD_STATUS_QUERY = String.format("SELECT * FROM %s" +
            " NATURAL JOIN %s NATURAL JOIN %s WHERE %s=? and %s=? and (%s.%s=? or %s.%s=?);",
            TableName.USER, TableName.ROLE, TableName.USER_STATUS, ColumnName.USER_EMAIL,
            ColumnName.USER_PASSWORD, TableName.USER_STATUS, ColumnName.USER_STATUS_STATUS, TableName.USER_STATUS,
            ColumnName.USER_STATUS_STATUS);

    private final static String GET_ALL_USERS_QUERY = String.format("SELECT * FROM %s NATURAL JOIN %s NATURAL JOIN %s ",
            TableName.USER, TableName.USER_STATUS, TableName.ROLE);

    private final static String GET_ALL_USERS_BY_STATUS_QUERY = String.format("SELECT * FROM %s NATURAL JOIN %s " +
            "NATURAL JOIN %s  WHERE %s.%s=?;", TableName.USER, TableName.USER_STATUS, TableName.ROLE,
             TableName.USER_STATUS, ColumnName.USER_STATUS_STATUS);

    private final static String GET_ALL_USERS_BY_ROLE_QUERY = String.format("SELECT * FROM %s NATURAL JOIN %s " +
                    "NATURAL JOIN %s  WHERE %s.%s=?;", TableName.USER, TableName.USER_STATUS, TableName.ROLE,
            TableName.ROLE, ColumnName.ROLE_ROLE);

    private final static String GET_COUNT_BY_STATUS_QUERY = String.format("select count(%s) from %s where %s.%s=" +
                    "(SELECT %s from %s where %s=?)", ColumnName.USER_EMAIL, TableName.USER, TableName.USER,
            ColumnName.USER_STATUS_ID_STATUS, ColumnName.USER_STATUS_ID_STATUS, TableName.USER_STATUS,
            ColumnName.USER_STATUS_STATUS);

    private final static String GET_COUNT_USER_BY_PERIOD_QUERY = String.format("SELECT * FROM %s NATURAL JOIN %s " +
                    "NATURAL JOIN %s WHERE %s BETWEEN ? AND ?;", TableName.USER, TableName.ROLE, TableName.USER_STATUS,
            ColumnName.USER_REGISTRATION);


    @Override
    public boolean create(User user) throws DaoException {
        logger.info("Create user");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, ADD_USER_QUERY, user.getPassword(), user.getFirstName(),
                    user.getLastName(), user.getCountViolations(), user.getEmail(),
                    user.getRole().name(),  user.getStatus().name());
          return prStatement.execute();
        } catch (SQLException sqlE) {
            logger.error("Failed to create user. User - {}.", user.toString());
            throw new DaoException(sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public int update(User user) throws DaoException {
        logger.info("User update.");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, UPDATE_USER_QUERY, user.getRole().name(),
                    user.getStatus().name(), user.getFirstName(), user.getLastName(), user.getEmail(),
                    user.getCountViolations(), user.getUserId());
            return prStatement.executeUpdate();
        } catch (SQLException sqlE) {
            logger.error("Failed to update user. User - {}", user.toString());
            throw new DaoException(sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public int updatePassword(User user) throws DaoException {
        logger.info("User password update");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, UPDATE_PASSWORD_QUERY, user.getPassword(),
                    user.getUserId());
            return prStatement.executeUpdate();
        } catch (SQLException sqlE) {
            logger.error("Error updating user password. User - {}", user.toString());
            throw new DaoException(sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<User> getUserById(long id) throws DaoException {
        logger.info("Receiving a user by id.");
        UserMapper mapper = new UserMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<User> entity = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, GET_USER_BY_ID_QUERY, id);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                User item = mapper.map(resultSet);
                entity.add(item);
            }

            if(entity.size() == 1) {
                logger.info("User by id received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                throw new UnsupportedOperationException("Find more 1 user.");
            }
        } catch (SQLException sqlE) {
            logger.error("Find more 1 user. Find - {}", entity.toString());
            throw new DaoException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<User> getUserByEmail(String email) throws DaoException {
        logger.info("There is an email in the system.");
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        UserMapper mapper = new UserMapper();
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_USER_BY_EMAIL_QUERY, email);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                users.add(mapper.map(resultSet));
            }
            return users;
        } catch (SQLException sqlE) {
            logger.error("An error occurred while receiving email from the database.");
            throw new DaoException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<User> getUserByEmailAndPassword(String email, String password) throws DaoException {
        logger.info("Verification user. Getting user by login, password and status.");
        UserMapper mapper = new UserMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<User> entity = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_USER_BY_EMAIL_PASSWORD_STATUS_QUERY, email,
                    password, UserStatus.ACTIVE.name(), UserStatus.BLOCKED.name());
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if(entity.size() == 1) {
                logger.info("User received for verification.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                logger.info("Find more 1 user.");
                throw new DaoException("Find more 1 user.");
            }
        } catch (SQLException sqlE) {
            logger.error("User not received for verification.");
            logger.error("Find more 1 user. Find - {}", entity.toString());
            throw new DaoException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    /**
     * The method deletes specified record in database table.
     *
     * @param user User status will be changed to deleted and updated in the table.
     * @return count row if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    @Override
    public int delete(User user) throws DaoException {
        logger.info("Start the removal process.");
        user.setStatus(UserStatus.DELETE);
        return update(user);
    }

    @Override
    public List<User> getUsers() throws DaoException {
        logger.info("Getting a list of users.");
        List<User> users = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = connection.prepareStatement(GET_ALL_USERS_QUERY);
            resultSet = prStatement.executeQuery();

            UserMapper mapper = new UserMapper();
            while (resultSet.next()) {
                users.add(mapper.map(resultSet));
            }
        }catch (SQLException sqlE) {
            logger.error("Users not received.");
            throw new DaoException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Users received.");
        return users;
    }

    @Override
    public List<User> getUsersByStatus(UserStatus status) throws DaoException {
        logger.info("Getting a list of users by status.");
        List<User> users = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, GET_ALL_USERS_BY_STATUS_QUERY, status.name());
            resultSet = prStatement.executeQuery();

            UserMapper mapper = new UserMapper();
            while (resultSet.next()) {
                users.add(mapper.map(resultSet));
            }
        }catch (SQLException sqlE) {
            logger.error("Users by status not received.");
            throw new DaoException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Users by status received.");
        return users;
    }

    @Override
    public List<User> getUsersByRole(UserRole role) throws DaoException {
        logger.info("Getting a list of users by role.");
        List<User> users = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, GET_ALL_USERS_BY_ROLE_QUERY, role.name());
            resultSet = prStatement.executeQuery();

            UserMapper mapper = new UserMapper();
            while (resultSet.next()) {
                users.add(mapper.map(resultSet));
            }
        }catch (SQLException sqlE) {
            logger.error("Users by role not received.");
            throw new DaoException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Users by role received.");
        return users;
    }

    @Override
    public long getCountByStatus(UserStatus status) throws DaoException {
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        int countUsers = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, GET_COUNT_BY_STATUS_QUERY, status.name());
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                countUsers = resultSet.getInt(1);
            }
        }catch (SQLException sqlE) {
            logger.error("Number of users by status not received.");
            throw new DaoException("Number of users by status not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        return countUsers;
    }
}
