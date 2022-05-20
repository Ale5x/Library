package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.dto.OrderDto;
import com.epam.library.service.*;
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
 * is used to display the book lending directory to the user.
 *
 * @author Alexander Pishchala
 */

public class CatalogGiveOutBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(CatalogGiveOutBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.GIVE_OUT_BOOK_USER);
            OrderDtoService orderDtoService = ServiceFactory.getInstance().getOrderDtoService();
            List<OrderDto> orders = new ArrayList<>();

            String orderId = req.getParameter(Constant.ORDER_ID);
            String city = req.getParameter(Constant.LIBRARY_CITY);
            String status = req.getParameter(Constant.STATUS);
            String all = req.getParameter(Constant.ORDER_ALL);
            if (orderId != null && orderId != "") {
                orders.add(orderDtoService.showOrderById(orderId).orElse(new OrderDto()));
            } else if (city != null && status == null) {
                orders = orderDtoService.showOrdersByCity(city);
            } else if (city != null && status != null) {
                orders = orderDtoService.showOrdersByCityAndStatus(city, status);
            }else if (all != null) {
                orders = orderDtoService.showAllOrders();
            }

            req.setAttribute(Constant.ORDERS, orders);
            req.setAttribute(Constant.ORDERS_SIZE, orders.size());
            req.getRequestDispatcher(PathJsp.GIVE_OUT_BOOK_PAGE).forward(req, resp);

        } catch (ServiceException e) {
            logger.error("An error occurred when working with the catalog for issuing books.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
