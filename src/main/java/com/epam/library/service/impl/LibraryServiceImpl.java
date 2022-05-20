package com.epam.library.service.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoFactory;
import com.epam.library.dao.LibraryDao;
import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;
import com.epam.library.service.LibraryService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.ServiceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Class {@link LibraryServiceImpl} is service class which provide operations a library
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public class LibraryServiceImpl implements LibraryService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);

    @Override
    public boolean create(String city, String street) throws ServiceException {
        try {
            LibraryDao libraryDao = DaoFactory.getInstance().getLibraryDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
                if (validator.isLength(city) && validator.isLengthStreet(street)) {
                    Optional<Library> optionalLibrary = libraryDao.getLibraryByCity(city);
                    if(optionalLibrary.isEmpty()) {
                        Library library = new Library();
                        library.setStreet(street);
                        library.setCity(city);
                        library.setStatus(LibraryStatus.OPENED);
                        libraryDao.create(library);
                        return true;
                    } else {
                        throw new ServiceException("Trying to build a library that exists on the system.");
                    }
                } else {
                    return false;
                }
        }catch (DaoException e) {
            logger.error("Error while creating a library.");
            throw new ServiceException("Error while creating a library.", e);
        }
    }

    @Override
    public List<Library> showAll() throws ServiceException {
        try {
            LibraryDao dao = DaoFactory.getInstance().getLibraryDAO();
            return dao.getLibraries();
        }catch (DaoException e) {
            logger.error("Error getting all libraries.");
            throw new ServiceException("Error getting all libraries.", e);
        }
    }

    @Override
    public boolean updateStatus(String libraryId, String status) throws ServiceException {
        try {
            LibraryDao libraryDAO = DaoFactory.getInstance().getLibraryDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(libraryId)) {
                if (status.equalsIgnoreCase(LibraryStatus.OPENED.name()) ||
                        status.equalsIgnoreCase(LibraryStatus.CLOSED.name())) {
                    Optional<Library> optionalLibrary = libraryDAO.getLibraryById(Long.parseLong(libraryId.trim()));
                    if (optionalLibrary.isPresent()) {
                        Library library = optionalLibrary.get();
                        library.setStatus(LibraryStatus.valueOf(status.toUpperCase()));
                        libraryDAO.update(library);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                        throw new ServiceException("An error occurred in services while updating the status in the " +
                                "library. Incorrect status");
                }
            } else {
                throw new ServiceException("Trying to get a library by ID using a text string that does not " +
                            "have a digit");
            }
        }catch (DaoException e) {
            logger.error("Services error when updating the status in the library.");
            throw new ServiceException("Services error when updating the status in the library.", e);
        }
    }

    @Override
    public boolean update(String libraryId, String city, String street) throws ServiceException {
        try {
            LibraryDao libraryDao = DaoFactory.getInstance().getLibraryDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(libraryId.trim())) {
                Optional<Library> optionalLibrary = libraryDao.getLibraryById(Long.parseLong(libraryId.trim()));
                if (optionalLibrary.isPresent()) {
                    if (validator.isLengthForUpdate(city) && validator.isLengthForUpdateStreet(street)) {
                        Library library = new Library();
                        library.setLibraryId(optionalLibrary.get().getLibraryId());
                        library.setCity(city != "" ? city : optionalLibrary.get().getCity());
                        library.setStreet(street != "" ? street : optionalLibrary.get().getStreet());
                        library.setStatus(optionalLibrary.get().getStatus());
                        libraryDao.update(library);
                        return true;
                    }
                } else {
                    throw new ServiceException("Attempting to update a library that exists on the system.");
                }
            } else {
                throw new ServiceException("Trying to get a library by ID using a text string that does not " +
                        "have a digit");
            }
            return false;
        }catch (DaoException e) {
            logger.error("Services error when updating the library.");
            throw new ServiceException("Services error when updating the library.", e);
        }
    }

    @Override
    public Optional<Library> showById(String libraryId) throws ServiceException {
        try {
            LibraryDao libraryDao = DaoFactory.getInstance().getLibraryDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            Optional<Library> libraryOptional = Optional.empty();

            if (validator.isNumber(libraryId.trim())) {
                libraryOptional = libraryDao.getLibraryById(Long.parseLong(libraryId.trim()));
            } else {
                throw new ServiceException("Trying to get a library by ID using a text string that does not " +
                        "have a digit");
            }
            return libraryOptional;
        } catch (DaoException e) {
            logger.error("Error in services when retrieving a library by ID.");
            throw new ServiceException("Error in services when retrieving a library by ID.", e);
        }
    }

    @Override
    public Optional<Library> showByCity(String city) throws ServiceException {
        try {
            LibraryDao libraryDao = DaoFactory.getInstance().getLibraryDAO();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            Optional<Library> optionalLibrary = Optional.empty();
            optionalLibrary = libraryDao.getLibraryByCity(city);
            return optionalLibrary;
        }catch (DaoException e) {
            logger.error("Error in services when getting a library around the city.");
            throw new ServiceException("Error in services when getting a library around the city.", e);
        }
    }

    @Override
    public List<Library> showByStatus(String status) throws ServiceException {
        try {
            LibraryDao libraryDao = DaoFactory.getInstance().getLibraryDAO();
            if (status.equalsIgnoreCase(LibraryStatus.OPENED.name()) ||
                    status.equalsIgnoreCase(LibraryStatus.CLOSED.name())) {
                return libraryDao.getLibrariesByStatus(LibraryStatus.valueOf(status.toUpperCase()));
            } else {
                throw new ServiceException("Unknown library status");
            }
        } catch (DaoException e) {
            logger.error("Error in services when retrieving a libraries by status.");
            throw new ServiceException("Error in services when retrieving a libraries by status.", e);
        }
    }
}
