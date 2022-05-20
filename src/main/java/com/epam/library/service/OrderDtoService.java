package com.epam.library.service;

import com.epam.library.entity.dto.OrderDto;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@link OrderDtoService} is service interface which provide operations an order
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public interface OrderDtoService {

    /**
     * The method is used to get orders by user.
     *
     * @param userId    specified id of user.
     * @return list of orders.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<OrderDto> showOrdersUser(String userId) throws ServiceException;

    /**
     * The method is used to get the order by id.
     *
     * @param orderId    specified id of order.
     * @return Return order.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    Optional<OrderDto> showOrderById(String orderId) throws ServiceException;

    /**
     * The method is used to get orders.
     *
     * @return list of orders.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<OrderDto> showAllOrders() throws ServiceException;

    /**
     * The method is used to get orders by status.
     *
     * @param status    specified status of order.
     * @return list of orders.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<OrderDto> showOrdersByStatus(String status) throws ServiceException;

    /**
     * The method is used to get orders by library and status.
     *
     * @param status    specified status of order.
     * @param city    specified city of library.
     * @return list of orders.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<OrderDto> showOrdersByCityAndStatus(String city, String status) throws ServiceException;

    /**
     * The method is used to get orders by library.
     *
     * @param city    specified city of library.
     * @return list of orders.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<OrderDto> showOrdersByCity(String city) throws ServiceException;

}
