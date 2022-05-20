package com.epam.library.dao.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.GenreDao;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.AuthorMapper;
import com.epam.library.dao.mapper.GenreMapper;
import com.epam.library.entity.Author;
import com.epam.library.entity.Genre;
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
 * Class {@link GenreDaoImpl} is an implementation of the 'GenreDao' interface.
 *
 * @author Alexander Pishchala
 */

public class GenreDaoImpl extends DaoHelper implements GenreDao {

    private static final Logger logger = LoggerFactory.getLogger(GenreDaoImpl.class);

    private static final String ADD_GENRE_QUERY = String.format("INSERT IGNORE INTO %s(%s) VALUES(?)",
            TableName.GENRES, ColumnName.GENRES_GENRE);

    private final static String UPDATE_GENRE_BY_ID_QUERY = String.format("UPDATE %s SET %s=? WHERE %s=?;",
            TableName.GENRES, ColumnName.GENRES_GENRE, ColumnName.GENRES_ID_GENRE);

    private final static String GET_ALL_GENRES_QUERY = String.format("SELECT * FROM %s order by %s;", TableName.GENRES,
            ColumnName.GENRES_ID_GENRE);

    private final static String GET_GENRE_BY_ID_QUERY = String.format("SELECT * FROM %s WHERE %s=?;",
            TableName.GENRES, ColumnName.GENRES_ID_GENRE);

    private final static String GET_GENRE_BY_CATEGORY_QUERY = String.format("SELECT * FROM %s WHERE %s=?;",
            TableName.GENRES, ColumnName.GENRES_GENRE);

    private final static String GET_COUNT_QUERY = String.format("select count(%s) from %s", ColumnName.GENRES_GENRE,
            TableName.GENRES);

    private final static String GET_COUNT_BOOKS_BY_GENRE_QUERY = String.format("select count(%s) from %s where %s=?",
            ColumnName.GHB_ID_BOOK, TableName.G_H_B, ColumnName.GHB_ID_GENRES);

    private static final String DELETE_GENRES_QUERY = String.format("delete from %s where %s=?",
            TableName.G_H_B, ColumnName.GHB_ID_BOOK);

    @Override
    public boolean create(Genre genre) throws DaoException {
        logger.info("Start adding genres to the table.");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, ADD_GENRE_QUERY, genre.getCategory());
            prStatement.execute();
            return true;
        } catch (SQLException sqlE) {
            logger.error("Genre of the book is not added to the table. Genre - {}", genre);
            throw new DaoException("Genre of the book is not added to the table.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public boolean update(Genre genre) throws DaoException {
        logger.info("Start to update genre by id.");
        PreparedStatement prStatement = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, UPDATE_GENRE_BY_ID_QUERY, genre.getCategory(),
                    genre.getGenreId());
            prStatement.execute();
            return true;
        } catch (SQLException sqlE) {
            logger.error("No genre update by id. Genre - {}", genre.toString());
            throw new DaoException(sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<Genre> getGenreById(long genreId) throws DaoException {
        logger.info("Receiving an author by ID.");
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<Genre> entity = new ArrayList<>();
        GenreMapper mapper = new GenreMapper();
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_GENRE_BY_ID_QUERY, genreId);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if(entity.size() == 1) {
                logger.info("Genre by ID received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                throw new DaoException("Find more 1 genre.");
            }
        } catch (SQLException sqlE) {
            logger.error("Error getting genre by ID.");
            throw new DaoException("Error getting genre by ID.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<Genre> getGenreByGenre(String category) throws DaoException {
        logger.info("Receiving an genre by category.");
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<Genre> entity = new ArrayList<>();
        GenreMapper mapper = new GenreMapper();
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_GENRE_BY_CATEGORY_QUERY, category);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if(entity.size() == 1) {
                logger.info("Genre by category received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                throw new DaoException("Find more 1 genre.");
            }
        } catch (SQLException sqlE) {
            logger.error("Error getting genre by category.");
            throw new DaoException("Error getting genre by category.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public boolean deleteGenreByBookId(long bookId)  {
        logger.info("Deleting genre data by book. BookId - {}", bookId);
        PreparedStatement prStatement = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, DELETE_GENRES_QUERY, bookId);
            prStatement.executeUpdate();
            return true;
        } catch (SQLException sqlE) {
            logger.error("Error while deleting genre data by book. BookDto id - {}", bookId);
            throw new DaoException("Error while deleting genre data by book.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<Genre> getGenres() throws DaoException {
        logger.info("Getting a list of genres.");
        List<Genre> genres = new ArrayList<>();
        PreparedStatement prStatement = null;
        GenreMapper mapper = new GenreMapper();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = connection.prepareStatement(GET_ALL_GENRES_QUERY);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                genres.add(mapper.map(resultSet));
            }
        }catch (SQLException sqlE) {
            logger.error("Genres not received.");
            throw new DaoException(sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Authors list received.");
        return genres;
    }

    @Override
    public long getCount() throws DaoException {
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
            logger.error("Number of genres not received.");
            throw new DaoException("Number of genres not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        return countAuthors;
    }

    @Override
    public long getCountBookByIdGenre(int genreId) throws DaoException {
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        int countGenres = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, GET_COUNT_BOOKS_BY_GENRE_QUERY, genreId);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                countGenres = resultSet.getInt(1);
            }
        }catch (SQLException sqlE) {
            logger.error("Number of books by genre not received.");
            throw new DaoException("Number of books by genre not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        return countGenres;
    }
}
