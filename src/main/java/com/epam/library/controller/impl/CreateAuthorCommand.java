package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
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
 * is used to create an author in the system.
 *
 * @author Alexander Pishchala
 */

public class CreateAuthorCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(CreateAuthorCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.CREATE_BOOK);
            String name = req.getParameter(Constant.AUTHORS);
            AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
            if (name != null) {
                boolean resultOperation = authorService.create(name);
                if (resultOperation) {
                    req.getSession().setAttribute(Constant.MESSAGE_CODE_10021, Constant.MESSAGE_CODE_10021);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                } else {
                    req.getSession().setAttribute(Constant.NEGATIVE_MESSAGE, Constant.NEGATIVE_MESSAGE);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher(PathJsp.ADD_BOOK_PAGE).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("Error adding author.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
