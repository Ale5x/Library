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
 * is used for actions with users.
 *
 * @author Alexander Pishchala
 */

public class ActionUserCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionUserCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.ACTION_USER);
            UserService userService = ServiceFactory.getInstance().getUserService();
            HttpSession session = req.getSession();
            String userId = req.getParameter(Constant.USER_ID);
            String addViolation = req.getParameter(Constant.USER_ADD_VIOLATION);
            String removeViolation = req.getParameter(Constant.USER_REMOVE_VIOLATION);
            String status = req.getParameter(Constant.STATUS);
            if (userId != null && status != null) {
                boolean resultOperation = userService.updateStatus(userId, status);
                if (resultOperation) {
                    session.setAttribute(Constant.MESSAGE_CODE_1013, Constant.MESSAGE_CODE_1013);
                }else {
                    session.setAttribute(Constant.MESSAGE_ERROR_CODE_1017, Constant.MESSAGE_ERROR_CODE_1017);
                }
                resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
            } else if (addViolation != null) {
                boolean resultOperation = userService.addViolation(userId);
                if (resultOperation) {
                    session.setAttribute(Constant.MESSAGE_CODE_1014, Constant.MESSAGE_CODE_1014);
                }else {
                    session.setAttribute(Constant.MESSAGE_ERROR_CODE_1018, Constant.MESSAGE_ERROR_CODE_1018);
                }
                resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
            } else if (removeViolation != null) {
                boolean resultOperation = userService.removeViolation(userId);
                if (resultOperation) {
                    session.setAttribute(Constant.MESSAGE_CODE_1015, Constant.MESSAGE_CODE_1015);
                }else {
                    session.setAttribute(Constant.MESSAGE_ERROR_CODE_1019, Constant.MESSAGE_ERROR_CODE_1019);
                }
                resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
            } else{
                req.getRequestDispatcher(PathJsp.USER_CATALOG_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error when changing user status.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
