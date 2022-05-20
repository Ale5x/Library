package com.epam.library.service.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoFactory;
import com.epam.library.dao.OrderDao;
import com.epam.library.entity.Book;
import com.epam.library.entity.Library;
import com.epam.library.entity.Order;
import com.epam.library.entity.OrderStatus;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Class {@link OrderServiceImpl} is service class which provide operations an order
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public boolean create(String bookId, String userId, String city, String comment) throws ServiceException {
        try {
            OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            if (validator.isNumber(bookId) && validator.isNumber(userId)) {
                Optional<Library> optionalLibrary = libraryService.showByCity(city);
                if (optionalLibrary.isPresent()) {
                    BookService bookService = ServiceFactory.getInstance().getBookService();
                    Optional<Book> optionalBook = bookService.showBookById(bookId);
                    bookService.addBorrow(optionalBook.get().getBookId() + "");
                    Order order = new Order();
                    order.setBookId(Long.parseLong(bookId.trim()));
                    order.setAdminId(0);
                    order.setUserId(Long.parseLong(userId.trim()));
                    order.setLibraryCity(city);
                    order.setComment(comment != "" ? comment : "-");
                    order.setStatus(OrderStatus.OPENED);
                    return orderDao.create(order);
                } else {
                    throw new ServiceException("Library with such a city was not found.");
                }
            } else {
                throw new ServiceException("Invalid type value ID");
            }
        }catch (DaoException e) {
            logger.error("Error in services when creating an order.");
            throw new ServiceException("Error in services when creating an order.", e);
        }
    }

    @Override
    public boolean updateStatus(String orderId, String status, String adminId) throws ServiceException {
        try {
            OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(orderId) && validator.isNumber(adminId)) {
                if (status.equalsIgnoreCase(OrderStatus.OPENED.name())
                        || status.equalsIgnoreCase(OrderStatus.APPROVED.name())
                        || status.equalsIgnoreCase(OrderStatus.ARRIVED.name())
                        || status.equalsIgnoreCase(OrderStatus.REJECTED.name())
                        || status.equalsIgnoreCase(OrderStatus.CLOSED.name())) {
                    Optional<Order> optionalOrder = orderDao.getOrderById(Long.parseLong(orderId.trim()));
                    if (optionalOrder.isPresent()) {
                        Order order = optionalOrder.get();
                        order.setAdminId(Long.parseLong(adminId.trim()));
                        order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
                        orderDao.update(order);

                        if (status.equalsIgnoreCase(OrderStatus.REJECTED.name()) ||
                                status.equalsIgnoreCase(OrderStatus.CLOSED.name())) {
                            BookService bookService = ServiceFactory.getInstance().getBookService();
                            Optional<Book> optionalBook = bookService.showBookById(order.getBookId() + "");
                            bookService.deleteBorrow(optionalBook.get().getBookId() + "");
                        }
                        return true;
                    } else {
                        throw new ServiceException("Error in services when updating order status. The order does not exist.");
                    }
                } else {
                    throw new ServiceException("Invalid order status.");
                }
            } else {
                throw new ServiceException("Invalid type value ID");
            }
        }catch (DaoException e) {
            logger.error("Error in services when updating order status.");
            throw new ServiceException("Error in services when updating order status.", e);
        }
    }

    @Override
    public boolean update(String orderId, String comment, String city) throws ServiceException {
        try {
            OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            if (validator.isNumber(orderId)) {
                Optional<Library> optionalLibrary = libraryService.showByCity(city);
                if (optionalLibrary.isPresent()) {
                    Optional<Order> optionalOrder = orderDao.getOrderById(Long.parseLong(orderId.trim()));
                    if (optionalOrder.isPresent()) {
                        Order order = optionalOrder.get();
                        order.setLibraryCity(city);
                        order.setComment(comment);
                        orderDao.update(order);
                        return true;
                    } else {
                        throw new ServiceException("Error in services when updating order status. The order does not exist.");
                    }
                } else {
                    throw new ServiceException("Library with such a city was not found.");
                }
            } else {
                throw new ServiceException("Invalid type value ID.");
            }
        }catch (DaoException e) {
            logger.error("Error in services when updating an order. The order does not exist.");
            throw new ServiceException("Error in services when updating an order. The order does not exist.", e);
        }
    }

    @Override
    public boolean delete(String orderId) throws ServiceException {
        try {
            OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(orderId)) {
                Optional<Order> optionalOrder = orderDao.getOrderById(Long.parseLong(orderId.trim()));
                if (optionalOrder.isPresent()) {
                    if (optionalOrder.get().getStatus().equals(OrderStatus.OPENED) ||
                            optionalOrder.get().getStatus().equals(OrderStatus.CLOSED)) {
                        orderDao.delete(Long.parseLong(orderId.trim()));
                        if (optionalOrder.get().getStatus().equals(OrderStatus.OPENED)) {
                            BookService bookService = ServiceFactory.getInstance().getBookService();
                            bookService.deleteBorrow(optionalOrder.get().getBookId() + "");
                        }
                        return true;
                    } else {
                        throw new ServiceException("The order cannot be deleted because it must be open or closed.");
                    }
                }
            } else {
                throw new ServiceException("Invalid type value ID.");
            }
        }catch (DaoException e) {
            logger.error("Error in services when deleting an order.");
            throw new ServiceException("Error in services when deleting an order.", e);
        }
        return false;
    }

    @Override
    public long showCountByStatus(String status) throws ServiceException {
        try {
            OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
            if (status.equalsIgnoreCase(OrderStatus.OPENED.name()) || status.equalsIgnoreCase(OrderStatus.APPROVED.name())
                    || status.equalsIgnoreCase(OrderStatus.ARRIVED.name())
                    || status.equalsIgnoreCase(OrderStatus.REJECTED.name())
                    || status.equalsIgnoreCase(OrderStatus.CLOSED.name())) {
                return orderDao.countOrderByStatus(OrderStatus.valueOf(status.toUpperCase()));
            } else {
                throw new ServiceException("Invalid order status.");
            }
        }catch (DaoException e) {
            logger.error("Error in services when receiving count orders by status.");
            throw new ServiceException("Error in services when receiving count orders by status.", e);
        }
    }

    @Override
    public Optional<Order> showById(String orderId) throws ServiceException {
        try {
            OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(orderId)) {
                return orderDao.getOrderById(Long.parseLong(orderId.trim()));
            } else {
                throw new ServiceException("Invalid type value ID.");
            }
        }catch (DaoException e) {
            logger.error("Error in services when receiving an order by ID.");
            throw new ServiceException("Error in services when receiving an order by ID.", e);
        }
    }
}
