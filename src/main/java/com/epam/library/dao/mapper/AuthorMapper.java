package com.epam.library.dao.mapper;

import com.epam.library.dao.constant.ColumnName;
import com.epam.library.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@link AuthorMapper} builds the entity 'Author'.
 *
 *
 * @author Alexander Pishchala
 */

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author map(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setAuthorId(resultSet.getLong(ColumnName.AUTHOR_ID_AUTHOR));
        author.setName(resultSet.getString(ColumnName.AUTHOR_NAME));
        return author;
    }
}
