package com.epam.library.service;

import com.epam.library.entity.User;
import java.util.List;
import java.util.Optional;

/**
 * Interface {@link UserService} is service interface which provide operations the user
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public interface UserService {

    /**
     * The method is used to get the user for verification.
     *
     * @param email    specified email of user.
     * @param password    specified password of user.
     * @return Return user.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    Optional<User> verification(String email, String password) throws ServiceException;

    /**
     * The method is used to remove the user.
     *
     * @param userId    specified id of user.
     * @return <tt>true</tt> if a user with specified parameter was removed.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean remove(String userId) throws ServiceException;

    /**
     * The method is used to create the user.
     *
     * @param email    specified email of user.
     * @param password    specified password of user.
     * @param secondName    specified secondName of user.
     * @param lastName    specified lastName of user.
     * @return <tt>true</tt> if a user with specified parameter was created.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean create(String email, String password, String secondName, String lastName) throws ServiceException;

    /**
     * The method is used to get users.
     *
     * @return list of users.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<User> getUsers() throws ServiceException;

    /**
     * The method is used to get the number of users by status.
     *
     * @return Returns the number of users in the system.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    long showCountByStatus(String status) throws ServiceException;

    /**
     * The method is used to get users by status.
     *
     * @param status    specified status of user.
     * @return list of users.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<User> showUserByStatus(String status) throws ServiceException;

    /**
     * The method is used to get users by role.
     *
     * @param role    specified role of user.
     * @return list of users.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<User> showUserByRole(String role) throws ServiceException;

    /**
     * The method is used to get users by email.
     *
     * @param email    specified email of user.
     * @return list of users.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<User> showUserByEmail(String email) throws ServiceException;

    /**
     * The method is used to get the user by id.
     *
     * @param userId    specified id of user.
     * @return Return user.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    Optional<User> showUserById(String userId) throws ServiceException;

    /**
     * The method is used to update the user.
     *
     * @param userId    specified id of user.
     * @param email    specified email of user.
     * @param secondName    specified secondName of user.
     * @param lastName    specified lastName of user.
     * @return <tt>true</tt> if a user with specified parameter was updated.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean update(String email, String secondName, String lastName, String userId) throws ServiceException;

    /**
     * The method is used to update the user.
     *
     * @param email    specified email of user.
     * @param newPassword    specified newPassword of user.
     * @param oldPassword    specified oldPassword of user.
     * @return <tt>true</tt> if a user with specified parameter was updated.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean updatePassword(String newPassword, String email, String oldPassword) throws ServiceException;

    /**
     * The method is used to update the user.
     *
     * @param userId    specified id of user.
     * @param status    specified status of user.
     * @return <tt>true</tt> if a user with specified parameter was updated.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean updateStatus(String userId, String status) throws ServiceException;

    /**
     * The method is used to update the user.
     *
     * @param userId    specified id of user.
     * @param role    specified role of user.
     * @return <tt>true</tt> if a user with specified parameter was updated.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean updateRole(String userId, String role) throws ServiceException;

    /**
     * The method is used to add violation.
     *
     * @param userId    specified id of user.
     * @return <tt>true</tt> if a user with specified parameter was added.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean addViolation(String userId) throws ServiceException;

    /**
     * The method is used to remove violation.
     *
     * @param userId    specified id of user.
     * @return <tt>true</tt> if a user with specified parameter was removed.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean removeViolation(String userId) throws ServiceException;

    /**
     * The method is used to check email.
     *
     * @param email    specified email of user.
     * @return <tt>true</tt> if a user with specified parameter was not found.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean isFreeEmail(String email) throws ServiceException;
}
