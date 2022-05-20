package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Order;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.service.BookDtoService;
import com.epam.library.service.OrderService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to prepare the display for updating the order.
 *
 * @author Alexander Pishchala
 */

public class GoToUpdateOrderCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToUpdateOrderCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String orderId = req.getParameter(Constant.ORDER_ID);
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND +
                    CommandType.GO_TO_UPDATE_ORDER + "&" + Constant.ORDER_ID + "=" + orderId);
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            if (orderId != null) {
                Optional<Order> optionalOrder = orderService.showById(orderId);
                Optional<BookDto> optionalBook = bookDtoService.showBookById(optionalOrder.get().getBookId() + "");

                req.getSession().setAttribute(Constant.ORDER_ID, optionalOrder.get().getOrderId());
                req.setAttribute(Constant.ORDERS, optionalOrder.get());
                req.setAttribute(Constant.BOOKS, optionalBook.get());
                req.getRequestDispatcher(PathJsp.UPDATE_ORDER_PAGE).forward(req, resp);
            } else {
                req.getRequestDispatcher(PathJsp.ORDER_USER_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("An error occurred while preparing an order for an upgrade.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
