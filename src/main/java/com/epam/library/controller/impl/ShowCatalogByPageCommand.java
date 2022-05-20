package com.epam.library.controller.impl;

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

import java.util.List;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to view books page by page.
 *
 * @author Alexander Pishchala
 */

public class ShowCatalogByPageCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ShowCatalogByPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Show catalog by page.");

            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            String next = req.getParameter(Constant.NEXT);
            String back = req.getParameter(Constant.BACK);
            String pageLine = req.getParameter(Constant.JUMP_PAGE);
            Integer page = (Integer) req.getSession().getAttribute(Constant.PAGE_CATALOG);
            List<BookDto> books;
            if (page == null) {
                page = Integer.valueOf(1);
            }
            int limit = 20;
            if (next != null) {
                page++;
            } else if (back != null) {
                page--;
                if (page <= 0) {
                    page = 1;
                }
            } else if (pageLine != null) {
                    page = Integer.parseInt(pageLine);
            } else {
                page = Integer.valueOf(1);
            }
            books = bookDtoService.showByPage(page, limit);
            req.getSession().setAttribute(Constant.PAGE_CATALOG, page);
            req.setAttribute(Constant.BOOKS, books);
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND
                    + CommandType.SHOW_CATALOG_BY_PAGE + "&" + Constant.PAGE_CATALOG + "=" + page);
            req.getRequestDispatcher(PathJsp.BOOK_CATALOG_BY_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("An error occurred while browsing the book catalog.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}