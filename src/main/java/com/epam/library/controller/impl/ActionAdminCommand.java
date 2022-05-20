package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used for actions with administrators.
 *
 * @author Alexander Pishchala
 */

public class ActionAdminCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionAdminCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.ACTION_ADMIN_COMMAND);
            UserService userService = ServiceFactory.getInstance().getUserService();
            HttpSession session = req.getSession();
            String userId = req.getParameter(Constant.USER_ID);
            String role = req.getParameter(Constant.USER_ROLE);

            if (userId != null && userId != "" && role != null && role != "") {
                boolean resultOperation = userService.updateRole(userId, role);
                if (resultOperation) {
                    session.setAttribute(Constant.MESSAGE_CODE_1007, Constant.MESSAGE_CODE_1007);
                } else {
                    session.setAttribute(Constant.MESSAGE_ERROR_CODE_1012, Constant.MESSAGE_ERROR_CODE_1012);
                }
                resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
            } else {
                req.getRequestDispatcher(PathJsp.MANAGER_CATALOG_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error while changing user role.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
