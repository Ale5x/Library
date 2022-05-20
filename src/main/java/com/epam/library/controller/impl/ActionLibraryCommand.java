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
 * is used for actions with libraries.
 *
 * @author Alexander Pishchala
 */

public class ActionLibraryCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionLibraryCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.ACTION_LIBRARY);
            String libraryId = req.getParameter(Constant.LIBRARY_ID);
            String status = req.getParameter(Constant.STATUS);
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            if (libraryId != null && status != null) {
                boolean resultOperation = libraryService.updateStatus(libraryId, status);
                if (resultOperation) {
                    req.getSession().setAttribute(Constant.MESSAGE_CODE_1009, Constant.MESSAGE_CODE_1009);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                } else {
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1014, Constant.MESSAGE_ERROR_CODE_1014);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher(PathJsp.LIBRARY_CATALOG_PAGE).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("Error while changing the library status.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
