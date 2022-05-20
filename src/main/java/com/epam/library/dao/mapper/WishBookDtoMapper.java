package com.epam.library.dao.mapper;

import com.epam.library.dao.constant.ColumnName;
import com.epam.library.entity.dto.WishBookDto;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@link WishBookDtoMapper} builds the entity 'WishBookDto'.
 *
 *
 * @author Alexander Pishchala
 */

public class WishBookDtoMapper implements RowMapper<WishBookDto>{

    @Override
    public WishBookDto map(ResultSet resultSet) throws SQLException {
        WishBookDto wishBook = new WishBookDto();
        wishBook.setWishBooksId(resultSet.getLong(ColumnName.WISH_BOOK_ID));
        wishBook.setBookId(resultSet.getLong(ColumnName.BOOK_ID_BOOK));
        wishBook.setUserId(resultSet.getLong(ColumnName.WISH_BOOK_ID_USER));
        wishBook.setTitle(resultSet.getString(ColumnName.BOOK_TITLE));
        wishBook.setPublisher(resultSet.getString(ColumnName.BOOK_PUBLISHER));
        wishBook.setYear(resultSet.getString(ColumnName.BOOK_YEAR));
        wishBook.setIsbn(resultSet.getString(ColumnName.BOOK_ISBN));
        wishBook.setQuantity(resultSet.getInt(ColumnName.BOOK_QUANTITY));
        wishBook.setBorrow(resultSet.getInt(ColumnName.BOOK_BORROW));

        return wishBook;
    }
}
