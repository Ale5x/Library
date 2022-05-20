package com.epam.library.service.impl;

import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.service.LoanCardDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LoanCardDtoServiceImplTest {

    private LoanCardDtoService loanCardDtoService;

    @BeforeEach
    void setUp() {
        loanCardDtoService = ServiceFactory.getInstance().getLoanCardDtoService();
    }

    @Test
    void showCardsByStatus() throws ServiceException {
        String status = "open";
        List<LoanCardDto> cards = loanCardDtoService.showCardsByStatus(status);
        assertNotNull(cards);
    }

    @Test
    void showCardsByUser() throws ServiceException {
        String userId = "1";
        List<LoanCardDto> cards = loanCardDtoService.showCardsByUser(userId);
        assertNotNull(cards);
    }

    @Test
    void showCardsByBook() throws ServiceException {
        String bookId = "1";
        List<LoanCardDto> cards = loanCardDtoService.showCardsByBook(bookId);
        assertNotNull(cards);
    }

    @Test
    void showCardsByCityAndStatus() throws ServiceException {
        String city = "Минск";
        String status = "open";
        List<LoanCardDto> cards = loanCardDtoService.showCardsByCityAndStatus(city, status);
        assertNotNull(cards);
    }

    @Test
    void showCardsByCity() throws ServiceException {
        String city = "Минск";
        List<LoanCardDto> cards = loanCardDtoService.showCardsByCity(city);
        assertNotNull(cards);
    }

    @Test
    void showAll() throws ServiceException {
        List<LoanCardDto> cards = loanCardDtoService.showAll();
        assertNotNull(cards);
    }

    @Test
    void showCardsById() throws ServiceException {
        String cardId = "1";
        Optional<LoanCardDto> cardDtoOptional = loanCardDtoService.showCardsById(cardId);
    }
}