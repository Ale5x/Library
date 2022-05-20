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
 * is used to search for books.
 *
 * @author Alexander Pishchala
 */

public class SearchBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(SearchBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.SEARCH_BOOKS);
            logger.info("Search book by parameters.");
            BookDtoService bookDtoService = ServiceFactory.getInstance().getBookDtoService();
            GenreService genreService = ServiceFactory.getInstance().getGenreService();
            AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
            BookService bookService = ServiceFactory.getInstance().getBookService();

            long countBooks = bookService.getCountBooks();
            int countAuthors = authorService.getCountAuthors();
            long countGenres = genreService.getCountGenres();

            String isbn = req.getParameter(Constant.BOOK_ISBN);
            String title = req.getParameter(Constant.BOOK_TITLE);
            String genre = req.getParameter(Constant.BOOK_GENRE);
            String author = req.getParameter(Constant.BOOK_AUTHOR);
            List<BookDto> booksDTO = new ArrayList<>();
            if (isbn == "" && title == "" && genre == "" && author == "") {
                req.getRequestDispatcher(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MAIN_PAGE).forward(req, resp);
            } else {
                booksDTO = bookDtoService.showBookByParameter(title, isbn, genre, author);
                req.setAttribute(Constant.BOOK_SIZE, booksDTO.size());
                req.setAttribute(Constant.BOOKS, booksDTO);
                req.setAttribute(Constant.STATISTIC_COUNT_BOOKS, countBooks);
                req.setAttribute(Constant.STATISTIC_COUNT_AUTHORS, countAuthors);
                req.setAttribute(Constant.STATISTIC_COUNT_GENRES, countGenres);

                req.getRequestDispatcher(PathJsp.MAIN_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("An error occured while searching the book by parameters. ", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
