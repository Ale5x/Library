package com.epam.library.controller.impl;

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
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to display the book catalog.
 *
 * @author Alexander Pishchala
 */

public class CatalogBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(CatalogBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.CATALOG_BOOK);
            BookDtoService bookService = ServiceFactory.getInstance().getBookDtoService();

            String bookId = req.getParameter(Constant.BOOK_ID);
            String title = req.getParameter(Constant.BOOK_TITLE);
            String isbn = req.getParameter(Constant.BOOK_ISBN);
            String author = req.getParameter(Constant.BOOK_AUTHOR);
            String genre = req.getParameter(Constant.BOOK_GENRE);
            String all = req.getParameter(Constant.BOOK_ALL_BOOKS);
            String city = req.getParameter(Constant.LIBRARY_CITY);

            List<BookDto> books = new ArrayList<>();

            if(bookId == null && title == null && isbn == null && author == null
                    && genre == null && all == null && city == null) {
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            } else {
                if (bookId != null && bookId != "") {
                    books.add(bookService.showBookById(bookId).orElse(new BookDto("-", "-")));
                }else if (title != "" && title != null || isbn != "" && isbn != null
                        || author != "" && author != null || genre != "" && genre != null) {
                    books = bookService.showBookByParameter(title, isbn, genre, author);
                } else if (all != null){
                    books = bookService.showCatalog();
                } else if (city != null) {
                    books = bookService.showBookByCity(city);
                }
                req.setAttribute(Constant.BOOKS, books);
                req.setAttribute(Constant.BOOK_SIZE, books.size());
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error in controllers book directory." , e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
