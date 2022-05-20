package com.epam.library.service.impl;

import com.epam.library.entity.Order;
import com.epam.library.entity.OrderStatus;
import com.epam.library.service.OrderService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImplTest.class);
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = ServiceFactory.getInstance().getOrderService();
    }

    @Test
    void createRightTest() {
        try{
            logger.info("Order creation test.");
            String bookId = "1";
            String userId = "1";
            String city = "Minsk";
            String comment = "Comment";
            logger.info("Parameters: bookId - {}, userId - {}, city - {}, comment - {}",
                    bookId, userId, city, comment);
            boolean condition = orderService.create(bookId, userId, city, comment);
            logger.info("Result operation - {}", condition);
            assertTrue(condition);
            logger.info("Finish test.");
        }catch (ServiceException e){}
    }

    @Test
    void createWrongNotNumberIdTest() {

        logger.info("Order creation test with not right type ID.");
        String bookId = "1w";
        String userId = "1";
        String city = "Minsk";
        String comment = "Comment";
        logger.info("Parameters: bookId - {}, userId - {}, city - {}, comment - {}",
                bookId, userId, city, comment);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            orderService.create(bookId, userId, city, comment);
        });
        String expectedMessage = "Invalid type value ID";
        logger.info("Parameters: bookId - {}, exceptionMessage - {}", bookId, expectedMessage);
        String actualMessage = exception.getMessage();
        logger.info("Result operation - " + actualMessage.contains(expectedMessage));
        assertTrue(actualMessage.contains(expectedMessage));
        logger.info("Finish test.");
    }

    @Test
    void updateStatusRightTest() {
        try {
            logger.info("Order status update test.");
            String orderId = "1";
            String adminId = "1";
            String status = OrderStatus.OPENED.name();
            logger.info("Parameters: orderId - {}, adminId - {}, status - {}",
                    orderId, adminId, status);
            boolean condition = orderService.updateStatus(orderId, status, adminId);
            logger.info("Result operation - {}", condition);
            assertTrue(condition);
            logger.info("Finish test.");
        }catch (ServiceException e){}
    }

    @Test
    void updateStatusWrongNotNumberIdTest() {
        logger.info("Order status update test with not right type number ID.");
        String orderId = "k1";
        String adminId = "1";
        String status = OrderStatus.OPENED.name();
        logger.info("Parameters: orderId - {}, adminId - {}, status - {}",
                orderId, adminId, status);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            orderService.updateStatus(orderId, status, adminId);
        });
        String expectedMessage = "Invalid type value ID";
        logger.info("Parameters: orderId - {}, exceptionMessage - {}", orderId, expectedMessage);
        String actualMessage = exception.getMessage();
        logger.info("Result operation - " + actualMessage.contains(expectedMessage));
        assertTrue(actualMessage.contains(expectedMessage));
        logger.info("Finish test.");
    }

    @Test
    void updateStatusWrongTypeStatusTest() {
        logger.info("Order status update test with not right type status.");
        String orderId = "1";
        String adminId = "1";
        String status = "status";
        logger.info("Parameters: orderId - {}, adminId - {}, status - {}",
                orderId, adminId, status);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            orderService.updateStatus(orderId, status, adminId);
        });
        String expectedMessage = "Invalid order status.";
        logger.info("Parameters: orderId - {}, exceptionMessage - {}", orderId, expectedMessage);
        String actualMessage = exception.getMessage();
        logger.info("Result operation - " + actualMessage.contains(expectedMessage));
        assertTrue(actualMessage.contains(expectedMessage));
        logger.info("Finish test.");
    }

    @Test
    void updateRightTest() {
        try {
            logger.info("Order update test.");
            String orderId = "1";
            String comment = "comment";
            String city = "Minsk";
            logger.info("Parameters: orderId - {}, comment - {}, city - {}",
                    orderId, comment, city);
            boolean condition = orderService.update(orderId, comment, city);
            logger.info("Result operation - {}", condition);
            assertTrue(condition);
            logger.info("Finish test.");
        }catch (ServiceException e){}
    }

    @Test
    void updateWrongNumberIdTest() {
        logger.info("Order update test with not right type number ID.");
        String orderId = "1q";
        String comment = "comment";
        String city = "Minsk";
        logger.info("Parameters: orderId - {}, comment - {}, city - {}",
                orderId, comment, city);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            orderService.update(orderId, comment, city);
        });
        String expectedMessage = "Invalid type value ID.";
        logger.info("Parameters: orderId - {}, exceptionMessage - {}", orderId, expectedMessage);
        String actualMessage = exception.getMessage();
        logger.info("Result operation - " + actualMessage.contains(expectedMessage));
        assertTrue(actualMessage.contains(expectedMessage));
        logger.info("Finish test.");
    }

    @Test
    void deleteRightTest() {
        try {
            logger.info("Order deletion test.");
            String orderId = "1";
            logger.info("Parameters: orderId - {}", orderId);
            boolean condition = orderService.delete(orderId);
            logger.info("Result operation - {}", condition);
            assertTrue(condition);
            logger.info("Finish test.");
        }catch (ServiceException e){}
    }

    @Test
    void deleteWrongNumberIdTest() {
        logger.info("Order deletion test with not right type number ID.");
        String orderId = "1q";
        logger.info("Parameters: orderId - {}", orderId);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            orderService.delete(orderId);
        });
        String expectedMessage = "Invalid type value ID.";
        logger.info("Parameters: orderId - {}, exceptionMessage - {}", orderId, expectedMessage);
        String actualMessage = exception.getMessage();
        logger.info("Result operation - " + actualMessage.contains(expectedMessage));
        assertTrue(actualMessage.contains(expectedMessage));
        logger.info("Finish test.");
    }

    @Test
    void deleteWrongStatusTest() {
        logger.info("Order deletion test with not right status.");
        String orderId = "1";
        logger.info("Parameters: orderId - {}", orderId);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            orderService.delete(orderId);
        });
        String expectedMessage = "The order cannot be deleted because it must be open or closed.";
        logger.info("Parameters: orderId - {}, exceptionMessage - {}", orderId, expectedMessage);
        String actualMessage = exception.getMessage();
        logger.info("Result operation - " + actualMessage.contains(expectedMessage));
        assertTrue(actualMessage.contains(expectedMessage));
        logger.info("Finish test.");
    }

    @Test
    void showCountByStatusRightTest() {
        try {
            logger.info("Test getting the number of orders by status.");
            String status = OrderStatus.ARRIVED.name();
            logger.info("Parameters: status - {}", status);
            long condition = orderService.showCountByStatus(status);
            logger.info("Result operation - {}", condition);
            assertTrue(condition > 0);
            logger.info("Finish test.");
        }catch (ServiceException e){}
    }

    @Test
    void showCountByStatusWrongStatusTest() {
        logger.info("Test getting the number of orders by with not right status.");
        String status = "status";
        logger.info("Parameters: status - {}", status);
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            orderService.showCountByStatus(status);
        });
        String expectedMessage = "Invalid order status.";
        logger.info("Parameters: status - {}, exceptionMessage - {}", status, expectedMessage);
        String actualMessage = exception.getMessage();
        logger.info("Result operation - " + actualMessage.contains(expectedMessage));
        assertTrue(actualMessage.contains(expectedMessage));
        logger.info("Finish test.");
    }

    @Test
    void showByIdRightTest() {
        try {
            logger.info("Test receiving an order by ID");
            String orderId = "1";
            logger.info("Parameters: orderId - {}", orderId);
            Optional<Order> optionalOrder = orderService.showById(orderId);
            logger.info("Result operation - {}", optionalOrder.isPresent());
            assertTrue(optionalOrder.isPresent());
            logger.info("Finish test.");
        }catch (ServiceException e){}
    }
    @Test
    void showByIdWrongNotRightIdTest() {
            logger.info("Test receiving an order by ID with not right type ID.");
            String orderId = "1X";
            logger.info("Parameters: orderId - {}", orderId);
            ServiceException exception = assertThrows(ServiceException.class, () -> {
            orderService.showById(orderId);
            });
            String expectedMessage = "Invalid type value ID.";
            logger.info("Parameters: orderId - {}, exceptionMessage - {}", orderId, expectedMessage);
            String actualMessage = exception.getMessage();
            logger.info("Result operation - " + actualMessage.contains(expectedMessage));
            assertTrue(actualMessage.contains(expectedMessage));
            logger.info("Finish test.");
    }
}