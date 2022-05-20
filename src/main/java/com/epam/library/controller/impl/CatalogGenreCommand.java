package com.epam.library.controller.impl;

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
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to display the catalog of genres.
 *
 * @author Alexander Pishchala
 */

public class CatalogGenreCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(CatalogGenreCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.CATALOG_GENRE);
            GenreService genreService = ServiceFactory.getInstance().getGenreService();
            String genreId = req.getParameter(Constant.GENRE_ID);
            String category = req.getParameter(Constant.GENRE_NAME);
            String allGenre = req.getParameter(Constant.GENRE_ALL);
            List<Genre> genres = new ArrayList<>();
            if(category != null && category != "" || genreId != null && genreId != "" || allGenre != null) {
                if (category != null) {
                    genres.add(genreService.showGenreByCategory(category).orElse(new Genre()));
                } else if (genreId != null) {
                    genres.add(genreService.showGenreById(genreId).orElse(new Genre("-")));
                } else if (allGenre != null) {
                    genres = genreService.getGenres();
                }
                req.setAttribute(Constant.GENRES, genres);
                req.setAttribute(Constant.GENRES_SIZE, genres.size());
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            } else {
                req.getRequestDispatcher(PathJsp.BOOK_CATALOG_PAGE).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("Error when working in the genres directory.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
