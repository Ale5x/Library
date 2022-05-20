package com.epam.library.service;

import com.epam.library.entity.Order;

import java.util.Optional;

/**
 * Interface {@link OrderService} is service interface which provide operations an order
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public interface OrderService {

    /**
     * The method is used to create the order.
     *
     * @param bookId    specified id of book.
     * @param userId    specified id of user.
     * @param city    specified city of library.
     * @param comment    specified comment of order.
     * @return <tt>true</tt> if an order with specified parameter was created.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean create(String bookId, String userId, String city, String comment) throws ServiceException;

    /**
     * The method is used to update status the order.
     *
     * @param orderId    specified id of order.
     * @param adminId    specified id of user.
     * @param status    specified status of order.
     * @return <tt>true</tt> if an order with specified parameter was updated.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean updateStatus(String orderId, String status, String adminId) throws ServiceException;

    /**
     * The method is used to update the order.
     *
     * @param orderId    specified id of order.
     * @param comment    specified id of order.
     * @param city    specified status of library.
     * @return <tt>true</tt> if an order with specified parameter was updated.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean update(String orderId, String comment, String city) throws ServiceException;

    /**
     * The method is used to remove the order.
     *
     * @param orderId    specified id of order.
     * @return <tt>true</tt> if an order with specified parameter was removed.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean delete(String orderId) throws ServiceException;

    /**
     * The method is used to get the number of orders.
     *
     * @return Returns the number of orders in the system.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    long showCountByStatus(String status) throws ServiceException;

    /**
     * The method is used to get the order.
     *
     * @param orderId    specified id of order.
     * @return Return order.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    Optional<Order> showById(String orderId) throws ServiceException;
}
