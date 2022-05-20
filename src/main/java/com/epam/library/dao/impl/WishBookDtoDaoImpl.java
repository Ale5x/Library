package com.epam.library.dao.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.WishBookDtoDao;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.WishBookDtoMapper;
import com.epam.library.entity.dto.WishBookDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@link WishBookDtoDaoImpl} is an implementation of the 'WishBookDtoDao' interface.
 *
 * @author Alexander Pishchala
 */

public class WishBookDtoDaoImpl extends DaoHelper implements WishBookDtoDao {

    private static final Logger logger = LoggerFactory.getLogger(WishBookDtoDaoImpl.class);

    private static final String GET_WISH_BOOKS_QUERY = String.format("SELECT * FROM %s left join %s on(%s.%s=%s.%s) " +
            " where %s.%s=? group by %s.%s",  TableName.WISH_BOOK,
            TableName.BOOK, TableName.WISH_BOOK, ColumnName.WISH_BOOK_ID_BOOK, TableName.BOOK, ColumnName.BOOK_ID_BOOK,
            TableName.WISH_BOOK, ColumnName.WISH_BOOK_ID_USER, TableName.WISH_BOOK, ColumnName.WISH_BOOK_ADDED);

    @Override
    public List<WishBookDto> getBooks(long userId) throws DaoException {
        logger.info("Getting wish books.");
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            List<WishBookDto> books = new ArrayList<>();
            prStatement = createPreparedStatement(connection, GET_WISH_BOOKS_QUERY, userId);
            resultSet = prStatement.executeQuery();
            WishBookDtoMapper mapper = new WishBookDtoMapper();
            while (resultSet.next()) {
                books.add(mapper.map(resultSet));
            }
            return books;
        } catch (SQLException e) {
            logger.error("Error while getting books.");
            throw new DaoException("Error while getting books.", e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }
}
