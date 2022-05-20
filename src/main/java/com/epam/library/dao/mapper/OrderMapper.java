package com.epam.library.dao.mapper;

import com.epam.library.dao.constant.ColumnName;
import com.epam.library.entity.Order;
import com.epam.library.entity.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@link OrderMapper} builds the entity 'Order'.
 *
 *
 * @author Alexander Pishchala
 */

public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order map(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getLong(ColumnName.ORDER_ID_REQUEST));
        order.setUserId(resultSet.getLong(ColumnName.ORDER_ID_USER));
        order.setAdminId(resultSet.getLong(ColumnName.ORDER_ID_ADMIN));
        order.setLibraryCity(resultSet.getString(ColumnName.LIBRARY_CITY));
        order.setBookId(resultSet.getLong(ColumnName.ORDER_ID_BOOK));
        order.setDate(resultSet.getDate(ColumnName.ORDER_DATE).toLocalDate());
        order.setComment(resultSet.getString(ColumnName.ORDER_COMMENT));
        order.setStatus(OrderStatus.valueOf(resultSet.getString(ColumnName.ORDER_STATUS_STATUS).toUpperCase()));
        return order;
    }
}
