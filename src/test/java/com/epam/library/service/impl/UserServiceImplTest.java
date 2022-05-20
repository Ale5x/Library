package com.epam.library.service.impl;

import com.epam.library.entity.User;
import com.epam.library.entity.UserStatus;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Test
    void verification() throws ServiceException {
        String email = "email";
        String password = "password";
        Optional<User> optionalUser = userService.verification(email, password);
        assertTrue(optionalUser.isPresent());
    }

    @Test
    void createRightTest() throws ServiceException {
        String email = "email@gamil.com";
        String password = "Password1";
        String secondName = "secondName";
        String lastName = "lastName";

        boolean condition = userService.create(email, password, secondName, lastName);
        assertTrue(condition);
    }

    @Test
    void createWrongEmailTest() throws ServiceException {
        String email = "email@gamil";
        String password = "Password1";
        String secondName = "secondName";
        String lastName = "lastName";
        boolean condition = userService.create(email, password, secondName, lastName);
        assertFalse(condition);
    }

    @Test
    void createWrongPasswordTest() throws ServiceException  {
        String email = "email@gamil.com";
        String password = "password";
        String secondName = "secondName";
        String lastName = "lastName";
        boolean condition = userService.create(email, password, secondName, lastName);
        assertFalse(condition);
    }

    @Test
    void getUsers() throws ServiceException {
        List<User> users = userService.getUsers();
        assertFalse(users.isEmpty());
    }

    @Test
    void getCountUsers() throws ServiceException {
        long count = userService.showCountByStatus(UserStatus.BLOCKED.name());
        System.out.println(count);
        assertTrue(count > 0);
    }

    @Test
    void showUserByStatusRightTest() throws ServiceException {
        String status = "active";
        List<User> users = userService.showUserByStatus(status);
        assertFalse(users.isEmpty());
    }

    @Test
    void showUserByStatusWrongTest() throws ServiceException  {
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            String status = "active1";
            userService.showUserByStatus(status);
        });

        String expectedMessage = "Unknown user status.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void showUserByRole() throws ServiceException {
        String role = "admin";
        List<User> users = userService.showUserByRole(role);
        assertFalse(users.isEmpty());
    }

    @Test
    void showUserByEmailRightTest() throws ServiceException {
        String email = "admin@gmail.com";
        List<User> users = userService.showUserByEmail(email);
        assertFalse(users.isEmpty());
    }

    @Test
    void showUserByEmailWrongTest() {
            ServiceException exception = assertThrows(ServiceException.class, () -> {
                String email = "Beginning in the eighth century B.C., Ancient Rome grew from a small town on central " +
                        "Italy’s Tiber River into an empire that at its peak encompassed most of continental Europe, " +
                        "Britain, much of western Asia, northern Africa and the Mediterranean islands. Among the many " +
                        "legacies of Roman dominance are the widespread use of the Romance languages (Italian, French, " +
                        "Spanish, Portuguese and Romanian) derived from Latin, the modern Western alphabet and calendar " +
                        "and the emergence of Christianity as a major world religion. ";
                userService.showUserByEmail(email);
            });

            String expectedMessage = "Too long value.";
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void showUserById() throws ServiceException {
        String userId = "1";
        Optional<User> optionalUser = userService.showUserById(userId);
        assertTrue(optionalUser.isPresent());
    }

    @Test
    void updateRightTest() throws ServiceException {
        String userId = "1";
        String email = "update@gmail.com";
        String secondName = "";
        String lastName = "update";

        boolean condition =  userService.update(email, secondName, lastName, userId);
        assertTrue(condition);
    }

    @Test
    void updateWrongTest() throws ServiceException {
        String userId = "1";
        String email = "update@gmail";
        String secondName = "";
        String lastName = "update";

        boolean condition =  userService.update(email, secondName, lastName, userId);
        assertFalse(condition);
    }


    @Test
    void updateStatus() throws ServiceException {
        String status = "delete";
        String userId = "1";
        boolean condition = userService.updateStatus(userId, status);
        assertTrue(condition);
    }

    @Test
    void updateRole() throws ServiceException {
        String role = "manager";
        String userId = "1";
        boolean condition = userService.updateRole(userId, role);
        assertTrue(condition);
    }

    @Test
    void updatePasswordRightTest()  throws ServiceException {
        String password = "test@gmail.com";
        String email = "1";
        String oldPassword = "1";
        boolean condition = userService.updatePassword(password, email, oldPassword);
        assertTrue(condition);

    }

    @Test
    void isFreeEmail() throws ServiceException {
        String email = "userU@gmail.com";
        boolean condition = userService.isFreeEmail(email);
        assertTrue(condition);
    }
}