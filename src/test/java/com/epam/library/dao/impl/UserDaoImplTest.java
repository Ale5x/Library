package com.epam.library.dao.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.UserDao;
import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
import com.epam.library.entity.UserStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserDaoImplTest {

    private final static Logger logger = LoggerFactory.getLogger(UserDaoImplTest.class);


    private static UserDao userDao;

    @BeforeEach
    public void setUp() {
        userDao = new UserDaoImpl();
    }

    @Test
    public void create() {
        logger.info("Create new User test.");
        User user = new User();
        user.setPassword("!Password");
        user.setFirstName("!Second name");
        user.setLastName("!Last name");
        user.setCountViolations(0);
        user.setEmail("!email");
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);
        try {
            boolean condition = userDao.create(user);
            logger.info("Finish test. Result - {}, user - {}", condition, user.toString());
            assertFalse(condition);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        logger.info("Update user test");
        User user = new User();
        user.setUserId(1);
        user.setPassword("!Update");
        user.setFirstName("!Update");
        user.setLastName("!Update");
        user.setCountViolations(0);
        user.setEmail("!Update");
        user.setDateRegistration(LocalDate.now());
        user.setStatus(UserStatus.ACTIVE);
        user.setRole(UserRole.USER);
        try {
            int expected = userDao.update(user);
            int actual = 1;
            logger.info("Finish test. Result - {}, user - {}", expected, user.toString());
            assertEquals(expected, actual);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateStatus() {
        logger.info("Update status test");
        User user = new User();
        user.setUserId(1);
        user.setStatus(UserStatus.BLOCKED);
        try {
            int expected = userDao.update(user);
            int actual = 1;
            logger.info("Finish test. Result - {}, user - {}", expected, user.toString());
            assertEquals(expected, actual);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserById() {
        logger.info("Get user by id test");
        long userId = 2;
        Optional<User> optional = userDao.getUserById(userId);
        logger.info(optional.toString());
        assertFalse(optional.isEmpty());
    }

    @Test
    public void testGetUserByEmailAndPassword() {
        logger.info("Get user by email and password test");
        String email = "!Update";
        String password = "!Password";
        try {
            Optional<User> optional = userDao.getUserByEmailAndPassword(email, password);
            assertFalse(optional.isEmpty());
        }catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        logger.info("Delete user test");
        User user = new User();
        long userId = 1;
        user.setUserId(userId);
        try {
            int expected = userDao.delete(user);
            int actual = 1;
            logger.info("Finish test. Result - {},", expected );
            assertEquals(expected, actual);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUsers() {
        logger.info("Get users test");
        try {
            List<User> users = userDao.getUsers();
            assertFalse(users.isEmpty());
        }catch (DaoException e) {
            e.toString();
        }
    }

    @Test
    public void getUsersByStatus() {
        logger.info("Get users test");
        try {
            UserStatus status = UserStatus.DELETE;
            List<User> users = userDao.getUsersByStatus(status);
            System.out.println(users);
            assertFalse(users.isEmpty());
        }catch (DaoException e) {
            e.toString();
        }
    }

    @Test
    public void getUsersByRole() {
        logger.info("Get users test");
        try {
            UserRole role = UserRole.USER;
            List<User> users = userDao.getUsersByRole(role);
            assertFalse(users.isEmpty());
        }catch (DaoException e) {
            e.toString();
        }
    }

    @Test
    public void getCountByStatus() {
        logger.info("Get users test");
        try {
            UserStatus status = UserStatus.DELETE;
            long expected = 1;
            long actual = userDao.getCountByStatus(status);
            assertEquals(expected, actual);
        }catch (DaoException e) {
            e.toString();
        }
    }
}