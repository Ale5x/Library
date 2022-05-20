package com.epam.library.dao.impl;

import com.epam.library.dao.OrderDtoDao;
import com.epam.library.entity.dto.OrderDto;
import com.epam.library.entity.OrderStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderDtoDaoImplTest {

    private OrderDtoDao orderDtoDao;

    @BeforeEach
    public void setUp() throws Exception {
        orderDtoDao = new OrderDtoDaoImpl();
    }

    @Test
    public void getOrderById() {
        Long requestId = 2L;
        Optional<OrderDto> optionalRequestMaterial = orderDtoDao.getOrderById(requestId);
        System.out.println(optionalRequestMaterial);
        assertTrue(optionalRequestMaterial.isPresent());
    }

    @Test
    public void getOrderByCity() {
        String city = "Minsk";
        List<OrderDto> requests = orderDtoDao.getOrderByCity(city);
        for (OrderDto orderBookDto :requests) {
            System.out.println(orderBookDto);
        }
        assertFalse(requests.isEmpty());
    }

    @Test
    public void getOrderByStatus() {
        List<OrderDto> requests = orderDtoDao.getOrderByStatus(OrderStatus.OPENED);
        System.out.println(requests.size());
        for (OrderDto orderBookDto :requests) {
            System.out.println(orderBookDto);
        }
        assertFalse(requests.isEmpty());
    }

    @Test
    public void getOrderByUser() {
        Long userId = 1L;
        List<OrderDto> requests = orderDtoDao.getOrderByUser(userId);
        for (OrderDto orderBookDto :requests) {
            System.out.println(orderBookDto);
        }
        assertFalse(requests.isEmpty());
    }

    @Test
    public void getOrders() {
        List<OrderDto> orders = orderDtoDao.getOrders();
        for (OrderDto orderBookDto :orders) {
            System.out.println(orderBookDto);
        }
        assertFalse(orders.isEmpty());
    }
}