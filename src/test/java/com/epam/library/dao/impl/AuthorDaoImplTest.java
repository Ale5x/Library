package com.epam.library.dao.impl;

import com.epam.library.dao.AuthorDao;
import com.epam.library.entity.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDaoImplTest {

    private AuthorDao authorDao;

    @BeforeEach
    public void setUp() throws Exception {
        authorDao = new AuthorDaoImpl();
    }

    @Test
    public void create() {
        Author author = new Author("Din");
        boolean condition = authorDao.create(author);
        assertTrue(condition);
    }

    @Test
    public void getAuthorByName() {
        String name = "din update";
        Optional<Author> optionalAuthor = authorDao.getAuthorByName(name);
        System.out.println(optionalAuthor);
        assertFalse(optionalAuthor.isEmpty());
    }

    @Test
    public void getAuthorsByIdBook() {
    }

    @Test
    public void getAuthors() {
        List<Author> authors = authorDao.getAuthors();
        for (Author author : authors) {
            System.out.println(author);
        }
        assertFalse(authors.isEmpty());
    }

    @Test
    public void getAllAuthorByPartName() {
        String name = "din";
        List<Author> authors = authorDao.getAllAuthorByPartName(name);
        for (Author author : authors) {
            System.out.println(author);
        }
        assertTrue(!authors.isEmpty());
    }

    @Test
    public void getCount() {
        int expected = 4;
        int actual = authorDao.getCountAuthors();
        System.out.println(actual);
        assertEquals(expected, actual);
    }
}