package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Author;
import com.epam.library.service.AuthorService;
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
 * is used to prepare the display of author statistics.
 *
 * @author Alexander Pishchala
 */

public class GoToStatisticAuthorCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToStatisticAuthorCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_STATISTIC_AUTHOR);
            AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
            String authorId = req.getParameter(Constant.AUTHOR_ID);
            if (authorId != null) {
                Author author = authorService.showAuthorById(authorId).orElse(new Author("-"));
                long countBook = authorService.getCountBooksByAuthor(authorId);
                req.setAttribute(Constant.AUTHORS_STAT, author);
                req.setAttribute(Constant.AUTHORS_COUNT_BOOKS, countBook);
            }
            req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while viewing author statistics.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
