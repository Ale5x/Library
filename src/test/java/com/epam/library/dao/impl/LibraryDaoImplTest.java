package com.epam.library.dao.impl;


import com.epam.library.dao.LibraryDao;
import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class LibraryDaoImplTest {

    private LibraryDao libraryDAO;
    @BeforeEach
    public void setUp() throws Exception {
        libraryDAO = new LibraryDaoImpl();
    }

    @Test
    public void create() {
        Library library = new Library();
        library.setCity("Могилёв");
        library.setStreet("STREET");
        library.setStatus(LibraryStatus.OPENED);
        boolean condition = libraryDAO.create(library);
        assertTrue(condition);
    }

    @Test
    public void update() {
        Library library = new Library();
        library.setCity("Update");
        library.setLibraryId(1);
        library.setStreet("STREET Update");
        library.setStatus(LibraryStatus.CLOSED);
        int expected = 1;
        int actual = libraryDAO.update(library);
        assertEquals(expected, actual);
    }

    @Test
    public void getLibraries() {
        List<Library> libraries;
        libraries = libraryDAO.getLibraries();
        assertFalse(libraries.isEmpty());
    }

    @Test
    public void getLibrariesByStatus() {
        List<Library> libraries;
        libraries = libraryDAO.getLibrariesByStatus(LibraryStatus.OPENED);
        assertFalse(libraries.isEmpty());
    }

    @Test
    public void getLibrariesById() {
        long libraryId = 1;
        Optional<Library> libraryOptional = libraryDAO.getLibraryById(libraryId);
        assertTrue(libraryOptional.isPresent());
    }

    @Test
    public void getLibraryByCity() {
        String city = "Брест";
        Library libraryExpected = new Library();
        libraryExpected.setCity(city);
        Optional<Library> libraryOptional = libraryDAO.getLibraryByCity(city);
        if (libraryOptional.isPresent()) {
            assertEquals(libraryExpected.getCity(), libraryOptional.get().getCity());
        }
    }
}