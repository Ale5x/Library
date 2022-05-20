package com.epam.library.service.impl;

import com.epam.library.entity.Library;
import com.epam.library.service.LibraryService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceImplTest {

    private LibraryService libraryService;

    @BeforeEach
    void setUp() {
        libraryService = ServiceFactory.getInstance().getLibraryService();
    }

    @Test
    void create() throws ServiceException  {
        String city = "Brest";
        String street = "Main street";

        boolean condition = libraryService.create(city, street);
        assertTrue(condition);
    }

    @Test
    void showAll() throws ServiceException  {
        List<Library> libraries = libraryService.showAll();
        assertNotNull(libraries);
    }

    @Test
    void updateStatus() throws ServiceException  {
        String status = "opened";
        String libraryId = "1 ";
        boolean condition = libraryService.updateStatus(libraryId, status);
        assertTrue(condition);
    }

    @Test
    void update() throws ServiceException {
        String city = "city";
        String street = "street";
        String libraryId = "1 ";
        boolean condition = libraryService.update(libraryId, city, street);
        assertTrue(condition);
    }

    @Test
    void showById() throws ServiceException  {
        String libraryId ="1 ";
        Optional<Library> optionalLibrary = libraryService.showById(libraryId);
        assertTrue(optionalLibrary.isPresent());
    }

    @Test
    void showByCity() throws ServiceException {
        String city = "Минск";
        Optional<Library> optionalLibrary = libraryService.showByCity(city);
        assertTrue(optionalLibrary.isPresent());
    }

    @Test
    public void showByStatus() throws ServiceException {
        List<Library> libraries;
        LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
        String status = "opened";
        libraries = libraryService.showByStatus(status);
        assertNotNull(libraries);
    }
}