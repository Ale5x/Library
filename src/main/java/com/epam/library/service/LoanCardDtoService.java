package com.epam.library.service;

import com.epam.library.entity.dto.LoanCardDto;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@link LoanCardDtoService} is service interface which provide operations a loan card
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public interface LoanCardDtoService {

    /**
     * The method is used to get the loan card by status.
     *
     * @param status    specified status of loan card.
     * @return list of loan cards.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<LoanCardDto> showCardsByStatus(String status) throws ServiceException;

    /**
     * The method is used to get the loan card by user id.
     *
     * @param userId    specified id of user.
     * @return list of loan cards.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<LoanCardDto> showCardsByUser(String userId) throws ServiceException;

    /**
     * The method is used to get the loan card by book id.
     *
     * @param bookId    specified id of book.
     * @return list of loan cards.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<LoanCardDto> showCardsByBook(String bookId) throws ServiceException;

    /**
     * The method is used to get the loan card by library and status.
     *
     * @param city    specified city of library.
     * @param status    specified status of loan card.
     * @return list of loan cards.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<LoanCardDto> showCardsByCityAndStatus(String city, String status) throws ServiceException;

    /**
     * The method is used to get the loan card by library.
     *
     * @param city    specified city of library.
     * @return list of loan cards.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<LoanCardDto> showCardsByCity(String city) throws ServiceException;

    /**
     * The method is used to get the loan card.
     *
     * @return list of loan cards.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<LoanCardDto> showAll() throws ServiceException;

    /**
     * The method is used to get the loan card by id.
     *
     * @param loanCardId    specified id of loan card.
     * @return Return loan card.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    Optional<LoanCardDto> showCardsById(String loanCardId) throws ServiceException;

}
