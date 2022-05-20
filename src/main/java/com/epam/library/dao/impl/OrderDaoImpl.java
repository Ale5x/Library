package com.epam.library.dao.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoHelper;
import com.epam.library.dao.OrderDao;
import com.epam.library.dao.connection.ConnectionPool;
import com.epam.library.dao.constant.ColumnName;
import com.epam.library.dao.constant.TableName;
import com.epam.library.dao.mapper.OrderDtoMapper;
import com.epam.library.dao.mapper.OrderMapper;
import com.epam.library.entity.Order;
import com.epam.library.entity.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class {@link OrderDaoImpl} is an implementation of the 'OrderDao' interface.
 *
 * @author Alexander Pishchala
 */

public class OrderDaoImpl extends DaoHelper implements OrderDao {

    private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

    private final static String ADD_ORDER_QUERY = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s)" +
                    " values(?, ?, ?, ?, (SELECT %s from %s where %s=?), (SELECT %s from %s where %s=?));",
            TableName.ORDER, ColumnName.ORDER_ID_BOOK, ColumnName.ORDER_ID_USER, ColumnName.ORDER_ID_ADMIN,
            ColumnName.ORDER_COMMENT, ColumnName.ORDER_ID_LIBRARY, ColumnName.ORDER_ID_STATUS,
            ColumnName.LIBRARY_ID_LIBRARY, TableName.LIBRARY, ColumnName.LIBRARY_CITY,
            ColumnName.ORDER_STATUS_ID_STATUS, TableName.ORDER_STATUS, ColumnName.ORDER_STATUS_STATUS);

    private final static String UPDATE_ORDER_QUERY = String.format("UPDATE %s set %s=?, %s=?, %s=?, %s=?, " +
                    "%s=(SELECT %s from %s where %s=?), %s=(SELECT %s from %s where %s=?) WHERE %s=?;",
            TableName.ORDER, ColumnName.ORDER_ID_BOOK, ColumnName.ORDER_ID_USER, ColumnName.ORDER_ID_ADMIN,
            ColumnName.ORDER_COMMENT, ColumnName.ORDER_ID_LIBRARY, ColumnName.LIBRARY_ID_LIBRARY, TableName.LIBRARY,
            ColumnName.LIBRARY_CITY, ColumnName.ORDER_ID_STATUS, ColumnName.ORDER_STATUS_ID_STATUS,
            TableName.ORDER_STATUS, ColumnName.ORDER_STATUS_STATUS, ColumnName.ORDER_ID_REQUEST);

    private static final String DELETE_ORDER_QUERY = String.format("delete from %s where %s=?",
            TableName.ORDER, ColumnName.ORDER_ID_REQUEST);

    private final static String GET_ORDER_BY_ID_QUERY = String.format("SELECT * from %s left join %s on(%s.%s=%s.%s) " +
                    "left join %s on(%s.%s=%s.%s) where %s.%s=? group by %s.%s", TableName.ORDER, TableName.LIBRARY,
            TableName.ORDER, ColumnName.ORDER_ID_LIBRARY, TableName.LIBRARY, ColumnName.LIBRARY_ID_LIBRARY,
            TableName.ORDER_STATUS, TableName.ORDER, ColumnName.ORDER_ID_STATUS, TableName.ORDER_STATUS,
            ColumnName.ORDER_STATUS_ID_STATUS, TableName.ORDER, ColumnName.ORDER_ID_REQUEST, TableName.ORDER,
            ColumnName.ORDER_ID_REQUEST);

    private final static String GET_ALL_ORDERS_QUERY = String.format("SELECT * from %s left join %s on(%s.%s=%s.%s) " +
                    "left join %s on(%s.%s=%s.%s) group by %s.%s", TableName.ORDER, TableName.LIBRARY,
            TableName.ORDER, ColumnName.ORDER_ID_LIBRARY, TableName.LIBRARY, ColumnName.LIBRARY_ID_LIBRARY,
            TableName.ORDER_STATUS, TableName.ORDER, ColumnName.ORDER_ID_STATUS, TableName.ORDER_STATUS,
            ColumnName.ORDER_STATUS_ID_STATUS, TableName.ORDER, ColumnName.ORDER_ID_REQUEST);

    private final static String GET_ORDER_BY_STATUS_QUERY = String.format("SELECT * from %s where %s=(Select %s " +
            "from %s where %s=?)", TableName.ORDER, ColumnName.ORDER_ID_STATUS, ColumnName.ORDER_STATUS_ID_STATUS,
            TableName.ORDER_STATUS, ColumnName.ORDER_STATUS_STATUS);


    @Override
    public boolean create(Order order) throws DaoException {
        logger.info("Create order.");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, ADD_ORDER_QUERY, order.getBookId(),
                    order.getUserId(), order.getAdminId(), order.getComment(), order.getLibraryCity(),
                    order.getStatus().name());
            prStatement.execute();
            return true;
        } catch (SQLException sqlE) {
            logger.error("Failed to create order. OrderBookDto - {}.", order.toString());
            throw new DaoException("Failed to create order.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public int update(Order order) throws DaoException {
        logger.info("Start update order.");
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, UPDATE_ORDER_QUERY, order.getBookId(), order.getUserId(),
                    order.getAdminId(), order.getComment(), order.getLibraryCity(), order.getStatus().name(),
                    order.getOrderId());
            return prStatement.executeUpdate();
        } catch (SQLException sqlE) {
            logger.error("Failed to update order.order - {}", order.toString());
            throw new DaoException("Failed to update order", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }


    @Override
    public int delete(long id) throws DaoException {
        logger.info("Delete order. Id - {}", id);
        PreparedStatement prStatement = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, DELETE_ORDER_QUERY, id);
            return prStatement.executeUpdate();
        } catch (SQLException sqlE) {
            logger.error("Error when deleting order. id - {}", id);
            throw new DaoException("Error when deleting order.", sqlE);
        } finally {
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public long countOrderByStatus(OrderStatus status) throws DaoException {
        logger.info("Receiving count orders by status.");
        OrderDtoMapper mapper = new OrderDtoMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
            prStatement = createPreparedStatement(connection, GET_ORDER_BY_STATUS_QUERY, status.name());
            resultSet = prStatement.executeQuery();
            long countOrder = 0;
            while (resultSet.next()) {
                countOrder = resultSet.getLong(1);
            }
            return countOrder;
        } catch (SQLException sqlE) {
            logger.error("Count order by status not received. Status - {}", status);
            throw new DaoException("Count order by status not received.", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }

    @Override
    public Optional<Order> getOrderById(long id) throws DaoException {
        logger.info("Receiving a order by ID.");
        OrderMapper mapper = new OrderMapper();
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<Order> entity = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){
            prStatement = createPreparedStatement(connection, GET_ORDER_BY_ID_QUERY, id);
            resultSet = prStatement.executeQuery();
            while (resultSet.next()) {
                entity.add(mapper.map(resultSet));
            }
            if(entity.size() == 1) {
                logger.info("Order by ID received.");
                return Optional.of(entity.get(0));
            } else if (entity.size() == 0) {
                return Optional.empty();
            } else {
                throw new DaoException("Find more 1 order.");
            }
        } catch (SQLException sqlE) {
            logger.error("Find more 1 order. Find - {}", entity.toString());
            throw new DaoException("Find more 1 order", sqlE);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(prStatement);
        }
    }
//
//    @Override
//    public Optional<Order> getOrderById(long orderId) throws DAOException {
//        logger.info("Receiving orders by ID.");
//        OrderMapper mapper = new OrderMapper();
//        PreparedStatement prStatement = null;
//        ResultSet resultSet = null;
//        List<Order> entity = new ArrayList<>();
//        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {
//            prStatement = createPreparedStatement(connection, GET_ORDER_BY_USER_QUERY, id);
//            resultSet = prStatement.executeQuery();
//            while (resultSet.next()) {
//                entity.add(mapper.map(resultSet));
//            }
//            if(entity.size() == 1) {
//                logger.info("Order by ID received.");
//                return Optional.of(entity.get(0));
//            } else if (entity.size() == 0) {
//                return Optional.empty();
//            } else {
//                throw new UnsupportedOperationException("Find more 1 order.");
//            }
//        } catch (SQLException sqlE) {
//            logger.error("OrderBookDto list by user not received. Order ID - {}", orderId);
//            throw new DAOException(sqlE);
//        } finally {
//            closeResultSet(resultSet);
//            closePreparedStatement(prStatement);
//        }
//    }
}
