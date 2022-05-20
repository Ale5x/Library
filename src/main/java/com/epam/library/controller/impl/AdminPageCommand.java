package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.OrderStatus;
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

import java.util.List;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to display the admin page.
 *
 * @author Alexander Pishchala
 */

public class AdminPageCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(AdminPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.ADMIN_PAGE);
            OrderDtoService orderDtoService = ServiceFactory.getInstance().getOrderDtoService();
            List<OrderDto> countOrders = orderDtoService.showOrdersByStatus(OrderStatus.OPENED.name());
            req.setAttribute(Constant.ORDERS_SIZE, countOrders.size());
            req.getRequestDispatcher(PathJsp.HOME_ADMIN_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            logger.error("Error receiving open orders for the admin page.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
