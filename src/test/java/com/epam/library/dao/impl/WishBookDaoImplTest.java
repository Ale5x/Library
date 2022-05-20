package com.epam.library.dao.impl;

import com.epam.library.dao.WishBookDao;
import com.epam.library.entity.WishBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class WishBookDaoImplTest {

    private WishBookDao wishBookDao;

    @BeforeEach
    public void setUp() throws Exception {
        wishBookDao = new WishBookDaoImpl();
    }

    @Test
    public void create() {
        WishBook wishBook = new WishBook();
        wishBook.setBookId(1);
        wishBook.setUserId(1);
        boolean condition = wishBookDao.create(wishBook);
        assertTrue(condition);
    }

    @Test
    public void delete() {
        long wishBookId = 1;
        int expected = 1;
        int actual =  wishBookDao.delete(wishBookId);
        assertEquals(expected, actual);
    }

    @Test
    public void getCountWishBooks() {
        long expected = 4;
        long actual = wishBookDao.getCountWishBooks();
        assertEquals(expected, actual);
    }
}