package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Library;
import com.epam.library.service.LibraryService;
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
 * is used to browse the library directory.
 *
 * @author Alexander Pishchala
 */

public class LibraryCatalogCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(LibraryCatalogCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.LIBRARY_CATALOG);
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            String libraryId = req.getParameter(Constant.LIBRARY_ID);
            String city = req.getParameter(Constant.LIBRARY_CITY);
            String status = req.getParameter(Constant.STATUS);
            String getAll = req.getParameter(Constant.LIBRARY_ALL);
            List<Library> libraries = new ArrayList<>();
            if (libraryId != null) {
                libraries.add(libraryService.showById(libraryId).get());
            } else if (city != null) {
                libraries.add(libraryService.showByCity(city).get());
            } else if (status != null) {
                libraries = libraryService.showByStatus(status);
            } else if (getAll != null) {
                libraries = libraryService.showAll();
            }
            req.setAttribute(Constant.LIBRARIES, libraries);
            req.getRequestDispatcher(PathJsp.LIBRARY_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while working with the library catalog.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
