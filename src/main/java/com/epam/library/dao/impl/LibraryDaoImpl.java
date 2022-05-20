package com.epam.library.dao.impl;

import com.epam.library.dao.LibraryDao;
import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.LibraryMapper;
import com.epam.library.entity.*;
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
 * Class {@link LibraryDaoImpl} is an implementation of the 'LibraryDao' interface.
 *
 * @author Alexander Pishchala
 */

public class LibraryDaoImpl extends DaoHelper implements LibraryDao {

    private static final Logger logger = LoggerFactory.getLogger(LibraryDaoImpl.class);

    private final static String ADD_LIBRARY_QUERY = String.format("INSERT INTO %s(%s, %s, %s) values(?, ?, " +
                    "(Select %s from %s where %s=?));", TableName.LIBRARY, ColumnName.LIBRARY_CITY,
            ColumnName.LIBRARY_STREET, ColumnName.LIBRARY_ID_STATUS, ColumnName.LIBRARY_STATUS_ID_STATUS,
            TableName.LIBRARY_STATUS, ColumnName.LIBRARY_STATUS_STATUS);

    private final static String UPDATE_QUERY = String.format("UPDATE %s SET %s=?, %s=?, %s=(Select %s from %s " +
                    "WHERE %s=?) WHERE %s=?;", TableName.LIBRARY, ColumnName.LIBRARY_CITY, ColumnName.LIBRARY_STREET,
            ColumnName.LIBRARY_ID_STATUS, ColumnName.LIBRARY_STATUS_ID_STATUS, TableName.LIBRARY_STATUS,
            ColumnName.LIBRARY_STATUS_STATUS, ColumnName.LIBRARY_ID_LIBRARY);

    private final static String GET_LIBRARY_BY_ID_QUERY = String.format("SELECT * FROM %s LEFT JOIN %s on(%s.%s=%s.%s) " +
                    "WHERE %s=?;", TableName.LIBRARY, TableName.LIBRARY_STATUS, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_STATUS, TableName.LIBRARY_STATUS, ColumnName.LIBRARY_STATUS_ID_STATUS,
            ColumnName.LIBRARY_ID_LIBRARY);

    private final static String GET_LIBRARY_BY_NAME_QUERY = String.format("SELECT * FROM %s LEFT JOIN %s on(%s.%s=%s.%s) " +
                    "WHERE %s=?;", TableName.LIBRARY, TableName.LIBRARY_STATUS, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_STATUS, TableName.LIBRARY_STATUS, ColumnName.LIBRARY_STATUS_ID_STATUS,
            ColumnName.LIBRARY_CITY);

    private final static String GET_LIBRARY_BY_STATUS_QUERY = String.format("SELECT * FROM %s LEFT JOIN %s on(%s.%s=%s.%s) " +
                    "WHERE %s.%s=?;", TableName.LIBRARY, TableName.LIBRARY_STATUS, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_STATUS, TableName.LIBRARY_STATUS, ColumnName.LIBRARY_STATUS_ID_STATUS,
            TableName.LIBRARY_STATUS, ColumnName.LIBRARY_STATUS_STATUS);

    private final static String GET_ALL_CITY_QUERY = String.format("SELECT * FROM %s LEFT JOIN %s on(%s.%s=%s.%s)",
            TableName.LIBRARY, TableName.LIBRARY_STATUS, TableName.LIBRARY, ColumnName.LIBRARY_ID_STATUS,
            TableName.LIBRARY_STATUS, ColumnName.LIBRARY_STATUS_ID_STATUS);

    @Override
    public boolean create(Library library) throws DaoException {
        logger.info("Create library {}", library.getCity());
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, ADD_LIBRARY_QUERY, library.getCity(),
                    library.getStreet(), library.getStatus().name());
            prStatement.execute();

            return true;

        } catch (SQLException sqlE) {
            logger.error("Failed to create library. Library - {}.", library.toString());
            throw new DaoException("Failed to create library.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public int update(Library library) throws DaoException {
        logger.info("Start of updating library data.");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, UPDATE_QUERY, library.getCity(), library.getStreet(),
                    library.getStatus().name(), library.getLibraryId());
            return prStatement.executeUpdate();
        } catch (SQLException sqlE) {
            logger.error("Failed to update library. Library - {}", library.toString());
            throw new DaoException("Failed to update library.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<Library> getLibraryById(long id) throws DaoException {
        logger.info("Receiving a library by id.");
        LibraryMapper mapper = new LibraryMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<Library> entity = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_LIBRARY_BY_ID_QUERY, id);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if (entity.size() == 1) {
                logger.info("Library by id received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                throw new DaoException("Find more 1 library.");
            }
        } catch (SQLException sqlE) {
            logger.error("Error getting library by ID.");
            throw new DaoException("Error getting library by ID.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<Library> getLibraryByCity(String city) throws DaoException {
        logger.info("Receiving a library by city.");
        LibraryMapper mapper = new LibraryMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<Library> entity = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_LIBRARY_BY_NAME_QUERY, city);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if (entity.size() == 1) {
                logger.info("Library by city received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                throw new DaoException("Find more 1 library.");
            }
        } catch (SQLException sqlE) {
            logger.error("Library in the city is not received.");
            throw new DaoException("Library in the city is not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<Library> getLibraries() throws DaoException {
        logger.info("Getting all Libraries.");
        List<Library> libraries = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = connection.prepareStatement(GET_ALL_CITY_QUERY);
            resultSet = prStatement.executeQuery();
            LibraryMapper mapper = new LibraryMapper();
            while (resultSet.next()) {
                libraries.add(mapper.map(resultSet));
            }
        } catch (SQLException sqlE) {
            logger.error("Libraries not received.");
            throw new DaoException("Libraries not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Library list received.");
        return libraries;
    }

    @Override
    public List<Library> getLibrariesByStatus(LibraryStatus status) throws DaoException {
        logger.info("Getting all Libraries by status.");
        List<Library> libraries = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_LIBRARY_BY_STATUS_QUERY, status.name());
            resultSet = prStatement.executeQuery();
            LibraryMapper mapper = new LibraryMapper();
            while (resultSet.next()) {
                libraries.add(mapper.map(resultSet));
            }
        } catch (SQLException sqlE) {
            logger.error("Libraries by status not received.");
            throw new DaoException("Libraries by status not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Library list received.");
        return libraries;
    }
}
