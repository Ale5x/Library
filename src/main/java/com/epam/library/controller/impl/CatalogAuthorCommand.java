package com.epam.library.controller.impl;

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
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to display the authors directory.
 *
 * @author Alexander Pishchala
 */

public class CatalogAuthorCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(CatalogAuthorCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.CATALOG_AUTHOR);
            AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
            String authorId = req.getParameter(Constant.AUTHOR_ID);
            String name = req.getParameter(Constant.AUTHOR_NAME);
            String allAuthor = req.getParameter(Constant.AUTHOR_ALL);
            List<Author> authors = new ArrayList<>();
            if(name != null && name != "" || authorId != null && authorId != "" || allAuthor != null) {
                if (name != null) {
                    authors.add(authorService.showAuthorByName(name).orElse(new Author()));
                } else if (authorId != null) {
                    authors.add(authorService.showAuthorById(authorId).orElse(new Author()));
                } else if (allAuthor != null) {
                    authors = authorService.showAllAuthors();
                }
                req.setAttribute(Constant.AUTHORS, authors);
                req.setAttribute(Constant.AUTHORS_SIZE, authors.size());
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            } else {
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("Error when working in the authors directory.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
