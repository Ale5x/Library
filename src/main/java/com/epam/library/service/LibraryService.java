package com.epam.library.service;

import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@link LibraryService} is service interface which provide operations a library
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public interface LibraryService {

    /**
     * The method is used to create the library.
     *
     * @param city    specified city of new library.
     * @param street    specified street of new library.
     * @return <tt>true</tt> if library with specified parameter was created.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean create(String city, String street) throws ServiceException;

    /**
     * The method is used to get the libraries.
     *
     * @return list of libraries.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<Library> showAll() throws ServiceException;

    /**
     * The method is used to get the library by id.
     *
     * @param libraryId    specified id of library.
     * @return Return library.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    Optional<Library> showById(String libraryId) throws ServiceException;

    /**
     * The method is used to get the library by city.
     *
     * @param city    specified city of library.
     * @return Return library.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    Optional<Library> showByCity(String city) throws ServiceException;

    /**
     * The method is used to get the libraries by status.
     *
     * @param status    specified status of library.
     * @return list of libraries.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<Library> showByStatus(String status) throws ServiceException;

    /**
     * The method is used to update status of library.
     *
     * @param libraryId    specified id of library.
     * @param status    specified status of library.
     * @return <tt>true</tt> if library with specified parameter was update.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean updateStatus(String libraryId, String status) throws ServiceException;

    /**
     * The method is used to update library.
     *
     * @param libraryId    specified id of library.
     * @param city    specified city of library.
     * @param street    specified street of library.
     * @return <tt>true</tt> if library with specified parameter was update.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean update(String libraryId, String city, String street) throws ServiceException;
}
