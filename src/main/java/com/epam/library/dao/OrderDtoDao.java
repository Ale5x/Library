package com.epam.library.dao;

import com.epam.library.entity.dto.OrderDto;
import com.epam.library.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@link OrderDtoDao} provides operation with data of database tables 'orders', 'libraries', 'users', 'books'.
 *
 * @author Alexander Pishchala
 */

public interface OrderDtoDao {

    /**
     * The method will return list Order Dto by id.
     *
     * @param id Order id.
     * @return specified Optional Order by id.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<OrderDto> getOrderById(long id) throws DaoException;

    /**
     * The method will return list Order Dto by library.
     *
     * @param city Library city.
     * @return list of Order Dto.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<OrderDto> getOrderByCity(String city) throws DaoException;

    /**
     * The method will return list Order Dto by library and status.
     *
     * @param city Library city.
     * @param status Order status.
     * @return list of Order Dto.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<OrderDto> getOrderByCityAndStatus(String city, OrderStatus status) throws DaoException;

    /**
     * The method will return list Order Dto by status.
     *
     * @param status Order status.
     * @return list of Order Dto.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<OrderDto> getOrderByStatus(OrderStatus status) throws DaoException;

    /**
     * The method will return list Order Dto by id.
     *
     * @param id Order id.
     * @return list of Order Dto.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<OrderDto> getOrderByUser(long id) throws DaoException;

    /**
     * The method will return list Order Dto.
     *
     * @return list of Order Dto.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<OrderDto> getOrders() throws DaoException;

}
