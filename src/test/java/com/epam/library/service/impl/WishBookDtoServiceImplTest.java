package com.epam.library.service.impl;

import com.epam.library.entity.dto.WishBookDto;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.WishBookDtoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WishBookDtoServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(WishBookDtoServiceImplTest.class);
    private WishBookDtoService wishBookDtoService;

    @BeforeEach
    void setUp() {
        wishBookDtoService = ServiceFactory.getInstance().getWishBookDtoService();
    }

    @Test
    void showBooksUserRightTest() {
        try{
            logger.info("Get user's favorite books.");
            String userId = "1";
            List<WishBookDto> books = wishBookDtoService.showBooksUser(userId);
            logger.info("Parameter: userId - {}", userId);
            logger.info("Result operation. Count books - " + books.size());
            assertFalse(books.isEmpty());
            logger.info("Finish test.");
        } catch (ServiceException e) {

        }
    }

    @Test
    void showBooksUserWrongNotRightNumberIdTest() {
        logger.info("Get user's favorite books by not right type value ID).");
        String userId = "1w";
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            wishBookDtoService.showBooksUser(userId);
        });
        String expectedMessage = "Invalid type value ID";
        logger.info("Parameters: userId - {}, exceptionMessage - {}", userId, expectedMessage);
        String actualMessage = exception.getMessage();
        logger.info("Result operation - " + actualMessage.contains(expectedMessage));
        assertTrue(actualMessage.contains(expectedMessage));

        logger.info("Finish test");
    }
}