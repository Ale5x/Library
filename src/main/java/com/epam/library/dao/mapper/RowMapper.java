package com.epam.library.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interface {@link RowMapper} for processing ResultSet.
 *
 * @param <T> entity type.
 *
 * @author Alexander Pishchala
 */

public interface RowMapper<T> {

    /**
     * The method will return to build the entity.
     *
     * @param resultSet row of data received to build the entity.
     * @return entity type.
     * @throws {@link java.sql.SQLException} if the method encounters errors during the construction of the entity.
     */
    T map(ResultSet resultSet) throws SQLException;
}
