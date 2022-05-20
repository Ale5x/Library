package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.dto.OrderDto;
import com.epam.library.service.OrderDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to view the order catalog.
 *
 * @author Alexander Pishchala
 */

public class OrderCatalogCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(OrderCatalogCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.ORDER_CATALOG);
            OrderDtoService orderDtoService = ServiceFactory.getInstance().getOrderDtoService();
            String userId = req.getParameter(Constant.USER_ID);
            String orderId = req.getParameter(Constant.ORDER_ID);
            String city = req.getParameter(Constant.LIBRARY_CITY);
            String status = req.getParameter(Constant.STATUS);
            String allOrders = req.getParameter(Constant.ORDER_ALL);
            List<OrderDto> orders = new ArrayList<>();
            if (userId != null && userId !="") {
                orders = orderDtoService.showOrdersUser(userId);
            }else if (orderId != null && orderId != "") {
                orders.add(orderDtoService.showOrderById(orderId).orElse(new OrderDto()));
            }else if (city != null && status == null) {
                orders = orderDtoService.showOrdersByCity(city);
            }else if (status != null && city == null) {
                orders = orderDtoService.showOrdersByStatus(status);
            }else if (city != null  && status != null) {
                orders = orderDtoService.showOrdersByCityAndStatus(city, status);
            } else if (allOrders != null) {
                orders = orderDtoService.showAllOrders();
            }
            req.setAttribute(Constant.ORDERS_SIZE, orders.size());
            req.setAttribute(Constant.ORDERS, orders);
            req.getRequestDispatcher(PathJsp.ORDERS_CATALOG_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            logger.error("Error while working with the order catalog.", e);
            e.printStackTrace();
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
