package com.epam.library.dao.impl;


import com.epam.library.dao.DaoException;
import com.epam.library.dao.OrderDao;
import com.epam.library.entity.Order;
import com.epam.library.entity.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDaoImplTest {

    private OrderDao orderDao;

    @BeforeEach
    public void setUp() throws DaoException {
        orderDao = new OrderDaoImpl();
    }

    @Test
    public void create() {
        Order order = new Order();
        order.setBookId(1);
        order.setUserId(1);
        order.setLibraryCity("Minsk");
        order.setStatus(OrderStatus.OPENED);
        try {
            boolean condition = orderDao.create(order);
            System.out.println(condition);
            assertFalse(condition);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        Order order = new Order();
        order.setOrderId(1);
        order.setBookId(1L);
        order.setAdminId(1L);
        order.setUserId(1L);
        order.setComment("Update");
        order.setLibraryCity("Brest");
        order.setStatus(OrderStatus.OPENED);
        try {
            int expected = orderDao.update(order);
            int actual = 1;
            assertEquals(expected, actual);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        Long ordertId = 2L;
        try {
            int expected = orderDao.delete(ordertId);
            int actual = 1;
            assertEquals(expected, actual);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getOrderById(Long id) throws DaoException {
        Long requestId = 2L;
        Optional<Order> optionalOrder = orderDao.getOrderById(requestId);
        System.out.println(optionalOrder);
        assertTrue(optionalOrder.isPresent());
    }
}