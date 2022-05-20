package com.epam.library.service;

import com.epam.library.entity.Genre;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@link GenreService} is service interface which provide operations a genre
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public interface GenreService {

    /**
     * The method is used to get the genres.
     *
     * @return list of genres.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<Genre> getGenres() throws ServiceException;

    /**
     * The method is used to create genre by category.
     *
     * @param category    specified category of new genre.
     * @return <tt>true</tt> if genre with specified parameter was created.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean create(String category) throws ServiceException;

    /**
     * The method is used to update genre by id.
     *
     * @param genreId    specified name of genre.
     * @param category    specified id of genre.
     * @return <tt>true</tt> if genre with specified parameter was update.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean update(String genreId, String category) throws ServiceException;

    /**
     * The method is used to get the number of books by a genre.
     *
     * @return Returns the number of genres in the system.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    long getCountGenres() throws ServiceException;

    /**
     * The method is used to get the number of books by a genre.
     *
     * @param genreId    specified id of genre.
     * @return Returns the number of genres in the system.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    long getCountBooksByGenres(String genreId) throws ServiceException;

    /**
     * The method is used to get the genre by category.
     *
     * @param category    specified category of genre.
     * @return Return genre.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    Optional<Genre> showGenreByCategory(String category) throws ServiceException;

    /**
     * The method is used to get the genre by id.
     *
     * @param genreId    specified id of genre.
     * @return Return genre.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    Optional<Genre> showGenreById(String genreId) throws ServiceException;
}
