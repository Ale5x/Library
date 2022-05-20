package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
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
import java.util.Optional;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to update user data.
 *
 * @author Alexander Pishchala
 */

public class UpdateUserCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UpdateUserCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.UPDATE_USER);
            UserService userService = ServiceFactory.getInstance().getUserService();
            HttpSession session = req.getSession();
            String email = req.getParameter(Constant.USER_EMAIL);
            String firstName = req.getParameter(Constant.USER_FIRST_NAME);
            String lastName = req.getParameter(Constant.USER_LAST_NAME);
            String delete = req.getParameter(Constant.USERS_DELETE);
            User user = (User) session.getAttribute(Constant.USER);
            if (email != null && email != "" || firstName != null && firstName != ""
                    || lastName != null && lastName != "") {
                boolean resultOperation = userService.update(email, firstName, lastName, user.getUserId() + "");
                Optional<User> newDataUser = userService.showUserById(user.getUserId() + "");
                if (newDataUser.isPresent()) {
                    User newUser = new User();
                    newUser.setUserId(newDataUser.get().getUserId());
                    newUser.setRole(newDataUser.get().getRole());
                    newUser.setFirstName(newDataUser.get().getFirstName());
                    newUser.setLastName(newDataUser.get().getLastName());
                    newUser.setStatus(newDataUser.get().getStatus());
                    req.getSession().setAttribute(Constant.USER, newUser);
                } else {
                    session.invalidate();
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
                }
                if (resultOperation) {
                    session.setAttribute(Constant.MESSAGE_CODE_1019, Constant.MESSAGE_CODE_1019);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                } else {
                    session.setAttribute(Constant.MESSAGE_ERROR_CODE_1024, Constant.MESSAGE_ERROR_CODE_1024);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else if (delete != null) {
                userService.remove(user.getUserId() + "");
                session.invalidate();
                resp.sendRedirect(PathJsp.INDEX_PAGE);
            }else {
                req.getRequestDispatcher(PathJsp.UPDATE_USER_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("An error occurred while updating the user's personal data.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
