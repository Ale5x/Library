package com.epam.library.dao.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoFactory;
import com.epam.library.dao.LoanCardDao;
import com.epam.library.entity.BookTypeUse;
import com.epam.library.entity.LoanCard;
import com.epam.library.entity.LoanCardStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LoanCardDaoImplTest {

    private LoanCardDao loanCardDao;

    @BeforeEach
    void setUp() {
        loanCardDao = DaoFactory.getInstance().getLoanCardDao();
    }

    @Test
    void create() throws DaoException {
        LoanCard card = new LoanCard();
        card.setUserId(1);
        card.setTakingBook(LocalDate.now());
        card.setCityLibrary("Минск");
        card.setBookId(1);
        card.setStatus(LoanCardStatus.OPEN);
        card.setDeadline(LocalDate.now());
        card.setTypeUse(BookTypeUse.READ_ROOM);

        boolean condition = loanCardDao.create(card);
        assertTrue(condition);
    }

    @Test
    void getCardById() {
        long cardId = 1;
        Optional<LoanCard> loanCard = loanCardDao.getCardById(cardId);
        System.out.println(loanCard.toString());
        assertTrue(loanCard.isPresent());
    }

    @Test
    void update() {
        LoanCard card = new LoanCard();
        card.setLoanCardId(1);
        card.setUserId(1);
        card.setTakingBook(LocalDate.now());
        card.setCityLibrary("Минск");
        card.setBookId(1);
        card.setStatus(LoanCardStatus.OPEN);
        card.setDeadline(LocalDate.now());
        card.setTypeUse(BookTypeUse.TAKE_HOME);

        boolean condition = loanCardDao.update(card);
        assertTrue(condition);
    }

    @Test
    void getCountCardsByStatus() {

        long count = loanCardDao.getCountCardsByStatus(LoanCardStatus.OPEN);
        assertTrue(count > 0);
    }
}