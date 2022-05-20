package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
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
 * is used to update order data.
 *
 * @author Alexander Pishchala
 */

public class UpdateOrderCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UpdateOrderCommand.class);


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long orderId = (Long) req.getSession().getAttribute(Constant.ORDER_ID);
            String city = req.getParameter(Constant.LIBRARY_CITY);
            String comment = req.getParameter(Constant.ORDER_BOOK_COMMENT);
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            req.getSession().removeAttribute(Constant.ORDER_ID);
            if (orderId != null && city != null) {
                boolean resultOperation = orderService.update(orderId + "", comment, city);
                if (resultOperation) {
                    req.getSession().setAttribute(Constant.MESSAGE_CODE_1020, Constant.MESSAGE_CODE_1020);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }  else {
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1025, Constant.MESSAGE_ERROR_CODE_1025);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            }else {
                req.getRequestDispatcher(PathJsp.ORDER_USER_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error updating order.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
