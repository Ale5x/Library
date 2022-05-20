package com.epam.library.dao.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.LoanCardDtoDao;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.LoanCardDtoMapper;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.entity.LoanCardStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class {@link LoanCardDtoDaoImpl} is an implementation of the 'LoanCardDtoDao' interface.
 *
 * @author Alexander Pishchala
 */

public class LoanCardDtoDaoImpl extends DaoHelper implements LoanCardDtoDao {

    private static final Logger logger = LoggerFactory.getLogger(LoanCardDtoDaoImpl.class);

    private final static String GET_ALL_CARDS_QUERY = String.format("SELECT * from %s left join %s on(%s.%s=%s.%s) " +
                    "left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) " +
                    "group by %s.%s", TableName.LOAN_CARDS, TableName.USER, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_USER, TableName.USER, ColumnName.USER_ID_USERS, TableName.LIBRARY,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_LIBRARY, TableName.LIBRARY, ColumnName.LIBRARY_ID_LIBRARY,
            TableName.BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_BOOK, TableName.BOOK,
            ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS_STATUS, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_STATUS,
            TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_ID_STATUS, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_CARD);

    private final static String GET_CARDS_BY_ID_BOOK_QUERY = String.format("SELECT * from %s left join %s " +
                    "on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s " +
                    "on(%s.%s=%s.%s) where %s.%s=? group by %s.%s", TableName.LOAN_CARDS, TableName.USER,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_USER, TableName.USER, ColumnName.USER_ID_USERS,
            TableName.LIBRARY, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_LIBRARY, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_BOOK,
            TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS_STATUS, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_STATUS, TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_ID_STATUS,
            TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD);


    private final static String GET_CARDS_BY_CITY_QUERY = String.format("SELECT * from %s left join %s " +
                    "on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s " +
                    "on(%s.%s=%s.%s) where %s.%s=? group by %s.%s", TableName.LOAN_CARDS, TableName.USER,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_USER, TableName.USER, ColumnName.USER_ID_USERS,
            TableName.LIBRARY, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_LIBRARY, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_BOOK,
            TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS_STATUS, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_STATUS, TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_ID_STATUS,
            TableName.LIBRARY, ColumnName.LIBRARY_CITY, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD);

    private final static String GET_CARDS_BY_CITY_AND_STATUS_QUERY = String.format("SELECT * from %s left join %s " +
                    "on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s " +
                    "on(%s.%s=%s.%s) where %s.%s=? and %s.%s=? group by %s.%s", TableName.LOAN_CARDS, TableName.USER,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_USER, TableName.USER, ColumnName.USER_ID_USERS,
            TableName.LIBRARY, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_LIBRARY, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_BOOK,
            TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS_STATUS, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_STATUS, TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_ID_STATUS,
            TableName.LIBRARY, ColumnName.LIBRARY_CITY, TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_STATUS,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD);

    private final static String GET_CARDS_BY_ID_USER_QUERY = String.format("SELECT * from %s left join %s " +
                    "on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s " +
                    "on(%s.%s=%s.%s) where %s.%s=? group by %s.%s", TableName.LOAN_CARDS, TableName.USER,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_USER, TableName.USER, ColumnName.USER_ID_USERS,
            TableName.LIBRARY, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_LIBRARY, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_BOOK,
            TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS_STATUS, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_STATUS, TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_ID_STATUS,
            TableName.USER, ColumnName.USER_ID_USERS, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD);

    private final static String GET_CARDS_BY_STATUS_CARD_QUERY = String.format("SELECT * from %s left join %s " +
                    "on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s " +
                    "on(%s.%s=%s.%s) where %s.%s=? group by %s.%s", TableName.LOAN_CARDS, TableName.USER,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_USER, TableName.USER, ColumnName.USER_ID_USERS,
            TableName.LIBRARY, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_LIBRARY, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_BOOK,
            TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS_STATUS, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_STATUS, TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_ID_STATUS,
            TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_STATUS, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD);

    private final static String GET_CARDS_BY_ID_CARD_QUERY = String.format("SELECT * from %s left join %s " +
                    "on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s on(%s.%s=%s.%s) left join %s " +
                    "on(%s.%s=%s.%s) where %s.%s=? group by %s.%s", TableName.LOAN_CARDS, TableName.USER,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_USER, TableName.USER, ColumnName.USER_ID_USERS,
            TableName.LIBRARY, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_LIBRARY, TableName.LIBRARY,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.BOOK, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_BOOK,
            TableName.BOOK, ColumnName.BOOK_ID_BOOK, TableName.LOAN_CARDS_STATUS, TableName.LOAN_CARDS,
            ColumnName.LOAN_CARD_ID_STATUS, TableName.LOAN_CARDS_STATUS, ColumnName.CARD_STATUS_ID_STATUS,
            TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD, TableName.LOAN_CARDS, ColumnName.LOAN_CARD_ID_CARD);

    @Override
    public List<LoanCardDto> getCardsByIdUser(long id) throws DaoException {
        logger.info("Getting a list of user's cards by id user - {}", id);
        List<LoanCardDto> cards = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_CARDS_BY_ID_USER_QUERY, id);
            resultSet = prStatement.executeQuery();

            LoanCardDtoMapper mapper = new LoanCardDtoMapper();

            while (resultSet.next()) {
                cards.add(mapper.map(resultSet));
            }
        } catch (SQLException sqlE) {
            logger.error("Loan cards by id user not received.");
            throw new DaoException("Loan cards by id user not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Loan cards by id user list received.");
        return cards;
    }

    @Override
    public List<LoanCardDto> getCardsByStatusCard(LoanCardStatus status) throws DaoException {
        logger.info("Getting a list of status cards by status - {}", status);
        List<LoanCardDto> cards = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_CARDS_BY_STATUS_CARD_QUERY, status.name());
            resultSet = prStatement.executeQuery();
            LoanCardDtoMapper mapper = new LoanCardDtoMapper();
            while (resultSet.next()) {
                cards.add(mapper.map(resultSet));
            }
        } catch (SQLException sqlE) {
            logger.error("Loan cards by status card not received.");
            throw new DaoException("Loan cards by status card not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Loan cards by status card received.");
        return cards;
    }

    @Override
    public List<LoanCardDto> getCardsByIdBook(long id) throws DaoException {
        logger.info("Getting a list of user's cards by id book - {}", id);
        List<LoanCardDto> cards = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_CARDS_BY_ID_BOOK_QUERY, id);
            resultSet = prStatement.executeQuery();

            LoanCardDtoMapper mapper = new LoanCardDtoMapper();

            while (resultSet.next()) {
                cards.add(mapper.map(resultSet));
            }
        } catch (SQLException sqlE) {
            logger.error("Loan cards by id book not received.");
            throw new DaoException("Loan cards by id book not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Loan cards list by book received.");
        return cards;
    }

    @Override
    public List<LoanCardDto> getCardsByCity(String city) throws DaoException {
        logger.info("Getting a list of user's cards by city - {}", city);
        List<LoanCardDto> cards = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_CARDS_BY_CITY_QUERY, city);
            resultSet = prStatement.executeQuery();

            LoanCardDtoMapper mapper = new LoanCardDtoMapper();

            while (resultSet.next()) {
                cards.add(mapper.map(resultSet));
            }
        } catch (SQLException sqlE) {
            logger.error("Loan cards by city not received.");
            throw new DaoException("Loan cards by city not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Loan cards list by city received.");
        return cards;
    }

    @Override
    public List<LoanCardDto> getCardsByCityAndStatus(String city, LoanCardStatus status) throws DaoException {
        logger.info("Getting a list of user's cards by city and status .");
        List<LoanCardDto> cards = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_CARDS_BY_CITY_AND_STATUS_QUERY, city, status.name());
            resultSet = prStatement.executeQuery();

            LoanCardDtoMapper mapper = new LoanCardDtoMapper();

            while (resultSet.next()) {
                cards.add(mapper.map(resultSet));
            }
            return cards;
        } catch (SQLException sqlE) {
            logger.error("Loan cards by city and status not received.");
            throw new DaoException("Loan cards by city and status not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<LoanCardDto> getCardsById(long id) throws DaoException {
        logger.info("Getting loan card by id.");
        LoanCardDtoMapper mapper = new LoanCardDtoMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<LoanCardDto> entity = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_CARDS_BY_ID_CARD_QUERY, id);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }

            if(entity.size() == 1) {
                logger.info("Loan card received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                logger.info("Find more 1 loan card.");
                throw new DaoException("Find more 1 loan card.");
            }
        } catch (SQLException sqlE) {
            logger.error("Loan card not received.");
            throw new DaoException("Loan card not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public List<LoanCardDto> getAll() throws DaoException {
        logger.info("Getting a list of cards");
        List<LoanCardDto> cards = new ArrayList<>();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_ALL_CARDS_QUERY);
            resultSet = prStatement.executeQuery();

            LoanCardDtoMapper mapper = new LoanCardDtoMapper();

            while (resultSet.next()) {
                cards.add(mapper.map(resultSet));
            }
        } catch (SQLException sqlE) {
            logger.error("Personal cards not received.");
            throw new DaoException("Loan cards not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
        logger.info("Loan cards list received.");
        return cards;
    }
}
