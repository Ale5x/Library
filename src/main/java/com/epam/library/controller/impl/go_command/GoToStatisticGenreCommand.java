package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Genre;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to prepare the display of genre statistics.
 *
 * @author Alexander Pishchala
 */

public class GoToStatisticGenreCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToStatisticGenreCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_STATISTIC_GENRE);
            GenreService genreService = ServiceFactory.getInstance().getGenreService();
            String genreId = req.getParameter(Constant.GENRE_ID);
            if (genreId != null) {
                Genre genre = genreService.showGenreById(genreId).orElse(new Genre("-"));
                long countBook = genreService.getCountBooksByGenres(genreId);
                req.setAttribute(Constant.GENRE_STAT, genre);
                req.setAttribute(Constant.GENRE_COUNT_BOOKS, countBook);
            }
            req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while viewing author statistics.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
