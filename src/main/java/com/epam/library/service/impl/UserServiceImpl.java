package com.epam.library.service.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoFactory;
import com.epam.library.dao.UserDao;
import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
import com.epam.library.entity.UserStatus;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.ServiceValidator;
import com.epam.library.service.UserService;
import com.epam.library.service.utill.Chiper.Cipher;
import com.epam.library.service.utill.UtilFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Class {@link UserServiceImpl} is service class which provide operations the user
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String ADMIN_ID = "1";

    @Override
    public Optional<User> verification(String email, String password) throws ServiceException {
        try {
            UserDao userDAO = DaoFactory.getInstance().getUserDAO();
            Cipher cipher = UtilFactory.getInstance().getCipher();
            String passwordCipher = cipher.getCipherString(password.trim());
            return userDAO.getUserByEmailAndPassword(email.trim(), passwordCipher);
        }catch (DaoException e) {
            logger.error("Error in services during user verification.");
            throw new ServiceException("Error in services during user verification.", e);
        }
    }

    @Override
    public boolean remove(String userId) throws ServiceException {
        try {
            UserDao userDao = DaoFactory.getInstance().getUserDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (!userId.equals(ADMIN_ID)) {
                if (validator.isNumber(userId.trim())) {
                    Optional<User> optionalUser = userDao.getUserById(Long.parseLong(userId.trim()));
                    if (optionalUser.isPresent()) {
                        userDao.delete(optionalUser.get());
                        return true;
                    } else {
                        throw new ServiceException("User in not found.");
                    }
                } else {
                    throw new ServiceException("Trying to get a user by an ID that is not a number.");
                }
            } else {
                throw new ServiceException("Attempted to remove the main administrator.");
            }
        }catch (DaoException e) {
            logger.error("Error when deleting a user.");
            throw new ServiceException("Error when deleting a user.", e);
        }
    }

    @Override
    public boolean create(String email, String password, String secondName, String lastName) throws ServiceException {
        try {
            UserDao userDAO = DaoFactory.getInstance().getUserDAO();
            Cipher cipher = UtilFactory.getInstance().getCipher();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isEmail(email.trim()) && validator.isPassword(password.trim())) {
                if (validator.isLength(secondName) && validator.isLength(lastName)) {
                    boolean flag = true;
                    List<User> users = userDAO.getUserByEmail(email);
                    if (users != null) {
                        for (User user : users) {
                            if (user.getStatus() == UserStatus.ACTIVE || user.getStatus() == UserStatus.BLOCKED) {
                                flag = false;
                            }
                        }
                    }
                    if (flag) {
                        String passwordCipher = cipher.getCipherString(password.trim());
                        User user = new User();
                        user.setPassword(passwordCipher);
                        user.setEmail(email);
                        user.setFirstName(secondName);
                        user.setLastName(lastName);
                        user.setStatus(UserStatus.ACTIVE);
                        user.setRole(UserRole.USER);
                        user.setCountViolations(0);
                        userDAO.create(user);
                        return true;
                    }
                } else {
                    throw new ServiceException("The value is too long.");
                }
            } else {
                return false;
            }
        }catch (DaoException e) {
            logger.error("Error in services during registration.");
            throw new ServiceException("Error in services during registration.", e);
        }
        return false;
    }

    @Override
    public List<User> getUsers() throws ServiceException {
        try {
            UserDao userDAO = DaoFactory.getInstance().getUserDAO();
            return userDAO.getUsers();
        }catch (DaoException e) {
            logger.error("Services error when getting a list of all users.");
            throw new ServiceException("Services error when getting a list of all users.", e);
        }
    }

    @Override
    public long showCountByStatus(String status) throws ServiceException {
        try {
            UserDao userDAO = DaoFactory.getInstance().getUserDAO();
            if (status.equalsIgnoreCase(UserStatus.ACTIVE.name())
                    || status.equalsIgnoreCase(UserStatus.BLOCKED.name())
                    || status.equalsIgnoreCase(UserStatus.DELETE.name())) {
                return userDAO.getCountByStatus(UserStatus.valueOf(status.toUpperCase()));
            } else {
                throw new ServiceException("Unknown user status.");
            }
        }catch (DaoException e) {
            logger.error("Error in services when getting the number of users by status.");
            throw new ServiceException("Error in services when getting the number of users by status.", e);
        }
    }

    @Override
    public List<User> showUserByStatus(String status) throws ServiceException {
        try {
            UserDao userDAO = DaoFactory.getInstance().getUserDAO();
            List<User> users;
            if (status.equalsIgnoreCase(UserStatus.ACTIVE.name()) || status.equalsIgnoreCase(UserStatus.BLOCKED.name())
                || status.equalsIgnoreCase(UserStatus.DELETE.name())) {
                users = userDAO.getUsersByStatus(UserStatus.valueOf(status.toUpperCase()));
                return users;
            } else {
                throw new ServiceException("Unknown user status.");
            }
        }catch (DaoException e) {
            logger.error("Error in services when getting the number of users by status.");
            throw new ServiceException("Error in services when getting the number of users by status.", e);
        }
    }

    @Override
    public List<User> showUserByRole(String role) throws ServiceException {
        try {
            UserDao userDAO = DaoFactory.getInstance().getUserDAO();
            List<User> users;
            if (role.equalsIgnoreCase(UserRole.USER.name()) || role.equalsIgnoreCase(UserRole.ADMIN.name())
                    || role.equalsIgnoreCase(UserRole.MANAGER.name())) {
                users = userDAO.getUsersByRole(UserRole.valueOf(role.toUpperCase()));
                return users;
            } else {
                throw new ServiceException("Unknown user role.");
            }
        }catch (DaoException e) {
            logger.error("Error in services when getting the number of users by role.");
            throw new ServiceException("Error in services when getting the number of users by role.", e);
        }
    }

    @Override
    public List<User> showUserByEmail(String email) throws ServiceException {
        try {
            UserDao userDAO = DaoFactory.getInstance().getUserDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isLength(email.trim())) {
                return userDAO.getUserByEmail(email.trim());
            }else {
                throw new ServiceException("Too long value.");
            }
        }catch (DaoException e) {
            logger.error("Error in services when fetching users by email");
            throw new ServiceException("Error in services when fetching users by email", e);
        }
    }

    @Override
    public Optional<User> showUserById(String userId) throws ServiceException {
        try {
            UserDao userDAO = DaoFactory.getInstance().getUserDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(userId.trim())) {
                return userDAO.getUserById(Long.parseLong(userId.trim()));
            } else {
                throw new ServiceException("Trying to get a user by an ID that is not a number.");
            }
        }catch (DaoException e) {
            logger.error("Error in services when getting users by ID.");
            throw new ServiceException("Error in services when getting users by ID.", e);
        }
    }

    @Override
    public boolean update(String email, String secondName, String lastName, String userId) throws ServiceException {
        try {
            UserDao userDao = DaoFactory.getInstance().getUserDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (email != "") {
                if(!validator.isEmail(email.trim())) {
                    return false;
                }
            }
            if (validator.isLengthForUpdate(secondName) && validator.isLengthForUpdate(lastName)
                     && validator.isNumber(userId)) {

                Optional<User> optionalUser = userDao.getUserById(Long.parseLong(userId.trim()));

                User user = optionalUser.get();
                user.setFirstName(secondName != "" ? secondName : optionalUser.get().getFirstName());
                user.setLastName(lastName != "" ? lastName : optionalUser.get().getLastName());
                user.setEmail(email != "" ? email : optionalUser.get().getEmail());
                userDao.update(user);
                return true;
            }
            return false;
        }catch (DaoException e) {
            logger.error("An error occurred while updating the user in services.", e);
            throw new ServiceException("An error occurred while updating the user in services.");
        }
    }

    @Override
    public boolean updateStatus(String userId, String status) throws ServiceException {
        try {
            UserDao userDAO = DaoFactory.getInstance().getUserDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (!userId.equals(ADMIN_ID)) {
                if (validator.isNumber(userId)) {
                    if (status.equalsIgnoreCase(UserStatus.ACTIVE.name()) || status.equalsIgnoreCase(UserStatus.BLOCKED.name())
                            || status.equalsIgnoreCase(UserStatus.DELETE.name())) {
                        Optional<User> optionalUser = userDAO.getUserById(Long.parseLong(userId.trim()));
                        if (optionalUser.isPresent()) {
                            User user = optionalUser.get();
                            user.setStatus(UserStatus.valueOf(status.toUpperCase()));
                            int result = userDAO.update(user);
                            if (result == 1) {
                                return true;
                            }
                        } else {
                            throw new ServiceException("User by ID not found");
                        }

                    } else {
                        throw new ServiceException("Unknown user status.");
                    }
                }else {
                    throw new ServiceException("Invalid ID value.");
                }
            } else {
                throw new ServiceException("Attempted to change the status of the main administrator.");
            }
        }catch (DaoException e) {
            logger.error("Error in status update services.");
            throw new ServiceException("Error in status update services.", e);
        }
        return false;
    }

    @Override
    public boolean updatePassword(String newPassword, String email, String oldPassword) throws ServiceException {
        try {
            UserDao userDao = DaoFactory.getInstance().getUserDAO();
            Cipher cipher = UtilFactory.getInstance().getCipher();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isPassword(newPassword) && validator.isPassword(oldPassword)) {
                String oldPasswordCipher = cipher.getCipherString(oldPassword.trim());
                Optional<User> optionalUser = verification(email, oldPasswordCipher);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    user.setPassword(cipher.getCipherString(newPassword));
                    userDao.updatePassword(user);
                    return true;
                } else {
                    throw new ServiceException("User is not found.");
                }
            } else {
                return false;
            }
        }catch (DaoException e) {
            logger.error("Error in services during registration.");
            throw new ServiceException("Error in services during registration.", e);
        }
    }

    @Override
    public boolean updateRole(String userId, String role) throws ServiceException {
        try {
            UserDao userDAO = DaoFactory.getInstance().getUserDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (!userId.equals(ADMIN_ID)){
                if (validator.isNumber(userId)) {
                    if (role.equalsIgnoreCase(UserRole.USER.name()) || role.equalsIgnoreCase(UserRole.ADMIN.name())
                            || role.equalsIgnoreCase(UserRole.MANAGER.name())) {
                        Optional<User> optionalUser = userDAO.getUserById(Long.parseLong(userId.trim()));
                        if (optionalUser.isPresent()) {
                            User user = optionalUser.get();
                            user.setRole(UserRole.valueOf(role.toUpperCase()));
                            int result = userDAO.update(user);
                            if (result == 1) {
                                return true;
                            }
                        } else {
                            throw new ServiceException("User by ID not found");
                        }

                    } else {
                        throw new ServiceException("Unknown user role.");
                    }
                }else {
                    throw new ServiceException("Invalid ID value.");
                }
            } else {
                throw new ServiceException("Attempted to change the role of the main administrator.");
            }
        }catch (DaoException e) {
            logger.error("Error in status update services.");
            throw new ServiceException("Error in status update services.", e);
        }
        return false;
    }

    @Override
    public boolean addViolation(String userId) throws ServiceException {
        try {
            UserDao userDAO = DaoFactory.getInstance().getUserDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(userId)) {
                Optional<User> optionalUser = userDAO.getUserById(Long.parseLong(userId.trim()));
                    if (optionalUser.isPresent()) {
                        User user = optionalUser.get();
                        user.setCountViolations(optionalUser.get().getCountViolations() + 1);
                        int result = userDAO.update(user);
                        if (result == 1) {
                            return true;
                        } else {
                            return false;
                        }
                    }else {
                        throw new ServiceException("User by ID not found");
                    }
            }else {
                throw new ServiceException("Invalid ID value.");
            }
        }catch (DaoException e) {
            logger.error("Error while adding violation.");
            throw new ServiceException("Error while adding violation.", e);
        }
    }

    @Override
    public boolean removeViolation(String userId) throws ServiceException {
        try {
            UserDao userDAO = DaoFactory.getInstance().getUserDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(userId)) {
                Optional<User> optionalUser = userDAO.getUserById(Long.parseLong(userId.trim()));
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    user.setCountViolations(optionalUser.get().getCountViolations() - 1);
                    int result = userDAO.update(user);
                    if (result == 1) {
                        return true;
                    } else {
                        return false;
                    }
                }else {
                    throw new ServiceException("User by ID not found");
                }
            }else {
                throw new ServiceException("Invalid ID value.");
            }
        }catch (DaoException e) {
            logger.error("Error while deleting violation.");
            throw new ServiceException("Error while deleting violation.", e);
        }
    }

    @Override
    public boolean isFreeEmail(String email) throws ServiceException {
        try {
            UserDao userDao = DaoFactory.getInstance().getUserDAO();
            List<User> users = userDao.getUserByEmail(email);
            if (!users.isEmpty()) {
                for (User user : users) {
                    if (user.getStatus().equals(UserStatus.ACTIVE)
                            || user.getStatus().equals(UserStatus.BLOCKED)) {
                        return false;
                    }
                }
            } else {
                return true;
            }
        } catch (DaoException e) {
            logger.error("Error while checking if email exists in the system.", e);
        }
        return true;
    }
}
