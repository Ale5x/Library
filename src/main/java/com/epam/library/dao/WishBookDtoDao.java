package com.epam.library.dao;

import com.epam.library.entity.dto.WishBookDto;

import java.util.List;

/**
 * Interface {@link WishBookDtoDao} provides operation with data of database table 'wish_book', 'users', 'books'.
 *
 * @author Alexander Pishchala
 */

public interface WishBookDtoDao {

    /**
     * The method will return list WishBook Dto by User.
     *
     * @param userId User id.
     * @return list of WishBook Dto.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<WishBookDto> getBooks(long userId) throws DaoException;

}
