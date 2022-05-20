package com.epam.library.dao.impl;

import com.epam.library.dao.AuthorDao;
import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.AuthorMapper;
import com.epam.library.entity.Author;
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
 * Class {@link AuthorDaoImpl} is an implementation of the 'AuthorDao' interface.
 *
 * @author Alexander Pishchala
 */

public class AuthorDaoImpl extends DaoHelper implements AuthorDao {

    private static final Logger logger = LoggerFactory.getLogger(AuthorDaoImpl.class);

    private static final String ADD_AUTHOR_QUERY = String.format("INSERT IGNORE INTO %s(%s) VALUES(?)",
            TableName.AUTHORS, ColumnName.AUTHOR_NAME);

    private final static String GET_AUTHOR_BY_NAME_QUERY = String.format("SELECT * FROM %s WHERE %s=?;",
            TableName.AUTHORS, ColumnName.AUTHOR_NAME);

    private final static String GET_AUTHOR_BY_ID_QUERY = String.format("SELECT * FROM %s WHERE %s=?;",
            TableName.AUTHORS, ColumnName.AUTHOR_ID_AUTHOR);

    private final static String GET_ALL_AUTHOR_QUERY = String.format("SELECT * FROM %s order by %s;",
            TableName.AUTHORS, ColumnName.AUTHOR_ID_AUTHOR);

    private final static String GET_BY_PART_NAME_QUERY = String.format("SELECT * FROM %s WHERE %s LIKE ",
            TableName.AUTHORS, ColumnName.AUTHOR_NAME);

    private final static String GET_COUNT_BOOKS_BY_AUTHOR_QUERY = String.format("select count(%s) from %s where %s=?",
            ColumnName.AHB_ID_BOOK, TableName.A_H_B, ColumnName.AHB_ID_AUTHORS);

    private final static String GET_COUNT_QUERY = String.format("select count(%s) from %s", ColumnName.AUTHOR_NAME,
            TableName.AUTHORS);

    private final static String UPDATE_AUTHOR_BY_ID_QUERY = String.format("UPDATE %s SET %s=? WHERE %s=?;",
            TableName.AUTHORS, ColumnName.AUTHOR_NAME, ColumnName.AUTHOR_ID_AUTHOR);

    private static final String DELETE_AUTHOR_BY_BOOK_QUERY = String.format("delete from %s where %s=?",
            TableName.A_H_B, ColumnName.AHB_ID_BOOK);


    @Override
    public boolean create(Author author) throws DaoException {
        logger.info("Start to create Author.");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, ADD_AUTHOR_QUERY, author.getName());
            prStatement.execute();
            return true;
        } catch (SQLException sqlE) {
            logger.error("Author not added. Author - {}", author);
            throw new DaoException("Author not added - " + author, sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public boolean update(Author author) throws DaoException {
        logger.info("Start to update by id.");
        PreparedStatement prStatement = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, UPDATE_AUTHOR_BY_ID_QUERY, author.getName(), author.getAuthorId());
            prStatement.execute();
            return true;
        } catch (SQLException sqlE) {
            logger.error("No author update by id. Author - {}", author.toString());
            throw new DaoException(sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public boolean deleteAuthorByBookId(long bookId) throws DaoException {
        logger.info("Deleting author data by book. BookId - {}", bookId);
        PreparedStatement prStatement = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, DELETE_AUTHOR_BY_BOOK_QUERY, bookId);
            prStatement.executeUpdate();
            return true;
        } catch (SQLException sqlE) {
            logger.error("Error while deleting author data by book. BookDto id - {}", bookId);
            throw new DaoException("Error while deleting author data by book.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<Author> getAuthorByName(String name) throws DaoException {
        logger.info("Receiving an author by name.");
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<Author> entity = new ArrayList<>();
        AuthorMapper mapper = new AuthorMapper();
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_AUTHOR_BY_NAME_QUERY, name);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if(entity.size() == 1) {
                logger.info("Author by name received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                throw new DaoException("Find more 1 author.");
            }
        } catch (SQLException sqlE) {
            logger.error("Error getting author by name.");
            throw new DaoException("Error getting author by name.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<Author> getAuthorById(long authorId) throws DaoException {
        logger.info("Receiving an author by ID.");
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<Author> entity = new ArrayList<>();
        AuthorMapper mapper = new AuthorMapper();
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_AUTHOR_BY_ID_QUERY, authorId);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if(entity.size() == 1) {
                logger.info("Author by ID received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                throw new DaoException("Find more 1 author.");
            }

        } catch (SQLException sqlE) {
            logger.error("Find more 1 author by ID. Find - {}", entity);
            throw new DaoException("Find more 1 author by ID.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public int getCountAuthors() throws DaoException {
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        int countAuthors = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = connection.prepareStatement(GET_COUNT_QUERY);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                countAuthors = resultSet.getInt(1);
            }
        }catch (SQLException sqlE) {
            logger.error("Number of authors not received.");
            throw new DaoException("Number of authors not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        return countAuthors;
    }

    @Override
    public int getCountBooksByAuthor(int authorId) throws DaoException {
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        int countAuthors = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, GET_COUNT_BOOKS_BY_AUTHOR_QUERY, authorId);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                countAuthors = resultSet.getInt(1);
            }
        }catch (SQLException sqlE) {
            logger.error("Number of books by authors not received.");
            throw new DaoException("Number of books by authors not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        return countAuthors;
    }

    @Override
    public List<Author> getAuthors() throws DaoException {
        logger.info("Getting a list of authors.");
        List<Author> authors = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = connection.prepareStatement(GET_ALL_AUTHOR_QUERY);
            resultSet = prStatement.executeQuery();
            AuthorMapper mapper = new AuthorMapper();
            while (resultSet.next()) {
                authors.add(mapper.map(resultSet));
            }
        }catch (SQLException sqlE) {
            logger.error("Authors not received.");
            throw new DaoException("Authors received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Authors list received.");
        return authors;
    }

    @Override
    public List<Author> getAllAuthorByPartName(String partName) throws DaoException {
        logger.info("Getting a list of authors by part name.");
        StringBuilder builder = new StringBuilder();
        List<Author> authors = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            builder.append(GET_BY_PART_NAME_QUERY).append(ColumnName.CHAR_HATCH).append(ColumnName.CHAR_PERCENT)
                    .append(partName).append(ColumnName.CHAR_PERCENT).append(ColumnName.CHAR_HATCH);
            prStatement = connection.prepareStatement(builder.toString());
            resultSet = prStatement.executeQuery();
            AuthorMapper mapper = new AuthorMapper();
            while (resultSet.next()) {
                authors.add(mapper.map(resultSet));
            }
        }catch (SQLException sqlE) {
            logger.error("Authors by part name not received.");
            throw new DaoException("Authors by part name not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Authors by part name list received.");
        return authors;
    }
}
