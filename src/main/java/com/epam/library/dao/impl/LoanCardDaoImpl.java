package com.epam.library.dao.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.LoanCardDao;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.LoanCardMapper;
import com.epam.library.entity.LoanCard;
import com.epam.library.entity.LoanCardStatus;
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
 * Class {@link LoanCardDaoImpl} is an implementation of the 'LoanCardDao' interface.
 *
 * @author Alexander Pishchala
 */

public class LoanCardDaoImpl extends DaoHelper implements LoanCardDao {

    private static final Logger logger = LoggerFactory.getLogger(LoanCardDaoImpl.class);

    private final static String ADD_LOAN_CARD_QUERY = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s)" +
                    " values(?, ?, ?, ?, ?, (SELECT %s FROM %s WHERE %s=?), (SELECT %s FROM %s WHERE %s=?));",
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_USER, ColumnName.LOAN_CARD_TAKING_BOOK, ColumnName.LOAN_DEADLINE,
            ColumnName.LOAN_CARD_TYPE_USE, ColumnName.LOAN_CARD_ID_BOOK, ColumnName.LOAN_CARD_ID_STATUS,
            ColumnName.LOAN_CARD_ID_LIBRARY, ColumnName.CARD_STATUS_ID_STATUS, TableName.LOAN_CARDS_STATUS,
            ColumnName.CARD_STATUS_STATUS, ColumnName.LIBRARY_ID_LIBRARY, TableName.LIBRARY, ColumnName.LIBRARY_CITY);

    private final static String UPDATE_LOAN_CARD_QUERY = String.format("Update %s SET  %s=?, %s=(SELECT %s from %s " +
                    "where %s=?), %s=?, %s=?, %s=?, %s=?, %s=(SELECT %s from %s where %s=?), %s=? where %s=?",
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_USER, ColumnName.LOAN_CARD_ID_STATUS,
            ColumnName.CARD_STATUS_ID_STATUS, TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_STATUS,
            ColumnName.LOAN_CARD_TAKING_BOOK, ColumnName.LOAN_DEADLINE, ColumnName.LOAN_CARD_TYPE_USE,
            ColumnName.LOAN_CARD_ID_BOOK, ColumnName.LOAN_CARD_ID_LIBRARY, ColumnName.LIBRARY_ID_LIBRARY,
            TableName.LIBRARY, ColumnName.LIBRARY_CITY, ColumnName.LOAN_CARD_RETURN_BOOK, ColumnName.LOAN_CARD_ID_CARD);

    private final static String GET_COUNT_BY_STATUS_QUERY = String.format("select count(%s) from %s where %s.%s=" +
                    "(SELECT %s from %s where %s=?)", ColumnName.LOAN_CARD_ID_USER, TableName.LOAN_CARDS,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_STATUS, ColumnName.LOAN_CARD_ID_STATUS,
            TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_STATUS);

    private final static String GET_CARDS_BY_ID_CARD_QUERY = String.format("SELECT * from %s left join %s " +
                    "on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) where %s.%s=?", TableName.LOAN_CARDS,
            TableName.LOAN_CARDS_STATUS, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_STATUS,
            TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_ID_STATUS, TableName.LIBRARY, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_LIBRARY, TableName.LIBRARY, ColumnName.LIBRARY_ID_LIBRARY,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD);


    @Override
    public boolean create(LoanCard card) throws DaoException {
        logger.info("Loan card creating.");
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, ADD_LOAN_CARD_QUERY, card.getUserId(),
                    card.getTakingBook(), card.getDeadline(), card.getTypeUse().name(), card.getBookId(),
                    card.getStatus().name(), card.getCityLibrary());
            prStatement.execute();
            return true;
        } catch (SQLException sqlE) {
            logger.error("An error occured while creating the loan card. Loan card - {}.", card.toString());
            throw new DaoException("Failed to create Loan card.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
            closeResultSet(resultSet);
        }
    }

    @Override
    public Optional<LoanCard> getCardById(long cardId) throws DaoException {
        logger.info("Receiving a loan card by ID.");
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<LoanCard> entity = new ArrayList<>();
        LoanCardMapper mapper = new LoanCardMapper();
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_CARDS_BY_ID_CARD_QUERY, cardId);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if(entity.size() == 1) {
                logger.info("Loan card by ID received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                throw new DaoException("Find more 1 loan card.");
            }
        } catch (SQLException sqlE) {
            logger.error("Error while retrieving loan card from database.");
            throw new DaoException("Error while retrieving loan card from database.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public boolean update(LoanCard card) throws DaoException {
        logger.info("Update loan card.");
        PreparedStatement prStatement = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, UPDATE_LOAN_CARD_QUERY, card.getUserId(),
                    card.getStatus().name(), card.getTakingBook(), card.getDeadline(), card.getTypeUse().name(),
                    card.getBookId(), card.getCityLibrary(), card.getReturnBook(), card.getLoanCardId());
            prStatement.executeUpdate();
            return true;
        } catch (SQLException sqlE) {
            logger.error("Failed to update Loan card. Personal card {}", card.toString());
            throw new DaoException("Failed to update Loan card.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public long getCountCardsByStatus(LoanCardStatus status) throws DaoException {
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        long count = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, GET_COUNT_BY_STATUS_QUERY, status.name());
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        }catch (SQLException sqlE) {
            logger.error("Number of loan cards by status not received.");
            throw new DaoException("Number of loan cards by status not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        return count;
    }
}
