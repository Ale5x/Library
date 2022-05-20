package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to prepare the display for updating the book.
 *
 * @author Alexander Pishchala
 */

public class GoToUpdateBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToUpdateBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            String bookId = req.getParameter(Constant.BOOK_ID);
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_UPDATE_BOOK + "&bookId=" + bookId);
            Optional<BookDto> optionalBook = bookDtoService.showBookById(bookId);
            if (optionalBook.isPresent()) {
                req.getSession().setAttribute(Constant.UPDATE_BOOK_ID, optionalBook.get().getBookDtoId());
                req.setAttribute(Constant.BOOKS, optionalBook.get());
                req.getRequestDispatcher(PathJsp.UPDATE_BOOK_PAGE).forward(req, resp);
            } else {
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Book for update not received.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
