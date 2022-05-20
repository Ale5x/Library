package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
import com.epam.library.service.OrderService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used for actions with orders.
 *
 * @author Alexander Pishchala
 */

public class ActionOrderCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionOrderCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.ACTION_ORDER);
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            String orderId = req.getParameter(Constant.ORDER_ID);
            String status = req.getParameter(Constant.STATUS);
            User user = (User) req.getSession().getAttribute(Constant.USER);

            if (orderId != null && status != null) {
                boolean resultOperation = orderService.updateStatus(orderId, status, user.getUserId() + "");
                if (resultOperation) {
                    req.getSession().setAttribute(Constant.MESSAGE_CODE_1010, Constant.MESSAGE_CODE_1010);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                } else {
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1015, Constant.MESSAGE_ERROR_CODE_1015);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher(PathJsp.ORDERS_CATALOG_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error updating book order status.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
