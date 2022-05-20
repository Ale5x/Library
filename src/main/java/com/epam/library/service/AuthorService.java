package com.epam.library.service;

import com.epam.library.entity.Author;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@link AuthorService} is service interface which provide operations on author
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public interface AuthorService {

    /**
     * The method is used to create an author by name.
     *
     * @param name    specified name of new author.
     * @return <tt>true</tt> if author with specified parameter was created.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean create(String name) throws ServiceException;

    /**
     * The method is used to update the  author by id.
     *
     * @param name    specified name of author.
     * @param authorId    specified id of author.
     * @return <tt>true</tt> if author with specified parameter was update.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean update(String authorId, String name) throws ServiceException;

    /**
     * The method is used to get count authors.
     *
     * @return Returns the number of authors in the system.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    int getCountAuthors() throws ServiceException;

    /**
     * The method is used to get the number of books by an author.
     *
     * @param authorId    specified id of author.
     * @return Returns the number of authors in the system.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    int getCountBooksByAuthor(String authorId) throws ServiceException;

    /**
     * The method is used to get the author by name.
     *
     * @param name    specified name of author.
     * @return Return author.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    Optional<Author> showAuthorByName(String name) throws ServiceException;

    /**
     * The method is used to get the author by id.
     *
     * @param authorId    specified id of author.
     * @return Return author.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    Optional<Author> showAuthorById(String authorId) throws ServiceException;

    /**
     * The method is used to get the authors.
     *
     * @return list of authors.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<Author> showAllAuthors() throws ServiceException;
}
