package com.epam.library.dao.mapper;

import com.epam.library.dao.constant.ColumnName;
import com.epam.library.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@link GenreMapper} builds the entity 'Genre'.
 *
 *
 * @author Alexander Pishchala
 */

public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre map(ResultSet resultSet) throws SQLException {
        Genre genre = new Genre();
        genre.setGenreId(resultSet.getLong(ColumnName.GENRES_ID_GENRE));
        genre.setCategory(resultSet.getString(ColumnName.GENRES_GENRE));
        return genre;
    }
}
