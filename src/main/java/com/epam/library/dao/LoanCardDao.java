package com.epam.library.dao;

import com.epam.library.entity.LoanCard;
import com.epam.library.entity.LoanCardStatus;

import java.util.Optional;

/**
 * Interface {@link LoanCardDao} provides operation with data of database table 'loan_cards'.
 *
 * @author Alexander Pishchala
 */

public interface LoanCardDao {

    /**
     * The method creates new record in database table.
     *
     * @param card entity that specifies creation of new records in database table.
     * @return <tt>true</tt> if new row in database table was created successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean create(LoanCard card) throws DaoException;

    /**
     * The method returns specified LoanCard by id.
     *
     * @param cardId LoanCard by id.
     * @return specified Optional LoanCard by id.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<LoanCard> getCardById(long cardId) throws DaoException;

    /**
     * The method updates specified record in database table.
     *
     * @param card that will be updated in database table.
     * @return <tt>true</tt> if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean update(LoanCard card) throws DaoException;

    /**
     * The method will return list LoanCards by status.
     *
     * @param status LoanCard status.
     * @return LoanCard count.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    long getCountCardsByStatus(LoanCardStatus status) throws DaoException;
}
