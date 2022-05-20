package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Genre;
import com.epam.library.service.GenreService;
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
 * is used to prepare the display for updating the genre.
 *
 * @author Alexander Pishchala
 */

public class GoToUpdateGenreCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToUpdateGenreCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String genreId = req.getParameter(Constant.UPDATE_GENRE_ID);
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_UPDATE_GENRE
                    + "&" + Constant.UPDATE_GENRE_ID + "=" + genreId);
            GenreService genreService = ServiceFactory.getInstance().getGenreService();
            if (genreId != null) {
                Optional<Genre> optionalGenre = genreService.showGenreById(genreId);
                req.getSession().setAttribute(Constant.UPDATE_GENRE_ID, optionalGenre.orElse(
                        new Genre(0,"")).getGenreId());
                req.setAttribute(Constant.GENRES, optionalGenre.orElse(new Genre(0, "-")));
                req.getRequestDispatcher(PathJsp.UPDATE_GENRE_PAGE).forward(req, resp);
            } else {
                req.getRequestDispatcher(CommandType.CONTROLLER_COMMAND + CommandType.CATALOG_BOOK).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("Error preparing genre data for update.", e);
            e.printStackTrace();
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
