package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
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
 * is used to update library data.
 *
 * @author Alexander Pishchala
 */

public class UpdateLibraryCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UpdateLibraryCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.UPDATE_LIBRARY);
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            Integer libraryId = (Integer) req.getSession().getAttribute(Constant.UPDATE_LIBRARY_ID);
            String city = req.getParameter(Constant.LIBRARY_CITY);
            String street = req.getParameter(Constant.LIBRARY_STREET);
            req.getSession().removeAttribute(Constant.UPDATE_LIBRARY_ID);
            if (libraryId != null && city != null && street != null) {
                boolean resultOperation = libraryService.update(libraryId + "", city, street);
                if (resultOperation) {
                    req.getSession().setAttribute(Constant.MESSAGE_CODE_1018, Constant.MESSAGE_CODE_1018);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }  else {
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1022, Constant.MESSAGE_ERROR_CODE_1022);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher(PathJsp.LIBRARY_CATALOG_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error while updating the library.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
