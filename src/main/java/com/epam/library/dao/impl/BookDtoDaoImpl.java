package com.epam.library.dao.impl;

import com.epam.library.dao.*;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.BookDtoMapper;
import com.epam.library.entity.Author;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class {@link BookDtoDaoImpl} is an implementation of the 'BookDtoDao' interface.
 *
 * @author Alexander Pishchala
 */

public class BookDtoDaoImpl extends DaoHelper implements BookDtoDao {

    private static final Logger logger = LoggerFactory.getLogger(BookDtoDaoImpl.class);

    private static final String ADD_AHB_QUERY = String.format("INSERT INTO %s(%s, %s) " +
                    "VALUES((SELECT %s FROM %s WHERE %s=?), ?)", TableName.A_H_B, ColumnName.AHB_ID_AUTHORS,
            ColumnName.AHB_ID_BOOK, ColumnName.AUTHOR_ID_AUTHOR, TableName.AUTHORS, ColumnName.AUTHOR_NAME);

    private static final String ADD_GHB_QUERY = String.format("INSERT INTO %s(%s, %s) " +
                    "VALUES((SELECT %s FROM %s WHERE %s=?), ?)", TableName.G_H_B, ColumnName.GHB_ID_GENRES,
            ColumnName.GHB_ID_BOOK, ColumnName.GENRES_ID_GENRE, TableName.GENRES, ColumnName.GENRES_GENRE);

    private static final String ADD_BOOK_QUERY = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s, %s) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, (SELECT %s FROM %s WHERE %s=?))", TableName.BOOK, ColumnName.BOOK_SHELF,
            ColumnName.BOOK_TITLE, ColumnName.BOOK_QUANTITY, ColumnName.BOOK_BORROW, ColumnName.BOOK_PUBLISHER,
            ColumnName.BOOK_DESCRIPTION, ColumnName.BOOK_YEAR, ColumnName.BOOK_ISBN, ColumnName.BOOK_ID_LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.LIBRARY, ColumnName.LIBRARY_CITY);

    private static final String UPDATE_BOOK_QUERY = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=?, " +
                    "%s=?, %s=?, %s=?, %s=(SELECT %s FROM %s WHERE %s=?), %s=? WHERE %s=?;", TableName.BOOK, ColumnName.BOOK_TITLE,
            ColumnName.BOOK_QUANTITY, ColumnName.BOOK_BORROW, ColumnName.BOOK_PUBLISHER, ColumnName.BOOK_DESCRIPTION,
            ColumnName.BOOK_YEAR, ColumnName.BOOK_ADDED, ColumnName.BOOK_ISBN, ColumnName.BOOK_ID_LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.LIBRARY, ColumnName.LIBRARY_CITY, ColumnName.BOOK_SHELF,
            ColumnName.BOOK_ID_BOOK);

    private static final String GET_BOOK_BY_ID_QUERY = String.format("Select *, group_concat(Distinct %s order by %s " +
                    "SEPARATOR', ') AS %s, group_concat(distinct %s order by %s SEPARATOR', ') AS %s from %s left " +
                    "join %s on(books.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join " +
                    "%s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) where %s.%s=? group by %s.%s", ColumnName.AUTHOR_NAME,
            ColumnName.AUTHOR_NAME, ColumnName.BOOK_UNION_AUTHORS, ColumnName.GENRES_GENRE, ColumnName.GENRES_GENRE,
            ColumnName.BOOK_UNION_GENRES, TableName.BOOK, TableName.A_H_B,  ColumnName.BOOK_ID_BOOK, TableName.A_H_B,
            ColumnName.AHB_ID_BOOK, TableName.AUTHORS, TableName.A_H_B, ColumnName.AHB_ID_AUTHORS, TableName.AUTHORS,
            ColumnName.AUTHOR_ID_AUTHOR, TableName.G_H_B, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.G_H_B,
            ColumnName.GHB_ID_BOOK, TableName.GENRES, TableName.G_H_B, ColumnName.GHB_ID_GENRES, TableName.GENRES,
            ColumnName.GENRES_ID_GENRE, TableName.LIBRARY, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.BOOK,
            ColumnName.BOOK_ID_BOOK);

    private static final String GET_BOOK_BY_CITY_QUERY = String.format("Select *, group_concat(Distinct %s order by %s " +
                    "SEPARATOR', ') AS %s, group_concat(distinct %s order by %s SEPARATOR', ') AS %s from %s left " +
                    "join %s on(books.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join " +
                    "%s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) where %s.%s=? group by %s.%s", ColumnName.AUTHOR_NAME,
            ColumnName.AUTHOR_NAME, ColumnName.BOOK_UNION_AUTHORS, ColumnName.GENRES_GENRE, ColumnName.GENRES_GENRE,
            ColumnName.BOOK_UNION_GENRES, TableName.BOOK, TableName.A_H_B,  ColumnName.BOOK_ID_BOOK, TableName.A_H_B,
            ColumnName.AHB_ID_BOOK, TableName.AUTHORS, TableName.A_H_B, ColumnName.AHB_ID_AUTHORS, TableName.AUTHORS,
            ColumnName.AUTHOR_ID_AUTHOR, TableName.G_H_B, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.G_H_B,
            ColumnName.GHB_ID_BOOK, TableName.GENRES, TableName.G_H_B, ColumnName.GHB_ID_GENRES, TableName.GENRES,
            ColumnName.GENRES_ID_GENRE, TableName.LIBRARY, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.LIBRARY, ColumnName.LIBRARY_CITY, TableName.BOOK,
            ColumnName.BOOK_ID_BOOK);

    private static final String GET_BOOK_BY_GENRE_QUERY = String.format("select *, group_concat(Distinct %s order " +
                    "by %s separator ',') as %s, group_concat(Distinct %s order by %s separator ',') as %s " +
                    "from %s left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) " +
                    "left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) where %s like ",
            ColumnName.AUTHOR_NAME, ColumnName.AUTHOR_NAME, ColumnName.BOOK_UNION_AUTHORS,
            ColumnName.GENRES_GENRE, ColumnName.GENRES_GENRE, ColumnName.BOOK_UNION_GENRES, TableName.BOOK,
            TableName.A_H_B, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.A_H_B, ColumnName.AHB_ID_BOOK,
            TableName.AUTHORS, TableName.A_H_B, ColumnName.AHB_ID_AUTHORS, TableName.AUTHORS,
            ColumnName.AUTHOR_ID_AUTHOR, TableName.G_H_B, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.G_H_B,
            ColumnName.GHB_ID_BOOK, TableName.GENRES, TableName.G_H_B, ColumnName.GHB_ID_GENRES, TableName.GENRES,
            ColumnName.GENRES_ID_GENRE, TableName.LIBRARY, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, ColumnName.GENRES_GENRE);

    private static final String GET_BOOK_BY_TITLE_QUERY = String.format("Select *, group_concat(Distinct %s order by %s " +
                    "SEPARATOR', ') AS %s, group_concat(distinct %s order by %s SEPARATOR', ') AS %s from %s left " +
                    "join %s on(books.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join " +
                    "%s on(%s.%s=%s.%s) left JOIN %s on(%s.%s=%s.%s) where %s.%s LIKE", ColumnName.AUTHOR_NAME,
            ColumnName.AUTHOR_NAME, ColumnName.BOOK_UNION_AUTHORS, ColumnName.GENRES_GENRE, ColumnName.GENRES_GENRE,
            ColumnName.BOOK_UNION_GENRES, TableName.BOOK, TableName.A_H_B,  ColumnName.BOOK_ID_BOOK, TableName.A_H_B,
            ColumnName.AHB_ID_BOOK, TableName.AUTHORS, TableName.A_H_B, ColumnName.AHB_ID_AUTHORS, TableName.AUTHORS,
            ColumnName.AUTHOR_ID_AUTHOR, TableName.G_H_B, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.G_H_B,
            ColumnName.GHB_ID_BOOK, TableName.GENRES, TableName.G_H_B, ColumnName.GHB_ID_GENRES, TableName.GENRES,
            ColumnName.GENRES_ID_GENRE, TableName.LIBRARY, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, ColumnName.BOOK_TITLE);

    private static final String GET_BOOK_BY_AUTHOR_QUERY = String.format("select *, group_concat(Distinct %s order " +
                    "by %s separator ',') as %s, group_concat(Distinct %s order by %s separator ',') as %s " +
                    "from %s left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) " +
                    "left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) where %s like ",
            ColumnName.AUTHOR_NAME, ColumnName.AUTHOR_NAME, ColumnName.BOOK_UNION_AUTHORS,
            ColumnName.GENRES_GENRE, ColumnName.GENRES_GENRE, ColumnName.BOOK_UNION_GENRES, TableName.BOOK,
            TableName.A_H_B, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.A_H_B, ColumnName.AHB_ID_BOOK,
            TableName.AUTHORS, TableName.A_H_B, ColumnName.AHB_ID_AUTHORS, TableName.AUTHORS,
            ColumnName.AUTHOR_ID_AUTHOR, TableName.G_H_B, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.G_H_B,
            ColumnName.GHB_ID_BOOK, TableName.GENRES, TableName.G_H_B, ColumnName.GHB_ID_GENRES, TableName.GENRES,
            ColumnName.GENRES_ID_GENRE, TableName.LIBRARY, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, ColumnName.AUTHOR_NAME);

    private static final String GET_ALL_BOOK_QUERY = String.format("Select *, group_concat(Distinct %s order by %s " +
                    "SEPARATOR', ') AS %s, group_concat(distinct %s order by %s SEPARATOR', ') AS %s from %s left " +
                    "join %s on(books.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join " +
                    "%s on(%s.%s=%s.%s) left JOIN %s on(%s.%s=%s.%s) group by %s.%s", ColumnName.AUTHOR_NAME,
            ColumnName.AUTHOR_NAME, ColumnName.BOOK_UNION_AUTHORS, ColumnName.GENRES_GENRE, ColumnName.GENRES_GENRE,
            ColumnName.BOOK_UNION_GENRES, TableName.BOOK, TableName.A_H_B,  ColumnName.BOOK_ID_BOOK, TableName.A_H_B,
            ColumnName.AHB_ID_BOOK, TableName.AUTHORS, TableName.A_H_B, ColumnName.AHB_ID_AUTHORS, TableName.AUTHORS,
            ColumnName.AUTHOR_ID_AUTHOR, TableName.G_H_B, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.G_H_B,
            ColumnName.GHB_ID_BOOK, TableName.GENRES, TableName.G_H_B, ColumnName.GHB_ID_GENRES, TableName.GENRES,
            ColumnName.GENRES_ID_GENRE, TableName.LIBRARY, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, ColumnName.BOOK_ID_BOOK);

    private static final String GET_BOOK_BY_PAGE_QUERY = String.format("Select *, group_concat(Distinct %s order by %s " +
                    "SEPARATOR', ') AS %s, group_concat(distinct %s order by %s SEPARATOR', ') AS %s from %s left " +
                    "join %s on(books.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join " +
                    "%s on(%s.%s=%s.%s) left JOIN %s on(%s.%s=%s.%s) group by %s.%s LIMIT ? offset ?", ColumnName.AUTHOR_NAME,
            ColumnName.AUTHOR_NAME, ColumnName.BOOK_UNION_AUTHORS, ColumnName.GENRES_GENRE, ColumnName.GENRES_GENRE,
            ColumnName.BOOK_UNION_GENRES, TableName.BOOK, TableName.A_H_B,  ColumnName.BOOK_ID_BOOK, TableName.A_H_B,
            ColumnName.AHB_ID_BOOK, TableName.AUTHORS, TableName.A_H_B, ColumnName.AHB_ID_AUTHORS, TableName.AUTHORS,
            ColumnName.AUTHOR_ID_AUTHOR, TableName.G_H_B, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.G_H_B,
            ColumnName.GHB_ID_BOOK, TableName.GENRES, TableName.G_H_B, ColumnName.GHB_ID_GENRES, TableName.GENRES,
            ColumnName.GENRES_ID_GENRE, TableName.LIBRARY, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, ColumnName.BOOK_ID_BOOK);

    private static final String GET_BOOK_BY_ISBN_QUERY = String.format("Select *, group_concat(Distinct %s order by %s " +
                    "SEPARATOR', ') AS %s, group_concat(distinct %s order by %s SEPARATOR', ') AS %s from %s left " +
                    "join %s on(books.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join " +
                    "%s on(%s.%s=%s.%s) left JOIN %s on(%s.%s=%s.%s) where %s.%s=? group by %s.%s", ColumnName.AUTHOR_NAME,
            ColumnName.AUTHOR_NAME, ColumnName.BOOK_UNION_AUTHORS, ColumnName.GENRES_GENRE, ColumnName.GENRES_GENRE,
            ColumnName.BOOK_UNION_GENRES, TableName.BOOK, TableName.A_H_B,  ColumnName.BOOK_ID_BOOK, TableName.A_H_B,
            ColumnName.AHB_ID_BOOK, TableName.AUTHORS, TableName.A_H_B, ColumnName.AHB_ID_AUTHORS, TableName.AUTHORS,
            ColumnName.AUTHOR_ID_AUTHOR, TableName.G_H_B, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.G_H_B,
            ColumnName.GHB_ID_BOOK, TableName.GENRES, TableName.G_H_B, ColumnName.GHB_ID_GENRES, TableName.GENRES,
            ColumnName.GENRES_ID_GENRE, TableName.LIBRARY, TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, ColumnName.BOOK_ISBN, TableName.BOOK,
            ColumnName.BOOK_ID_BOOK);

    private static final String DELETE_BOOK_BY_ID_QUERY = String.format("DELETE FROM %s where %s=?;", TableName.BOOK,
            ColumnName.BOOK_ID_BOOK);

    private final static String GROUP_BY_ELEMENT = "group by ";

    @Override
    public boolean create(BookDto bookDto) throws DaoException {
        logger.info("Adding a bookDto.");
        AuthorDao authorDAO = new AuthorDaoImpl();
        GenreDao genreDAO = new GenreDaoImpl();
        Connection connection = null;
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);

            prStatement = connection.prepareStatement(ADD_BOOK_QUERY, Statement.RETURN_GENERATED_KEYS);

            prStatement.setString(1, bookDto.getShelf());
            prStatement.setString(2, bookDto.getTitle());
            prStatement.setInt(3, bookDto.getQuantity());
            prStatement.setInt(4, bookDto.getBorrow());
            prStatement.setString(5, bookDto.getPublisher());
            prStatement.setString(6, bookDto.getDescription());
            prStatement.setString(7, bookDto.getYear());
            prStatement.setString(8, bookDto.getIsbn());
            prStatement.setString(9, bookDto.getCityLibrary());

            int bookId = 0;
            int rowExecute = prStatement.executeUpdate();
            if (rowExecute == 0) {
                throw new DaoException("Creating bookDto failed, no rows affected.");
            }
            resultSet = prStatement.getGeneratedKeys();
            if (resultSet.next()) {
                bookId = resultSet.getInt(1);
            }
            if (bookDto.getAuthors() != null) {
                if (!bookDto.getAuthors().isEmpty()) {
                    for (Author author : bookDto.getAuthors()) {
                        prStatement = createPreparedStatement(connection, ADD_AHB_QUERY, author.getName(), bookId);
                        prStatement.execute();
                    }
                }
            }

            if(bookDto.getGenres() != null) {
                if (!bookDto.getGenres().isEmpty()) {
                    for (Genre genre : bookDto.getGenres()) {
                        prStatement = createPreparedStatement(connection, ADD_GHB_QUERY, genre.getCategory(), bookId);
                        prStatement.execute();
                    }
                }
            }

            connection.commit();
            return true;
        }catch (SQLException sqlE) {
            logger.error("Error while adding bookDto to table. BookDto - {}", bookDto.toString());
            sqlE.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DaoException("Rollback is not closed", e);
            }
            throw new DaoException("Error while adding bookDto to table.", sqlE);
        } finally {
            try {
                connection.setAutoCommit(true);
            }catch (SQLException sql) {
                logger.error("An error occurred while closing the transaction.");
            }
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
            try {
                connection.close();
            } catch (SQLException sqlException) {
                throw new DaoException("The connection is not closed.", sqlException);
            }
        }
    }

    @Override
    public boolean update(BookDto bookDto) throws DaoException {
        logger.info("Updating a book by ID.");
        AuthorDao authorDAO = new AuthorDaoImpl();
        GenreDao genreDAO = new GenreDaoImpl();
        Connection connection = null;
        PreparedStatement prStatement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);

            if (bookDto.getAuthors() != null) {
                if (!bookDto.getAuthors().isEmpty()) {
                    authorDAO.deleteAuthorByBookId(bookDto.getBookDtoId());
                    for (Author author : bookDto.getAuthors()) {
                        authorDAO.create(author);
                        prStatement = createPreparedStatement(connection, ADD_AHB_QUERY, author.getName(), bookDto.getBookDtoId());
                        prStatement.execute();
                    }
                }
            }
            if (bookDto.getGenres() != null) {
                if (!bookDto.getGenres().isEmpty()) {
                    genreDAO.deleteGenreByBookId(bookDto.getBookDtoId());
                    for (Genre genre : bookDto.getGenres()) {
                        genreDAO.create(genre);
                        prStatement = createPreparedStatement(connection, ADD_GHB_QUERY,
                                genre.getCategory(), bookDto.getBookDtoId());
                        prStatement.execute();
                    }
                }
            }

            prStatement = createPreparedStatement(connection, UPDATE_BOOK_QUERY, bookDto.getTitle(), bookDto.getQuantity(),
                    bookDto.getBorrow(), bookDto.getPublisher(), bookDto.getDescription(), bookDto.getYear(), bookDto.getAdded(),
                    bookDto.getIsbn(), bookDto.getCityLibrary(), bookDto.getShelf(), bookDto.getBookDtoId());

            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException sqlE) {
            logger.error("Error while bookDto's updating.");
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DaoException("Rollback is not closed", e);
            }
            throw new DaoException("Error while bookDto's updating.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<BookDto> getBookByIsbn(String isbn) throws DaoException {
        logger.info("Receiving a book by ISBN.");
        BookDtoMapper mapper = new BookDtoMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<BookDto> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, GET_BOOK_BY_ISBN_QUERY, isbn);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                books.add(mapper.map(resultSet));
            }
            return books;
        } catch (SQLException sqlE) {
            logger.error("Error while receiving a book by ISBN.");
            throw new DaoException("Error while receiving a book by ISBN.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<BookDto> getBookById(long bookId) throws DaoException {
        logger.info("Receiving a book by ID.");
        BookDtoMapper mapper = new BookDtoMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<BookDto> entity = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, GET_BOOK_BY_ID_QUERY, bookId);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }

            if(entity.size() == 1) {
                logger.info("BookDto by ID received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                throw new DaoException("Find more 1 BookDto.");
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
    public List<BookDto> getBooks() throws DaoException {
        logger.info("Getting a list of bookDtos.");
        List<BookDto> bookDtos = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = connection.prepareStatement(GET_ALL_BOOK_QUERY);
            resultSet = prStatement.executeQuery();

            BookDtoMapper mapper = new BookDtoMapper();
            while (resultSet.next()) {
                bookDtos.add(mapper.map(resultSet));
            }
        }catch (SQLException sqlE) {
            logger.error("Error when receiving bookDtos.");
            throw new DaoException("Error when receiving bookDtos.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Books received.");
        return bookDtos;
    }

    @Override
    public List<BookDto> getBooksByCity(String city) throws DaoException {
        logger.info("Receiving a book by city.");
        BookDtoMapper mapper = new BookDtoMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<BookDto> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, GET_BOOK_BY_CITY_QUERY, city);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                books.add(mapper.map(resultSet));
            }
            return books;
        } catch (SQLException sqlE) {
            logger.error("Books by city not received.");
            throw new DaoException("Books by city not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<BookDto> getBooksByTitle(String title) throws DaoException {
        logger.info("Receiving a book by title.");
        StringBuilder builder = new StringBuilder();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<BookDto> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            builder.append(GET_BOOK_BY_TITLE_QUERY).append(ColumnName.CHAR_HATCH).append(ColumnName.CHAR_PERCENT)
                    .append(title).append(ColumnName.CHAR_PERCENT).append(ColumnName.CHAR_HATCH).append(GROUP_BY_ELEMENT)
                    .append(ColumnName.BOOK_ID_BOOK);
            prStatement = connection.prepareStatement(builder.toString());
            resultSet = prStatement.executeQuery();
            BookDtoMapper mapper = new BookDtoMapper();
            while (resultSet.next()) {
                books.add(mapper.map(resultSet));
            }
            return books;
        } catch (SQLException sqlE) {
            logger.error("Books by title not received.");
            throw new DaoException("Books by title not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<BookDto> getBooksByPage(int limit, int page) throws DaoException {
        logger.info("Receiving a book by page.");
        BookDtoMapper mapper = new BookDtoMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<BookDto> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, GET_BOOK_BY_PAGE_QUERY, limit, page);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                books.add(mapper.map(resultSet));
            }
            return books;
        } catch (SQLException sqlE) {
            logger.error("Books by page not received.");
            throw new DaoException("Books by page not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<BookDto> getBooksByAuthor(String author) throws DaoException {
        logger.info("Receiving a book by author.");
        BookDtoMapper mapper = new BookDtoMapper();
        StringBuilder builderQuery = new StringBuilder();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<BookDto> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            builderQuery.append(GET_BOOK_BY_AUTHOR_QUERY).append(ColumnName.CHAR_HATCH).append(ColumnName.CHAR_PERCENT)
                    .append(author).append(ColumnName.CHAR_PERCENT).append(ColumnName.CHAR_HATCH).append(GROUP_BY_ELEMENT)
                    .append(ColumnName.BOOK_ID_BOOK);
            prStatement = createPreparedStatement(connection, builderQuery.toString());
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                books.add(mapper.map(resultSet));
            }
            return books;
        } catch (SQLException sqlE) {
            logger.error("Books by author not received.");
            throw new DaoException("Books by author not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<BookDto> getBooksByGenre(String genre) throws DaoException {
        logger.info("Receiving a book by genre.");
        BookDtoMapper mapper = new BookDtoMapper();
        StringBuilder builderQuery = new StringBuilder();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<BookDto> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            builderQuery.append(GET_BOOK_BY_GENRE_QUERY).append(ColumnName.CHAR_HATCH).append(ColumnName.CHAR_PERCENT)
                    .append(genre).append(ColumnName.CHAR_PERCENT).append(ColumnName.CHAR_HATCH).append(GROUP_BY_ELEMENT)
                    .append(ColumnName.BOOK_ID_BOOK);
            prStatement = createPreparedStatement(connection, builderQuery.toString());
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                books.add(mapper.map(resultSet));
            }
            return books;
        } catch (SQLException sqlE) {
            logger.error("Books by genre not received.");
            throw new DaoException("Books by genre not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }
}
