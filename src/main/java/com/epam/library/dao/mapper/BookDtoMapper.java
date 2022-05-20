package com.epam.library.dao.mapper;

import com.epam.library.dao.constant.ColumnName;
import com.epam.library.entity.Author;
import com.epam.library.entity.Genre;
import com.epam.library.entity.dto.BookDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class {@link BookDtoMapper} builds the entity 'BookDto'.
 *
 *
 * @author Alexander Pishchala
 */

public class BookDtoMapper implements RowMapper<BookDto> {

    @Override
    public BookDto map(ResultSet resultSet) throws SQLException {
        BookDto bookDto = new BookDto();
        bookDto.setBookDtoId(resultSet.getLong(ColumnName.BOOK_ID_BOOK));
        bookDto.setCityLibrary(resultSet.getString(ColumnName.LIBRARY_CITY));
        bookDto.setTitle(resultSet.getString(ColumnName.BOOK_TITLE));
        bookDto.setQuantity(resultSet.getInt(ColumnName.BOOK_QUANTITY));
        bookDto.setBorrow(resultSet.getInt(ColumnName.BOOK_BORROW));
        bookDto.setPublisher(resultSet.getString(ColumnName.BOOK_PUBLISHER));
        bookDto.setDescription(resultSet.getString(ColumnName.BOOK_DESCRIPTION));
        bookDto.setYear(resultSet.getString(ColumnName.BOOK_YEAR));
        bookDto.setAdded(resultSet.getDate(ColumnName.BOOK_ADDED).toLocalDate());
        bookDto.setIsbn(resultSet.getString(ColumnName.BOOK_ISBN));
        bookDto.setShelf(resultSet.getString(ColumnName.BOOK_SHELF));

        List<String> authorLine = Arrays.asList(resultSet.getString(ColumnName.BOOK_UNION_AUTHORS).split(","));
        List<String> genreLine = Arrays.asList(resultSet.getString(ColumnName.BOOK_UNION_GENRES).split(","));

        List<Author> authors = new ArrayList<>();
        for (String author : authorLine) {
            authors.add(new Author(author.trim()));
        }

        List<Genre> genres = new ArrayList<>();
        for (String genre : genreLine) {
            genres.add(new Genre(genre.trim()));
        }

        bookDto.setAuthors(authors);
        bookDto.setGenres(genres);
        return bookDto;
    }
}
