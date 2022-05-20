package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
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
 * is used to order the book.
 *
 * @author Alexander Pishchala
 */

public class OrderBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(OrderBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.ORDER_BOOK);
            Long bookId = (Long) req.getSession().getAttribute(Constant.ORDER_BOOK_ID);
            User user = (User) req.getSession().getAttribute(Constant.USER);
            String library = req.getParameter(Constant.LIBRARY_CITY);
            String comment = req.getParameter(Constant.ORDER_BOOK_COMMENT);
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            req.getSession().removeAttribute(Constant.ORDER_BOOK_ID);
            if (user != null && bookId != null && library != null) {
                boolean resultOperation = orderService.create(bookId + "", user.getUserId() + "", library, comment);
                if (resultOperation) {
                    req.getSession().setAttribute(Constant.MESSAGE_CODE_1006, Constant.MESSAGE_CODE_1006);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                } else {
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1011, Constant.MESSAGE_ERROR_CODE_1011);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
            }
        }catch (ServiceException e) {
            logger.error("Error while ordering a book.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
