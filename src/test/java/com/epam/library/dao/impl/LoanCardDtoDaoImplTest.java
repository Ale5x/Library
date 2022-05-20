package com.epam.library.dao.impl;


import com.epam.library.dao.LoanCardDtoDao;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.entity.LoanCardStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoanCardDtoDaoImplTest {

    private LoanCardDtoDao loanCardDtoDao;

    @BeforeEach
    public void setUp() throws Exception {
        loanCardDtoDao = new LoanCardDtoDaoImpl();
    }

    @Test
    public void getCardsByIdUser() {
        Long userId = 1L;
        List<LoanCardDto> cards = loanCardDtoDao.getCardsByIdUser(userId);
        for (LoanCardDto card : cards) {
            System.out.println(card);
        }
        assertTrue(cards.size() == 1);
    }

    @Test
    public void getCardsByStatusCard() {
        List<LoanCardDto> cards = loanCardDtoDao.getCardsByStatusCard(LoanCardStatus.OPEN);
        for (LoanCardDto card : cards) {
            System.out.println(card);
        }
        assertFalse(cards.isEmpty());
    }

    @Test
    public void getCardsByIdBook() {
        Long bookId = 1L;
        List<LoanCardDto> cards = loanCardDtoDao.getCardsByIdBook(bookId);
        for (LoanCardDto card : cards) {
            System.out.println(card);
        }
        assertFalse(cards.isEmpty());
    }

    @Test
    public void getCardsById() {
        Long loanCardId = 1L;
        Optional<LoanCardDto> optional = loanCardDtoDao.getCardsById(loanCardId);
        System.out.println(optional.toString());
        assertTrue(optional.isPresent());
    }

    @Test
    public void getCardsByCity() {
        String city = "Brest";
        List<LoanCardDto> cards = loanCardDtoDao.getCardsByCity(city);
        for (LoanCardDto card : cards) {
            System.out.println(card);
        }
        assertFalse(cards.isEmpty());
    }

    @Test
    public void getAll() {
        List<LoanCardDto> cards = loanCardDtoDao.getAll();
        for (LoanCardDto card : cards) {
            System.out.println(card);
        }
        assertFalse(cards.isEmpty());
    }
}