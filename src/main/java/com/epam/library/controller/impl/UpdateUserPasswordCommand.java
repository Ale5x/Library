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
 * is used to update the user's password.
 *
 * @author Alexander Pishchala
 */

public class UpdateUserPasswordCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UpdateUserPasswordCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.UPDATE_USER_PASSWORD);
            UserService userService = ServiceFactory.getInstance().getUserService();
            HttpSession session = req.getSession();
            String email = req.getParameter(Constant.USER_EMAIL);
            String oldPassword = req.getParameter(Constant.USER_OLD_PASSWORD);
            String newPassword = req.getParameter(Constant.USER_NEW_PASSWORD);
            if (email != "" && email != null && oldPassword != "" && oldPassword !=null
                    && newPassword != "" && newPassword != null) {
                boolean resultOperation = userService.updatePassword(newPassword, email, oldPassword);
                if (resultOperation) {
                    session.setAttribute(Constant.MESSAGE_CODE_1001, Constant.MESSAGE_CODE_1001);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                } else {
                    session.setAttribute(Constant.MESSAGE_ERROR_CODE_1005, Constant.MESSAGE_ERROR_CODE_1005);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher(PathJsp.UPDATE_USER_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error during password update.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
