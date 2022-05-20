package com.epam.library.service.impl;

import com.epam.library.entity.Genre;
import com.epam.library.service.GenreService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenreServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(GenreServiceImplTest.class);
    private GenreService genreService;

    private String text = "Beginning in the eighth century B.C., Ancient Rome grew from a small town on central " +
            "Italy’s Tiber River into an empire that at its peak encompassed most of continental Europe, " +
            "Britain, much of western Asia, northern Africa and the Mediterranean islands. Among the many " +
            "legacies of Roman dominance are the widespread use of the Romance languages (Italian, French, " +
            "Spanish, Portuguese and Romanian) derived from Latin, the modern Western alphabet and calendar " +
            "and the emergence of Christianity as a major world religion. ";

    @BeforeEach
    void setUp() {
        genreService = ServiceFactory.getInstance().getGenreService();
    }

    @Test
    void getGenres() {
        try {
            logger.info("Receiving test of all genres.");
            List<Genre> genres = genreService.getGenres();
            logger.info("Result operation - count genres - {}", genres.size());
            assertFalse(genres.isEmpty());
            logger.info("Finish test.");
        }catch (ServiceException e) {}
    }

    @Test
    void createRightTest() {
        try {
            logger.info("Genre creation test.");
            String category = "Category";
            boolean condition = genreService.create(category);
            logger.info("Parameters: category - {}.", category);
            logger.info("Result operation - {}", condition);
            assertTrue(condition);
            logger.info("Finish test.");
        }catch (ServiceException e) {}
    }

    @Test
    void createWrongTest() {
        try {
            logger.info("Genre creation test.");
            String category = text;
            boolean condition = genreService.create(category);
            logger.info("Result operation - {}", condition);
            assertFalse(condition);
            logger.info("Finish test.");
        }catch (ServiceException e) {}
    }

    @Test
    void update() {
    }

    @Test
    void getCountGenres() {
        try {
            logger.info("Getting the number of genres test.");
            long count = genreService.getCountGenres();
            logger.info("Result operation: count - {}", count);
            assertTrue(count > 0);
            logger.info("Finish test.");
        }catch (ServiceException e) {}
    }

    @Test
    void getCountBooksByGenres() {
        try {
            logger.info("Get number of books by genre. Test.");
            String category = "Category";
            long count = genreService.getCountBooksByGenres(category);
            logger.info("Parameters: category - {}.", category);
            logger.info("Result operation: count - {}", count);
            assertTrue(count > 0);
            logger.info("Finish test.");
        }catch (ServiceException e) {}
    }

    @Test
    void showGenreByCategory() {
    }

    @Test
    void showGenreById() {
    }
}