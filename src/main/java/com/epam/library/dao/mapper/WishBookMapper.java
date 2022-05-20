package com.epam.library.dao.mapper;

import com.epam.library.dao.constant.ColumnName;
import com.epam.library.entity.WishBook;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@link WishBookMapper} builds the entity 'WishBook'.
 *
 *
 * @author Alexander Pishchala
 */

public class WishBookMapper implements RowMapper<WishBook> {

    @Override
    public WishBook map(ResultSet resultSet) throws SQLException {
        WishBook wishBook = new WishBook();
        wishBook.setWishBookId(resultSet.getLong(ColumnName.WISH_BOOK_ID));
        wishBook.setBookId(resultSet.getLong(ColumnName.WISH_BOOK_ID_BOOK));
        wishBook.setUserId(resultSet.getLong(ColumnName.WISH_BOOK_ID_USER));
        wishBook.setAdded(resultSet.getDate(ColumnName.WISH_BOOK_ADDED).toLocalDate());
        return wishBook;
    }
}
