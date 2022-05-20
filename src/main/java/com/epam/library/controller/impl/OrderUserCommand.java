package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
import com.epam.library.entity.dto.OrderDto;
import com.epam.library.service.OrderDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to view the user's orders.
 *
 * @author Alexander Pishchala
 */

public class OrderUserCommand implements Command {

    private final static Logger logger = LoggerFactory.getLogger(OrderUserCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.ORDER_USER);
            logger.info("Getting orders for user.");
            OrderDtoService orderDtoService = ServiceFactory.getInstance().getOrderDtoService();
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute(Constant.USER);
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            List<OrderDto> orders = orderDtoService.showOrdersUser(user.getUserId() + "");

            req.setAttribute(Constant.USER_FIRST_NAME, firstName);
            req.setAttribute(Constant.USER_LAST_NAME, lastName);
            req.setAttribute(Constant.ORDERS, orders);
            req.getRequestDispatcher(PathJsp.ORDER_USER_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.warn("Error while viewing user's orders.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
