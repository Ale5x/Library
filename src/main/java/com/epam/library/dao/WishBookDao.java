package com.epam.library.dao;

import com.epam.library.entity.WishBook;

import java.util.Optional;

/**
 * Interface {@link WishBookDao} provides operation with data of database table 'wish_books'.
 *
 * @author Alexander Pishchala
 */

public interface WishBookDao {

    /**
     * The method creates new record in database table.
     *
     * @param wishBook entity that specifies creation of new records in database table.
     * @return <tt>true</tt> if new row in database table was created successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean create(WishBook wishBook) throws DaoException;

    /**
     * The method deletes specified record in database table.
     *
     * @param wishBookId WishBook id.
     * @return count row if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    int delete(long wishBookId) throws DaoException;

    /**
     * The method will return the number of WishBooks.
     *
     * @return WishBooks count.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    long getCountWishBooks() throws DaoException;

    /**
     * The method returns specified WishBook by userId and bookId.
     *
     * @param bookId WishBook by bookId.
     * @param userId WishBook by userId.
     * @return specified Optional WishBook by bookId and userId.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<WishBook> getWishBookByBookAndByUser(long bookId, long userId);

    /**
     * The method returns specified WishBook by id.
     *
     * @param wishBookId WishBook by id.
     * @return specified Optional WishBook by id.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<WishBook> getWishBookById(long wishBookId);
}
