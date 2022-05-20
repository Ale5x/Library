package com.epam.library.dao.mapper;

import com.epam.library.dao.constant.ColumnName;
import com.epam.library.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@link BookMapper} builds the entity 'Book'.
 *
 *
 * @author Alexander Pishchala
 */

public class BookMapper implements RowMapper<Book>{

    @Override
    public Book map(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setBookId(resultSet.getLong(ColumnName.BOOK_ID_BOOK));
        book.setCityLibrary(resultSet.getString(ColumnName.LIBRARY_CITY));
        book.setIsbn(resultSet.getString(ColumnName.BOOK_ISBN));
        book.setTitle(resultSet.getString(ColumnName.BOOK_TITLE));
        book.setQuantity(resultSet.getInt(ColumnName.BOOK_QUANTITY));
        book.setBorrow(resultSet.getInt(ColumnName.BOOK_BORROW));
        book.setPublisher(resultSet.getString(ColumnName.BOOK_PUBLISHER));
        book.setDescription(resultSet.getString(ColumnName.BOOK_DESCRIPTION));
        book.setYear(resultSet.getString(ColumnName.BOOK_YEAR));
        book.setAdded(resultSet.getDate(ColumnName.BOOK_ADDED).toLocalDate());
        book.setShelf(resultSet.getString(ColumnName.BOOK_SHELF));
        return book;
    }
}
