package com.epam.library.service.impl;

import com.epam.library.service.ServiceException;
import com.epam.library.service.WishBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class WishBookServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(WishBookServiceImplTest.class);
    private WishBookService wishBookService;

    @BeforeEach
    void setUp() {
        wishBookService = new WishBookServiceImpl();
    }

    @Test
    void addRightTest() throws ServiceException {
        logger.info("Start. Adding favorite book to wish list.");
        String userId = "1";
        String bookId = "1";
        boolean condition = wishBookService.create(userId, bookId);
        logger.info("Result operation - " + condition);
        assertTrue(condition);
        logger.info("Finish test");
    }

    @Test
    void addWrongNotNumberIdTest() throws ServiceException {
        logger.info("Start. Adding favorite book to wish list wish not right number ID.");
        String userId = "1l";
        String bookId = "1";
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            wishBookService.create(userId, bookId);
        });
        String expectedMessage = "Invalid ID values.";
        logger.info("Parameters: userId - {}, bookId - {}, exceptionMessage - {}", userId, bookId, expectedMessage);
        String actualMessage = exception.getMessage();
        logger.info("Result operation - " + actualMessage.contains(expectedMessage));
        assertTrue(actualMessage.contains(expectedMessage));

        logger.info("Finish test");
    }

    @Test
    void deleteRightTest() throws ServiceException {
        logger.info("Start. Deleting favorite book from wish list.");
        String wishBookId = "1";
        boolean condition = wishBookService.delete(wishBookId);
        logger.info("Parameters: wishBookId - {}", wishBookId);
        logger.info("Result operation - " + condition);
        assertTrue(condition);
        logger.info("Finish test");
    }

    @Test
    void deleteWrongNotNumberIdTest() throws ServiceException {
        logger.info("Start. Deleting favorite book from wish list wish not right number ID.");
        String wishBookId = "1l";
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            wishBookService.delete(wishBookId);
        });
        String expectedMessage = "Invalid ID value.";
        logger.info("Parameters: wishBookId - {}, exceptionMessage - {}", wishBookId, expectedMessage);
        String actualMessage = exception.getMessage();
        logger.info("Result operation - " + actualMessage.contains(expectedMessage));
        assertTrue(actualMessage.contains(expectedMessage));

        logger.info("Finish test");
    }

    @Test
    void showCountBooks() throws ServiceException {
        logger.info("Start. Getting count favorite books.");
        long countBooks = wishBookService.showCountBooks();
        logger.info("Result operation count books - " + countBooks);
        assertTrue(countBooks > 0);
        logger.info("Finish test");
    }
}