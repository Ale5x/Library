package com.epam.library.dao;

import com.epam.library.entity.Order;
import com.epam.library.entity.OrderStatus;

import java.util.Optional;

/**
 * Interface {@link OrderDao} provides operation with data of database table 'orders'.
 *
 * @author Alexander Pishchala
 */

public interface OrderDao {

    /**
     * The method creates new record in database table.
     *
     * @param order entity that specifies creation of new records in database table.
     * @return <tt>true</tt> if new row in database table was created successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean create(Order order) throws DaoException;

    /**
     * The method updates specified record in database table.
     *
     * @param order that will be updated in database table.
     * @return count row if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    int update(Order order) throws DaoException;

    /**
     * The method deletes specified record in database table.
     *
     * @param id Order id.
     * @return count row if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    int delete(long id) throws DaoException;

    /**
     * The method will return the number of orders by status.
     *
     * @param status order id.
     * @return Order count.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    long countOrderByStatus(OrderStatus status) throws DaoException;

    /**
     * The method returns specified Order by id.
     *
     * @param id Order by id.
     * @return specified Optional Order by id.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<Order> getOrderById(long id) throws DaoException;

}
