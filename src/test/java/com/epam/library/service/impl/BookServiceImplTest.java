package com.epam.library.service.impl;

import com.epam.library.entity.Book;
import com.epam.library.service.BookService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImplTest.class);
    private BookService bookService;
    private String text = "Beginning in the eighth century B.C., Ancient Rome grew from a small town on central " +
            "Italy’s Tiber River into an empire that at its peak encompassed most of continental Europe, " +
            "Britain, much of western Asia, northern Africa and the Mediterranean islands. Among the many " +
            "legacies of Roman dominance are the widespread use of the Romance languages (Italian, French, " +
            "Spanish, Portuguese and Romanian) derived from Latin, the modern Western alphabet and calendar " +
            "and the emergence of Christianity as a major world religion. ";

    @BeforeEach
    void setUp() {
        bookService = ServiceFactory.getInstance().getBookService();
    }

    @Test
    void create() throws ServiceException{
        String title = "Service Garry Potter";
        String isbn = "978-5-389-07435-4";
        String publisher = "Росмен";
        String year = "2000";
        String quantity = "7";
        String shelf = "test";
        String description = "Service";
        String library = "Минск";
        boolean condition = bookService.create(title, isbn, publisher, year, quantity, shelf,
                description, library);
        assertTrue(condition);
    }

    @Test
    void getCountBooks() throws ServiceException{
        logger.info("Test getting the number of books.");
        long count = bookService.getCountBooks();
        logger.info("Result operation: count - {}", count);
        assertTrue(count > 0);
        logger.info("Finish test.");
    }

    @Test
    void updateRightTest() throws ServiceException{
        logger.info("Book update test.");
        String bookId = "1";
        Book book = new Book();
        book.setTitle("test title");
        book.setShelf("1G");
        book.setPublisher("Publisher");
        book.setIsbn("Isbn");
        book.setDescription("Description");
        book.setYear("xxxx");
        String quantity = "1";
        String cityLibrary = "Minsk";
        boolean condition = bookService.update(bookId, book, quantity, cityLibrary);
        logger.info("Parameter: bookId - {}, quantity - {}, cityLibrary - {}, book - {}", bookId,
                quantity, cityLibrary, book);
        logger.info("Result operation: Operation is {}", condition);
        assertTrue(condition);
        logger.info("Finish test.");
    }

    @Test
    void addBorrow() throws ServiceException{
        logger.info("Test adding issuance of a book.");
        String bookId = "1";
        boolean condition = bookService.addBorrow(bookId);
        logger.info("Parameter: bookId - {}", bookId);
        logger.info("Result operation: condition - {}", condition);
        assertTrue(condition);
        logger.info("Finish test.");
    }

    @Test
    void deleteBorrow() throws ServiceException {
        logger.info("Test deleting issuance of a book.");
        String bookId = "1";
        boolean condition = bookService.deleteBorrow(bookId);
        logger.info("Parameter: bookId - {}", bookId);
        logger.info("Result operation: condition - {}", condition);
        assertTrue(condition);
        logger.info("Finish test.");
    }

    @Test
    void showBookById() throws ServiceException {
        logger.info("Test getting a book by ID.");
        String bookId = "1";
        Optional<Book> optionalBook = bookService.showBookById(bookId);
        logger.info("Parameter: bookId - {}", bookId);
        logger.info("Result operation: The book exists - {}", optionalBook.isPresent());
        assertTrue(optionalBook.isPresent());
        logger.info("Finish test.");

    }
}