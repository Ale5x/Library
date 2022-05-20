package com.epam.library.dao;

import com.epam.library.dao.impl.UserDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@link DaoHelper} is a helper class.
 *
 * @author Alexander Pishchala
 */

public abstract class DaoHelper {

    private final static Logger logger = LoggerFactory.getLogger(DaoHelper.class);

    /**
     * The method will return PreparedStatement.
     *
     * @param connection Connection for database.
     * @param query Query for database without parameters.
     * @param parameters Parameters for the query.
     * @return Finished PreparedStatement.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    public PreparedStatement createPreparedStatement(Connection connection, String query, Object... parameters) throws DaoException {
        try {
            logger.info("Create statement. Query - {}", query);
            PreparedStatement prStatement = connection.prepareStatement(query);
            for (int i = 1; i <= parameters.length; i++) {
                logger.info("Parameters i - {}, value - {}", i, parameters[i - 1]);
                prStatement.setObject(i, parameters[i - 1]);
            }
            return prStatement;
        }catch (SQLException sqlException) {
            logger.error("Error creating PreparedStatement.");
            throw new DaoException("Error creating PreparedStatement.", sqlException);
        }
    }

    /**
     * Method closes PreparedStatement.
     *
     * @param statement PreparedStatement after request.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    public void closePreparedStatement(PreparedStatement statement) throws DaoException {
        logger.info("Start to close statement.");
        try {
            if (!(statement == null)) {
                statement.close();
            }
        } catch (SQLException sqlE) {
            logger.error("An error occured while closing PreparedStatement.");
            throw new DaoException("An error occured while closing PreparedStatement.", sqlE);
        }
        logger.info("Finish to close statement.");
    }

    /**
     * Method closes ResultSet.
     *
     * @param resultSet ResultSet after request.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    public void closeResultSet(ResultSet resultSet) throws DaoException {
        logger.info("Start to close resultSet.");
        try {
            if (!(resultSet == null)) {
                resultSet.close();
            }
        } catch (SQLException sqlE) {
            logger.error("An error occured while closing ResultSet.");
            throw new DaoException("An error occured while closing ResultSet.", sqlE);
        }
        logger.info("Finish to close resultSet.");
    }
}
