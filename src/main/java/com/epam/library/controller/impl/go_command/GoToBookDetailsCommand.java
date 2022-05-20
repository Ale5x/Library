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
import java.io.IOException;
import java.util.Optional;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to get detailed book data.
 *
 * @author Alexander Pishchala
 */

public class GoToBookDetailsCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToBookDetailsCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String bookId = req.getParameter(Constant.BOOK_ID);
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND +
                    CommandType.GO_TO_BOOK_DETAILS_PAGE + "&" + Constant.BOOK_ID + "=" + bookId);
            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            Optional<BookDto> bookOptional;
            if (bookId != null) {
                bookOptional = bookDtoService.showBookById(bookId);
                req.setAttribute(Constant.BOOKS, bookOptional.get());
            }
            req.getRequestDispatcher(PathJsp.BOOK_DETAILS).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error getting book description.");
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
