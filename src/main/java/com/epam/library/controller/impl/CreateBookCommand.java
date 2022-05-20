package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.service.BookDtoService;
import com.epam.library.service.BookService;
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
 * is used to create a book in the system.
 *
 * @author Alexander Pishchala
 */

public class CreateBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(CreateBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.CREATE_BOOK);
            String title = req.getParameter(Constant.BOOK_TITLE);
            String isbn = req.getParameter(Constant.BOOK_ISBN);
            String publisher = req.getParameter(Constant.BOOK_PUBLISHER);
            String year = req.getParameter(Constant.BOOK_YEAR);
            String count = req.getParameter(Constant.BOOK_QUANTITY);
            String city = req.getParameter(Constant.LIBRARY_CITY);
            String shelf = req.getParameter(Constant.BOOK_SHELF);
            String author = req.getParameter(Constant.BOOK_AUTHOR);
            String category = req.getParameter(Constant.BOOK_CATEGORY);
            String description = req.getParameter(Constant.BOOK_DESCRIPTION);

            if (title != "" && title != null && isbn != "" && isbn != null && publisher != "" && publisher != null
                    && year != "" && year != null && count != "" && count != null && shelf != "" && shelf != null) {
                BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
                BookService bookService = ServiceFactory.getInstance().getBookService();
                boolean resultOperation;
                if (author != "" || category != "") {
                    resultOperation = bookDtoService.create(title, isbn, publisher, year, count, city, shelf,
                            author, category,description);
                } else {
                    resultOperation = bookService.create(title, isbn, publisher, year, count, shelf, description, city);
                }
                if (resultOperation) {
                    req.getSession().setAttribute(Constant.MESSAGE_CODE_1004, Constant.MESSAGE_CODE_1004);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                } else {
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1009, Constant.MESSAGE_ERROR_CODE_1009);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher(PathJsp.ADD_BOOK_PAGE).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("Error while creating book.", e);
            e.printStackTrace();
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
