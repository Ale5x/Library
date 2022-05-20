package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.service.BookDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to prepare for displaying the book for order.
 *
 * @author Alexander Pishchala
 */

public class GoToOrderBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToOrderBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Preparing to order a book.");
            HttpSession session = req.getSession();
            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            BookDto bookDTO;
            String bookId = req.getParameter(Constant.BOOK_ID);
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_ORDER + "&bookId=" + bookId);
            if (bookId != "" && bookId != null) {
                Optional<BookDto> optional = bookDtoService.showBookById(bookId);
                if (optional.isPresent()) {
                    bookDTO = optional.get();
                    req.setAttribute(Constant.BOOKS, bookDTO);
                    session.setAttribute(Constant.ORDER_BOOK_ID, bookDTO.getBookDtoId());
                    req.getRequestDispatcher(PathJsp.ORDER_PAGE).forward(req, resp);
                } else {
                String negativeMessage = "Operation failed";
                req.getSession().setAttribute(Constant.NEGATIVE_MESSAGE, negativeMessage);
                resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
            }
            } else {
                resp.sendRedirect(PathJsp.MESSAGE_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("An error occurred while preparing to order the book.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
