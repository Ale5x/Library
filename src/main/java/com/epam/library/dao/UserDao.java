package com.epam.library.dao;

import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
import com.epam.library.entity.UserStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface {@link UserDao} provides operation with data of database table 'users'.
 *
 * @author Alexander Pishchala
 */

public interface UserDao {

    /**
     * The method creates new record in database table.
     *
     * @param user entity that specifies creation of new records in database table.
     * @return <tt>true</tt> if new row in database table was created successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean create(User user) throws DaoException;

    /**
     * The method updates specified record in database table.
     *
     * @param user that will be updated in database table.
     * @return count row if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    int update(User user) throws DaoException;

    /**
     * The method password updates specified record in database table.
     *
     * @param user that will be updated in database table.
     * @return count row if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    int updatePassword(User user) throws DaoException;

    /**
     * The method returns specified User by id.
     *
     * @param id User id.
     * @return specified Optional User by id.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<User> getUserById(long id) throws DaoException;

    /**
     * The method will return list User.
     *
     * @param email User email.
     * @return list of users.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<User> getUserByEmail(String email) throws DaoException;

    /**
     * The method returns specified User by email and password.
     *
     * @param password User password.
     * @param email User email.
     * @return specified Optional User by email and password.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<User> getUserByEmailAndPassword(String email, String password) throws DaoException;

    /**
     * The method deletes specified record in database table.
     *
     * @param user that will be updated in database table.
     * @return count row if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    int delete(User user) throws DaoException;

    /**
     * The method will return list Users.
     *
     * @return list of users.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<User> getUsers() throws DaoException;

    /**
     * The method will return list Users by status.
     *
     * @param status User status.
     * @return list of users.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<User> getUsersByStatus(UserStatus status) throws DaoException;

    /**
     * The method will return list Users by role.
     *
     * @param role User role.
     * @return list of users.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<User> getUsersByRole(UserRole role) throws DaoException;

    /**
     * The method will return the number of users.
     *
     * @return Users count.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    long getCountByStatus(UserStatus status) throws DaoException;

}
