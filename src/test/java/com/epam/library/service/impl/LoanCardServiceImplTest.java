package com.epam.library.service.impl;

import com.epam.library.service.LoanCardService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanCardServiceImplTest {

    private LoanCardService loanCardService;

    @BeforeEach
    void setUp() {
        loanCardService = ServiceFactory.getInstance().getLoanCardService();
    }

    @Test
    void create() throws ServiceException {
        String orderId = "1";
        String typeUse = "read_room";
        boolean condition = loanCardService.create(orderId, typeUse);
        assertTrue(condition);
    }

    @Test
    void update() throws ServiceException {
        String cardId = "1";
        String typeUse = "read_room";
        String status = "open";
        boolean condition = loanCardService.update(cardId, typeUse, status);
        assertTrue(condition);
    }

    @Test
    void closeLoanCard() throws ServiceException {
        String cardId = "1";
        boolean condition = loanCardService.closeLoanCard(cardId);
        assertTrue(condition);
    }

    @Test
    void closeLoanCardWithViolation() throws ServiceException {
        String cardId = "1";
        boolean condition = loanCardService.closeLoanCardWithViolation(cardId);
        assertTrue(condition);
    }

    @Test
    void showCountCards() throws ServiceException {
        String status = "open";
        long count = loanCardService.showCountCards(status);
        assertTrue(count > 0);
    }
}