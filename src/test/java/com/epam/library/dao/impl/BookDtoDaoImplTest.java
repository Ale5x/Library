package com.epam.library.dao.impl;

import com.epam.library.dao.BookDtoDao;
import com.epam.library.dao.DaoException;
import com.epam.library.entity.Author;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.entity.Genre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BookDtoDaoImplTest {

    private static final Logger logger = LoggerFactory.getLogger(BookDtoDaoImplTest.class);

    private BookDtoDao bookDtoDao;

    @BeforeEach
    public void setUp(){
        bookDtoDao = new BookDtoDaoImpl();
    }

    @Test
    public void create() {
        logger.info("Start of the bookDto creation test");
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Гарри Поттер");
        bookDto.setQuantity(5);
        bookDto.setBorrow(0);
        bookDto.setPublisher("Росмен");
        bookDto.setYear("2000");
        bookDto.setShelf("S1-F");

        bookDto.setIsbn("ISBN");
        bookDto.setCityLibrary("Минск");

        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Роулинг"));

        List<Genre> genres = new ArrayList<>();

        genres.add(new Genre("Фантастика"));

        bookDto.setAuthors(authors);
        bookDto.setGenres(genres);

        boolean condition = bookDtoDao.create(bookDto);

        assertTrue(condition);
    }

    @Test
    public void update() {
        BookDto bookDto = new BookDto();
        bookDto.setBookDtoId(17l);
        bookDto.setTitle("Test 3 title (Update)");
        bookDto.setQuantity(5);
        bookDto.setBorrow(0);
        bookDto.setPublisher("Test 3 publisher (Update)");
        bookDto.setYear("2000");
        bookDto.setIsbn("ISBN 3");
        bookDto.setCityLibrary("Minsk");
        bookDto.setDescription("Test");

        List<Author> authors = new ArrayList<>();
        authors.add(new Author("author 1"));
        authors.add(new Author("author 2"));

        List<Genre> genres = new ArrayList<>();

        genres.add(new Genre("genres 3"));

        bookDto.setAuthors(authors);
        bookDto.setGenres(genres);


        boolean condition = bookDtoDao.update(bookDto);

        assertTrue(condition);
    }

    @Test
    public void getBookByIsbn() {
        String isbn = "test isbn";
        List<BookDto> books = bookDtoDao.getBookByIsbn(isbn);
        output(books);
        assertFalse(books.isEmpty());
    }

    @Test
    public void getBookById() {
        long bookId = 1;
        Optional<BookDto> books = bookDtoDao.getBookById(bookId);
        System.out.println(books);

        assertTrue(books.isPresent());
    }

    @Test
    public void getBooks() {
        List<BookDto> books = bookDtoDao.getBooks();
        output(books);
        assertNotNull(books);
    }

    //check
    @Test
    public void getBooksByTitle() {
        String title = "hobbit";
        List<BookDto> books = bookDtoDao.getBooksByTitle(title);
        output(books);
        System.out.println(books.size());
        assertTrue(!books.isEmpty());
    }

    @Test
    public void getBooksByPage() {
        try {
            int limit = 3;
            int page = 1;
            List<BookDto> books = bookDtoDao.getBooksByPage(limit, page);
            output(books);
            assertTrue(!books.isEmpty());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getBooksByAuthor() {
        String author = "Royl";
        List<BookDto> books = bookDtoDao.getBooksByAuthor(author);
        output(books);
        assertTrue(!books.isEmpty());
    }

    @Test
    public void getBooksByGenre() {
        String genre = "trip";
        List<BookDto> books = bookDtoDao.getBooksByGenre(genre);
        output(books);
        assertTrue(!books.isEmpty());
    }

    private void output(List<BookDto> list) {
        for (BookDto book : list) {
            System.out.println(book);
        }
    }
}