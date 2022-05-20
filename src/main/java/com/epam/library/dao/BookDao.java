package com.epam.library.dao;

import com.epam.library.entity.Book;

import java.util.Optional;

/**
 * Interface {@link BookDao} provides operation with data of database table 'books'.
 *
 * @author Alexander Pishchala
 */

public interface BookDao {

    /**
     * The method creates new record in database table.
     *
     * @param book entity that specifies creation of new records in database table.
     * @return <tt>true</tt> if new row in database table was created successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean create(Book book) throws DaoException;

    /**
     * The method updates specified record in database table.
     *
     * @param book that will be updated in database table.
     * @return count row if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    int update(Book book) throws DaoException;

    /**
     * The method returns specified Book by id.
     *
     * @param bookId Book id.
     * @return specified Optional Book by id.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<Book> getBookById(long bookId) throws DaoException;

    /**
     * The method will return the number of Books.
     *
     * @return Books count.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    long getCountBooks() throws DaoException;

}
