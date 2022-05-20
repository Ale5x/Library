package com.epam.library.dao.impl;

import com.epam.library.dao.BookDao;
import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoFactory;
import com.epam.library.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoImplTest {

    private BookDao bookDao;

    @BeforeEach
    void setUp() {
        bookDao = DaoFactory.getInstance().getBookDao();
    }

    @Test
    void create() throws DaoException {
        Book book = new Book();
        book.setTitle("DAO Garry Potter");
        book.setIsbn("978-5-389-07435-4");
        book.setPublisher("Росмен");
        book.setYear("2000");
        book.setQuantity(7);
        book.setBorrow(7);
        book.setShelf("test");
        book.setDescription("DAO");
        book.setCityLibrary("Минск");
        boolean condition = bookDao.create(book);
        assertTrue(condition);
    }

    @Test
    void update() throws DaoException {
        Book book = new Book();
        book.setBookId(1);
        book.setTitle("DAO Garry Potter");
        book.setIsbn("978-5-389-07435-4");
        book.setPublisher("Росмен");
        book.setYear("2000");
        book.setQuantity(7);
        book.setBorrow(7);
        book.setShelf("test");
        book.setDescription("DAO - Update");
        book.setCityLibrary("Минск");
        int expected = 1;
        int actual = bookDao.update(book);
        assertEquals(expected, actual);
    }

    @Test
    void getBookById() throws DaoException {
        long bookId = 1;
        Optional<Book> optionalBook = bookDao.getBookById(bookId);
        assertTrue(optionalBook.isPresent());
    }

    @Test
    void getCountBooks() throws DaoException {
        long countBooks = bookDao.getCountBooks();
        assertTrue(countBooks > 0);
    }
}