package com.epam.library.service.impl;

import com.epam.library.entity.dto.BookDto;
import com.epam.library.service.BookDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookDtoServiceImplTest {

    private BookDtoService bookDtoService;

    @BeforeEach
    void setUp() {
        bookDtoService = ServiceFactory.getInstance().getBookDtoService();
    }

    @Test
    void create() {
        try {
            String title = "Book title";
            String isbn = "-";
            String publisher = "publisher";
            String year = "year";
            String count = "7";
            String city = "city";
            String shelf = "shelf";
            String author = "author/author1";
            String genre = "genre";
            String description = "description";

            boolean condition = bookDtoService.create(title, isbn, publisher, year, count, city, shelf, author, genre, description);
            assertTrue(condition);
        } catch (ServiceException e) {}
    }

    @Test
    void update() {
        try {
            BookDto book = new BookDto();
            String bookId = "1";
            book.setTitle("Book title");
            book.setIsbn("");
            book.setPublisher("publisher");
            book.setYear("year");
            String count = "7";
            book.setCityLibrary("Minsk");
            book.setShelf("shelf");
            String author = "author/author1";
            String genre = "genre";
            book.setDescription("");

            boolean condition = bookDtoService.update(bookId, book, author, genre, count);
            assertTrue(condition);
        } catch (ServiceException e) {}
    }

    @Test
    void showCatalog() {
        try {
            List<BookDto> books = bookDtoService.showCatalog();
            assertNotNull(books);
        } catch (ServiceException e){}
    }

    @Test
    void showBookByParameterByTitle() throws ServiceException {
        String title = "Гроздья гнева";
        String isbn = "";
        String genre = "";
        String author = "";
        List<BookDto> books = bookDtoService.showBookByParameter(title, isbn, genre, author);
        assertNotNull(books);
    }

    @Test
    void showBookByParameterByISBN() throws ServiceException {
        String title = "";
        String isbn = "1-225-2115-25";
        String genre = "";
        String author = "";
        List<BookDto> books = bookDtoService.showBookByParameter(title, isbn, genre, author);
        assertNotNull(books);
    }

    @Test
    void showBookByParameterByGenre() throws ServiceException {
        String title = "";
        String isbn = "";
        String genre = "Фантастика";
        String author = "";
        List<BookDto> books = bookDtoService.showBookByParameter(title, isbn, genre, author);
        assertNotNull(books);
    }

    @Test
    void showBookByParameterByAuthor() throws ServiceException {
        String title = "";
        String isbn = "";
        String genre = "";
        String author = "Маккиавели";
        List<BookDto> books = bookDtoService.showBookByParameter(title, isbn, genre, author);
        assertNotNull(books);
    }

    @Test
    void showBookById() throws ServiceException {
        String bookId = "1";
        Optional<BookDto> book = bookDtoService.showBookById(bookId);
        assertTrue(book.isPresent());
    }

    @Test
    void showBookByCity() throws ServiceException  {
        String city = "Минск";
        List<BookDto> books = bookDtoService.showBookByCity(city);
        assertNotNull(books);
        assertEquals(books.get(0).getCityLibrary(), city);
    }

    @Test
    void showByPage() throws ServiceException {
        int limit = 20;
        int page = 5;
        List<BookDto> books = bookDtoService.showByPage(limit, page);
        assertNotNull(books);
    }
}