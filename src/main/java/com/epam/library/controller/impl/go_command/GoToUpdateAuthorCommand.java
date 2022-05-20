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
import java.util.Optional;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to prepare the display for updating the author.
 *
 * @author Alexander Pishchala
 */

public class GoToUpdateAuthorCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToUpdateAuthorCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String authorId = req.getParameter(Constant.UPDATE_AUTHOR_ID);
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_UPDATE_AUTHOR
                    + "&" + Constant.UPDATE_AUTHOR_ID + "=" + authorId);
            AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
            if (authorId != null) {
                Optional<Author> optionalAuthor = authorService.showAuthorById(authorId);
                req.getSession().setAttribute(Constant.UPDATE_AUTHOR_ID, optionalAuthor.orElse(
                        new Author(0,"")).getAuthorId());
                req.setAttribute(Constant.AUTHORS, optionalAuthor.orElse(new Author(0, "-")));
                req.getRequestDispatcher(PathJsp.UPDATE_AUTHOR_PAGE).forward(req, resp);
            } else {
                req.getRequestDispatcher(CommandType.CONTROLLER_COMMAND + CommandType.CATALOG_BOOK).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("Error preparing author data for update.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
