package com.epam.library.service.impl;

import com.epam.library.entity.dto.OrderDto;
import com.epam.library.service.OrderDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderDtoServiceImplTest {

    private OrderDtoService orderDtoService;

    @BeforeEach
    void setUp() {
        orderDtoService = ServiceFactory.getInstance().getOrderDtoService();
    }

    @Test
    void showOrdersUser() throws ServiceException {
        String userId = "1";
        List<OrderDto> orders = orderDtoService.showOrdersUser(userId);
        assertNotNull(orders);
    }

    @Test
    void showOrderById() throws ServiceException {
        String orderId = "1";
        Optional<OrderDto> optionalOrder = orderDtoService.showOrderById(orderId);
        assertTrue(optionalOrder.isPresent());
    }

    @Test
    void showAllOrders() throws ServiceException {
        List<OrderDto> orders = orderDtoService.showAllOrders();
        assertNotNull(orders);
    }

    @Test
    void showOrdersByStatus() throws ServiceException {
        String status = "opened";
        List<OrderDto> orders = orderDtoService.showOrdersByStatus(status);
        assertNotNull(orders);
    }

    @Test
    void showOrdersByCityAndStatus() throws ServiceException {
        String city = "Минск";
        String status = "opened";
        List<OrderDto> orders = orderDtoService.showOrdersByCityAndStatus(city, status);
        assertNotNull(orders);
    }

    @Test
    void showOrdersByCity() throws ServiceException {
        String city = "Минск";
        List<OrderDto> orders = orderDtoService.showOrdersByCity(city);
        assertNotNull(orders);
    }
}