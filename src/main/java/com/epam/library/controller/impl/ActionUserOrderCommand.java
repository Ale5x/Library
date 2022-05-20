package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
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
 * is used for actions with user orders.
 *
 * @author Alexander Pishchala
 */

public class ActionUserOrderCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionUserOrderCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.ACTION_USER_ORDER);
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            String orderUserId = req.getParameter(Constant.ORDER_USER_ID);
            String action = req.getParameter(Constant.ACTION);
            if (orderUserId != null && action != null) {
                boolean resultOperation = orderService.delete(orderUserId);
                if (resultOperation) {
                    req.getSession().setAttribute(Constant.MESSAGE_CODE_1016, Constant.MESSAGE_CODE_1016);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ORDER_USER);
                } else {
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1020, Constant.MESSAGE_ERROR_CODE_1020);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher(CommandType.CONTROLLER_COMMAND + CommandType.ORDER_USER).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("Error while deleting book order by user.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
