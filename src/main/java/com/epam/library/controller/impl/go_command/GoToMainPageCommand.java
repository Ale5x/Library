package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.*;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to prepare the data needed for the main page.
 *
 * @author Alexander Pishchala
 */

public class GoToMainPageCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToMainPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Go to main page command.");
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MAIN_PAGE);
            GenreService genreService = ServiceFactory.getInstance().getGenreService();
            AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
            BookService bookService = ServiceFactory.getInstance().getBookService();

            long countBooks = bookService.getCountBooks();
            int countAuthors = authorService.getCountAuthors();
            long countGenres = genreService.getCountGenres();

            req.setAttribute(Constant.STATISTIC_COUNT_BOOKS, countBooks);
            req.setAttribute(Constant.STATISTIC_COUNT_AUTHORS, countAuthors);
            req.setAttribute(Constant.STATISTIC_COUNT_GENRES, countGenres);
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<Library> libraries = libraryService.showByStatus(LibraryStatus.OPENED.name());
            req.getSession().setAttribute(Constant.LIBRARIES, libraries);

            req.getRequestDispatcher(PathJsp.MAIN_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while executing the command GoTo Main Command error during command execution", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
