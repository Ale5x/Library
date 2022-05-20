package com.epam.library.dao;

import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.entity.LoanCardStatus;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@link LoanCardDtoDao} provides operation with data of database tables 'loan_cards', 'books', 'libraries', 'users'.
 *
 * @author Alexander Pishchala
 */

public interface LoanCardDtoDao {

    /**
     * The method will return list LoanCard Dto by id.
     *
     * @param id LoanCard id.
     * @return list of LoanCards Dto.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<LoanCardDto> getCardsByIdUser(long id) throws DaoException;

    /**
     * The method will return list LoanCard Dto by status.
     *
     * @param status LoanCard status.
     * @return list of LoanCards Dto.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<LoanCardDto> getCardsByStatusCard(LoanCardStatus status) throws DaoException;

    /**
     * The method will return list LoanCard Dto by book id.
     *
     * @param id book id.
     * @return list of LoanCards Dto.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<LoanCardDto> getCardsByIdBook(long id) throws DaoException;

    /**
     * The method returns specified LoanCard Dto by id.
     *
     * @param id LoanCard Dto by id.
     * @return specified Optional LoanCard Dto by id.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<LoanCardDto> getCardsById(long id) throws DaoException;

    /**
     * The method will return list LoanCard Dto by city of library.
     *
     * @param city Library city.
     * @return list of LoanCards Dto.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<LoanCardDto> getCardsByCity(String city) throws DaoException;

    /**
     * The method will return list LoanCard Dto by status and city.
     *
     * @param status LoanCard status.
     * @param city Library city.
     * @return list of LoanCards Dto.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<LoanCardDto> getCardsByCityAndStatus(String city, LoanCardStatus status) throws DaoException;

    /**
     * The method will return list LoanCard Dto.
     *
     * @return list of LoanCards Dto.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<LoanCardDto> getAll() throws DaoException;
}
