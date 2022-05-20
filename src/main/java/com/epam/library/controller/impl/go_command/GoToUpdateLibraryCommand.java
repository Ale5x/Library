package com.epam.library.controller.impl.go_command;

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

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to prepare the display for updating the library.
 *
 * @author Alexander Pishchala
 */

public class GoToUpdateLibraryCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToUpdateLibraryCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            String libraryId = req.getParameter(Constant.LIBRARY_ID);
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND +
                    CommandType.GO_TO_UPDATE_LIBRARY + "&" + Constant.LIBRARY_ID + "=" + libraryId);
            if (libraryId != null) {
                Library library = libraryService.showById(libraryId).orElse(new Library("-", "-"));
                req.getSession().setAttribute(Constant.UPDATE_LIBRARY_ID, library.getLibraryId());
                req.getSession().setAttribute(Constant.LIBRARY, library);
                req.getRequestDispatcher(PathJsp.UPDATE_LIBRARY_PAGE).forward(req, resp);
            } else {
                req.getRequestDispatcher(CommandType.LIBRARY_CATALOG).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("An error occurred while preparing the library for updating.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
