package com.epam.library.dao;

import com.epam.library.entity.Author;
import com.epam.library.entity.Book;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@link AuthorDao} provides operation with data of database table 'authors'.
 *
 * @author Alexander Pishchala
 */

public interface AuthorDao {

    /**
     * The method creates new record in database table.
     *
     * @param author entity that specifies creation of new records in database table.
     * @return <tt>true</tt> if new row in database table was created successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean create(Author author) throws DaoException;

    /**
     * The method updates specified record in database table.
     *
     * @param author that will be updated in database table.
     * @return <tt>true</tt> if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean update(Author author) throws DaoException;

    /**
     * The method deletes specified record in database table.
     *
     * @param bookId that will be deleted in database table.
     * @return <tt>true</tt> if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean deleteAuthorByBookId(long bookId) throws DaoException;

    /**
     * The method returns specified Author by name.
     *
     * @param name Author by name.
     * @return specified Optional Author by name.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<Author> getAuthorByName(String name) throws DaoException;

    /**
     * The method returns specified Author by id.
     *
     * @param authorId Author by name.
     * @return specified Optional Author by id.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<Author> getAuthorById(long authorId) throws DaoException;

    /**
     * The method will return the number of authors.
     *
     * @return Authors count.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    int getCountAuthors() throws DaoException;

    /**
     * The method will return the number of books by the author id.
     *
     * @param authorId Author id.
     * @return Books count.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    int getCountBooksByAuthor(int authorId) throws DaoException;

    /**
     * The method will return list Authors.
     *
     * @return list of authors.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<Author> getAuthors() throws DaoException;

    /**
     * The method will return list Authors by part name.
     *
     * @return list of authors.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<Author> getAllAuthorByPartName(String partName);

}
