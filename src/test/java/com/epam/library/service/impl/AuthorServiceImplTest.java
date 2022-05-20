package com.epam.library.service.impl;

import com.epam.library.entity.Author;
import com.epam.library.service.AuthorService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuthorServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImplTest.class);
    private AuthorService authorService;
    private String text = "Beginning in the eighth century B.C., Ancient Rome grew from a small town on central " +
            "Italy’s Tiber River into an empire that at its peak encompassed most of continental Europe, " +
            "Britain, much of western Asia, northern Africa and the Mediterranean islands. Among the many " +
            "legacies of Roman dominance are the widespread use of the Romance languages (Italian, French, " +
            "Spanish, Portuguese and Romanian) derived from Latin, the modern Western alphabet and calendar " +
            "and the emergence of Christianity as a major world religion. ";

    @BeforeEach
    void setUp() {
        authorService = ServiceFactory.getInstance().getAuthorService();
    }

    @Test
    void createRightTest() {
        try {
            logger.info("Author creation test.");
            String name = "Test";
            logger.info("Parameters: name - {}.", name);
            boolean condition = authorService.create(name);
            logger.info("Result operation - {}", condition);
            assertTrue(condition);
            logger.info("Finish test.");
        } catch (ServiceException e) {}
    }

    @Test
    void createWrongTest() {
        logger.info("Author creation test with long parameter.");
        String name = text;
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            authorService.create(name);
        });
        String expectedMessage = "Author name value is longer than specified.";
        logger.info("Parameters: long text.");
        String actualMessage = exception.getMessage();
        logger.info("Result operation - {}", actualMessage.contains(expectedMessage));
        assertTrue(actualMessage.contains(expectedMessage));
        logger.info("Finish test.");
    }

    @Test
    void updateRightTest() {
        try {
            logger.info("Author update test.");
            String authorId = "1";
            String name = "Author";
            logger.info("Parameters: author ID - {}, name - {}", authorId, name);
            boolean condition = authorService.update(authorId, name);
            logger.info("Result operation - {}", condition);
            assertTrue(condition);
            logger.info("Finish test.");
        } catch (ServiceException e) {}
    }

    @Test
    void getCountAuthors() {
        try {
            logger.info("Getting the number of authors.");
            int count = authorService.getCountAuthors();
            logger.info("Result operation: count authors - {} ", count);
            assertTrue(count > 0);
            logger.info("Finish test.");
        } catch (ServiceException e) {}
    }

    @Test
    void getCountBooksByAuthors() {
        try {
            logger.info("Getting the number of books by authors.");
            String name = "Jon";
            int count = authorService.getCountBooksByAuthor(name);
            logger.info("Result operation: author - {}, count books - {} ", name, count);
            assertTrue(count > 0);
            logger.info("Finish test.");
        } catch (ServiceException e) {}
    }

    @Test
    void showAuthorById() {
        try {
            logger.info("Get author by ID.");
            String authorId = "1";
            Optional<Author> optionalAuthor = authorService.showAuthorById(authorId);
            logger.info("Result operation: authorId - {}, Author exists- {} ", authorId, optionalAuthor.isPresent());
            assertTrue(optionalAuthor.isPresent());
            logger.info("Finish test.");
        } catch (ServiceException e) {}
    }

    @Test
    void showAuthorByName() {
        try {
            logger.info("Get author by name.");
            String name = "Jon";
            Optional<Author> optionalAuthor = authorService.showAuthorByName(name);
            logger.info("Result operation: name - {}, Author exists- {} ", name, optionalAuthor.isPresent());
            assertTrue(optionalAuthor.isPresent());
            logger.info("Finish test.");
        } catch (ServiceException e) {}
    }

    @Test
    void showAllAuthors() {
        try {
            logger.info("Getting all authors.");
            List<Author> authors = authorService.showAllAuthors();
            logger.info("Result operation: count authors - {}", authors.size());
            assertFalse(authors.isEmpty());
            logger.info("Finish test.");
        } catch (ServiceException e) {}
    }
}