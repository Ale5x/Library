package com.epam.library.dao.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.WishBookDao;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.WishBookMapper;
import com.epam.library.entity.WishBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class {@link WishBookDaoImpl} is an implementation of the 'WishBookDao' interface.
 *
 * @author Alexander Pishchala
 */

public class WishBookDaoImpl extends DaoHelper implements WishBookDao {

    private static final Logger logger = LoggerFactory.getLogger(WishBookDaoImpl.class);

    private static final String ADD_WISH_BOOKS_QUERY = String.format("insert into %s(%s, %s) values(?, ?)",
            TableName.WISH_BOOK, ColumnName.WISH_BOOK_ID_BOOK, ColumnName.WISH_BOOK_ID_USER);

    private static final String GET_WISH_BOOK_USER_QUERY = String.format("select * from %s where %s=? and %s=?",
            TableName.WISH_BOOK, ColumnName.WISH_BOOK_ID_BOOK, ColumnName.WISH_BOOK_ID_USER);

    private static final String GET_WISH_BOOK_BY_ID_QUERY = String.format("select * from %s where %s=?",
            TableName.WISH_BOOK, ColumnName.WISH_BOOK_ID);

    private static final String DELETE_WISH_BOOKS_QUERY = String.format("delete from %s where %s=?",
            TableName.WISH_BOOK, ColumnName.WISH_BOOK_ID);

    private final static String GET_COUNT_QUERY = String.format("select count(%s) from %s", ColumnName.WISH_BOOK_ID,
            TableName.WISH_BOOK);

    @Override
    public boolean create(WishBook wishBook) throws DaoException {
        logger.info("Creating wish book");
        PreparedStatement prStatement = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, ADD_WISH_BOOKS_QUERY, wishBook.getBookId(),
                    wishBook.getUserId());
            return prStatement.execute();
        }catch (SQLException e) {
            logger.error("Error adding favorite book to list.");
            throw new DaoException("Error adding favorite book to list.", e);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public int delete(long wishBookId) throws DaoException {
        logger.info("Deleting wish book");
        PreparedStatement prStatement = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, DELETE_WISH_BOOKS_QUERY, wishBookId);
            return prStatement.executeUpdate();
        }catch (SQLException e) {
            logger.error("Error deleting favorite book from list.");
            throw new DaoException("Error deleting favorite book from list.", e);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public long getCountWishBooks() throws DaoException {
        logger.info("Getting the number of favorite books.");
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_COUNT_QUERY);
            resultSet = prStatement.executeQuery();
            int countBooks = 0;
            while (resultSet.next()) {
                countBooks = resultSet.getInt(1);
            }
            return countBooks;
        }catch (SQLException e) {
            logger.error("Error when getting the number of favorite books.");
            throw new DaoException("Error when getting the number of favorite books.", e);
        }finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<WishBook> getWishBookByBookAndByUser(long bookId, long userId) {
        logger.info("Getting wish book.");
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            List<WishBook> entity = new ArrayList<>();
            prStatement = createPreparedStatement(connection, GET_WISH_BOOK_USER_QUERY, bookId, userId);
            resultSet = prStatement.executeQuery();
            WishBookMapper mapper = new WishBookMapper();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if(entity.size() == 1) {
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                logger.info("Found more than one book for the user.");
                throw new DaoException("Found more than one book for the user.");
            }
        } catch (SQLException e) {
            logger.error("Error while getting wish book.");
            throw new DaoException("Error while getting wish book.", e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<WishBook> getWishBookById(long wishBookId) {
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            List<WishBook> entity = new ArrayList<>();
            prStatement = createPreparedStatement(connection, GET_WISH_BOOK_BY_ID_QUERY, wishBookId);
            resultSet = prStatement.executeQuery();
            WishBookMapper mapper = new WishBookMapper();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if(entity.size() == 1) {
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                logger.info("Find more 1 wishBook.");
                throw new DaoException("Find more 1 wishBook.");
            }
        } catch (SQLException e) {
            logger.error("Error while getting wish book.");
            throw new DaoException("Error while getting wish book.", e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }
}
