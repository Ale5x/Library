package com.epam.library.dao.impl;

import com.epam.library.dao.BookDao;
import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.BookMapper;
import com.epam.library.entity.Book;
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
 * Class {@link BookDaoImpl} is an implementation of the 'BookDao' interface.
 *
 * @author Alexander Pishchala
 */

public class BookDaoImpl extends DaoHelper implements BookDao {

    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    private static final String ADD_BOOK_QUERY = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s, %s) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, (SELECT %s FROM %s WHERE %s=?))", TableName.BOOK, ColumnName.BOOK_SHELF,
            ColumnName.BOOK_TITLE, ColumnName.BOOK_QUANTITY, ColumnName.BOOK_BORROW, ColumnName.BOOK_PUBLISHER,
            ColumnName.BOOK_DESCRIPTION, ColumnName.BOOK_YEAR, ColumnName.BOOK_ISBN, ColumnName.BOOK_ID_LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.LIBRARY, ColumnName.LIBRARY_CITY);

    private static final String UPDATE_BOOK_QUERY = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=?, " +
            "%s=?, %s=?, %s=?, %s=(SELECT %s FROM %s WHERE %s=?) where %s=?", TableName.BOOK, ColumnName.BOOK_ISBN,
            ColumnName.BOOK_TITLE, ColumnName.BOOK_QUANTITY, ColumnName.BOOK_BORROW, ColumnName.BOOK_PUBLISHER,
            ColumnName.BOOK_DESCRIPTION, ColumnName.BOOK_SHELF, ColumnName.BOOK_ID_LIBRARY, ColumnName.LIBRARY_ID_LIBRARY,
            TableName.LIBRARY, ColumnName.LIBRARY_CITY, ColumnName.BOOK_ID_BOOK);

    private static final String GET_BOOK_BY_ID = String.format("SELECT * FROM %s left join %s on %s.%s=%s.%s WHERE %s=?",
            TableName.BOOK, TableName.LIBRARY, TableName.BOOK, ColumnName.BOOK_ID_LIBRARY, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, ColumnName.BOOK_ID_BOOK);

    private final static String GET_COUNT_BOOKS_QUERY = String.format("SELECT COUNT(%s) FROM %s;",
            ColumnName.BOOK_TITLE, TableName.BOOK);


    @Override
    public boolean create(Book book) throws DaoException {
        logger.info("Adding a bookDto.");
        PreparedStatement prStatement = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
        prStatement = createPreparedStatement(connection, ADD_BOOK_QUERY, book.getShelf(), book.getTitle(),
                book.getQuantity(), book.getBorrow(), book.getPublisher(), book.getDescription(),
                book.getYear(), book.getIsbn(), book.getCityLibrary());
        prStatement.execute();
        return true;
        }catch (SQLException sql) {
            logger.error("An error occurred while adding a book to the system.");
            throw new DaoException("An error occurred while adding a book to the system.", sql);
        }
    }

    @Override
    public int update(Book book) throws DaoException {
        logger.info("Updating a book by ID.");
        PreparedStatement prStatement = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, UPDATE_BOOK_QUERY, book.getIsbn(), book.getTitle(),
                    book.getQuantity(), book.getBorrow(), book.getPublisher(), book.getDescription(), book.getShelf(),
                    book.getCityLibrary(), book.getBookId());
            return prStatement.executeUpdate();
        }catch (SQLException sqlE) {
            logger.error("Error while updating the book.");
            throw new DaoException("Error while updating the book.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<Book> getBookById(long bookId) throws DaoException {
        logger.info("Receiving a book by ID.");
        BookMapper mapper = new BookMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<Book> entity = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, GET_BOOK_BY_ID, bookId);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }

            if(entity.size() == 1) {
                logger.info("Book by id received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                throw new DaoException("Find more 1 Book.");
            }
        } catch (SQLException sqlE) {
            logger.error("Error while receiving a book by ID.");
            throw new DaoException("Error while receiving a book by ID.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public long getCountBooks() throws DaoException {
        logger.info("Getting count books.");
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        long countBook = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = connection.prepareStatement(GET_COUNT_BOOKS_QUERY);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                countBook = resultSet.getLong(1);
            }
        } catch (SQLException sqlE) {
            logger.error("Number of books not received");
            throw new DaoException("Number of books not received", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Books list received.");
        return countBook;
    }
}
