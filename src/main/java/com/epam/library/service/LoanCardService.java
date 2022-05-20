package com.epam.library.service;

/**
 * Interface {@link LoanCardService} is service interface which provide operations an loan card
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public interface LoanCardService {

    /**
     * The method is used to create the loan card.
     *
     * @param orderId    specified id of order.
     * @param typeUse    specified typeUse of book.
     * @return <tt>true</tt> if a loan card with specified parameter was created.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean create(String orderId, String typeUse) throws ServiceException;

    /**
     * The method is used to close the loan card.
     *
     * @param cardId    specified id of loan card.
     * @return <tt>true</tt> if a loan card with specified parameter was closed.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean closeLoanCard(String cardId) throws ServiceException;

    /**
     * The method is used to close the loan card with violation.
     *
     * @param cardId    specified id of loan card.
     * @return <tt>true</tt> if a loan card with specified parameter was closed.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean closeLoanCardWithViolation(String cardId) throws ServiceException;

    /**
     * The method is used to update the loan card.
     *
     * @param cardId    specified id of loan card.
     * @param typeUse    specified entity of book.
     * @param status    specified quantity of loan card.
     * @return <tt>true</tt> if a loan card with specified parameter was updated.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean update(String cardId, String typeUse, String status) throws ServiceException;

    /**
     * The method is used to get the number of books.
     *
     * @return Returns the number of loan cards in the system.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    long showCountCards(String status) throws ServiceException;
}
