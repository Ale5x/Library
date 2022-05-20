package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.entity.OrderStatus;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to prepare for going to the admin page.
 *
 * @author Alexander Pishchala
 */

public class GoToAdminPageCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToAdminPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Go to the admin page.");
            HttpSession session = req.getSession();
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            long countOrders = orderService.showCountByStatus(OrderStatus.OPENED.name());
            session.setAttribute(Constant.ORDERS_SIZE, countOrders);

            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ADMIN_PAGE);

        }catch (ServiceException e) {
            logger.error("Error getting admin page.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
