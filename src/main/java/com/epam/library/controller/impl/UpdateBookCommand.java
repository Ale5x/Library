package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Book;
import com.epam.library.entity.dto.BookDto;
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
 * is used to update the book data.
 *
 * @author Alexander Pishchala
 */

public class UpdateBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UpdateBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{

            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            BookService bookService = ServiceFactory.getInstance().getBookService();
            Long bookId = (Long) req.getSession().getAttribute(Constant.UPDATE_BOOK_ID);
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND
                    + CommandType.UPDATE_BOOK + "&" + Constant.UPDATE_BOOK_ID + "=" + bookId);
            String title = req.getParameter(Constant.BOOK_TITLE);
            String isbn = req.getParameter(Constant.BOOK_ISBN);
            String cityLibrary = req.getParameter(Constant.LIBRARY_CITY);
            String author = req.getParameter(Constant.BOOK_AUTHOR);
            String genre = req.getParameter(Constant.BOOK_GENRE);
            String publisher = req.getParameter(Constant.BOOK_PUBLISHER);
            String year = req.getParameter(Constant.BOOK_YEAR);
            String shelf = req.getParameter(Constant.BOOK_SHELF);
            String quantity = req.getParameter(Constant.BOOK_QUANTITY);
            String description = req.getParameter(Constant.BOOK_DESCRIPTION);

            boolean resultOperation;

            if (bookId != null) {
               if (title != "" && title != null || isbn != "" && isbn != null
                       || author != "" && author != null || genre != "" && genre != null
                       || publisher != "" && publisher != null
                       || year != "" && year != null || shelf != "" && shelf !=null
                       || quantity != "" && quantity != null || description != "" && description != null) {
                   if (author == "" && genre == "") {
                       Book book = new Book();

                       book.setTitle(title);
                       book.setIsbn(isbn);
                       book.setPublisher(publisher);
                       book.setYear(year);
                       book.setShelf(shelf);
                       book.setDescription(description);

                       req.getSession().removeAttribute(Constant.UPDATE_BOOK_ID);
                       resultOperation = bookService.update(bookId + "", book, quantity, cityLibrary);
                   } else {
                       BookDto bookDto = new BookDto();

                       bookDto.setTitle(title);
                       bookDto.setIsbn(isbn);
                       bookDto.setCityLibrary(cityLibrary);
                       bookDto.setPublisher(publisher);
                       bookDto.setYear(year);
                       bookDto.setShelf(shelf);
                       bookDto.setDescription(description);

                       req.getSession().removeAttribute(Constant.UPDATE_BOOK_ID);
                       resultOperation = bookDtoService.update(bookId + "", bookDto, author, genre, quantity);
                   }
                   if (resultOperation) {
                       req.getSession().setAttribute(Constant.MESSAGE_CODE_1017, Constant.MESSAGE_CODE_1017);
                       resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                   } else {
                       req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1021, Constant.MESSAGE_ERROR_CODE_1021);
                       resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                   }
               }
           } else {
               req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
           }
        }catch (ServiceException e) {
            logger.error("When updating the book, the error.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
