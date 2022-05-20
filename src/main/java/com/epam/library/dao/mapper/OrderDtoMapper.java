package com.epam.library.dao.mapper;

import com.epam.library.dao.constant.ColumnName;
import com.epam.library.entity.dto.OrderDto;
import com.epam.library.entity.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class {@link OrderDtoMapper} builds the entity 'OrderDto'.
 *
 *
 * @author Alexander Pishchala
 */

public class OrderDtoMapper implements RowMapper<OrderDto> {

    @Override
    public OrderDto map(ResultSet resultSet) throws SQLException {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderDtoId(resultSet.getLong(ColumnName.ORDER_ID_REQUEST));
        orderDto.setBookId(resultSet.getLong(ColumnName.ORDER_ID_BOOK));
        orderDto.setAdminId(resultSet.getLong(ColumnName.ORDER_ID_ADMIN));
        orderDto.setUserId(resultSet.getLong(ColumnName.ORDER_ID_USER));
        orderDto.setCityLibrary(resultSet.getString(ColumnName.LIBRARY_CITY));
        orderDto.setCountViolations(resultSet.getInt(ColumnName.USER_COUNT_VIOLATIONS));
        orderDto.setDate(resultSet.getDate(ColumnName.ORDER_DATE).toLocalDate());
        orderDto.setStatus(OrderStatus.valueOf(resultSet.getString(ColumnName.ORDER_STATUS_STATUS).toUpperCase()));
        orderDto.setComment(resultSet.getString(ColumnName.ORDER_COMMENT));
        orderDto.setIsbn(resultSet.getString(ColumnName.BOOK_ISBN));
        orderDto.setTitle(resultSet.getString(ColumnName.BOOK_TITLE));
        orderDto.setYear(resultSet.getString(ColumnName.BOOK_YEAR));
        return orderDto;
    }
}
