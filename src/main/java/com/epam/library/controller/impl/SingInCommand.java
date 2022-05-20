package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
import com.epam.library.service.ServiceException;
import com.epam.library.service.UserService;
import com.epam.library.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Optional;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to verify the user in the system.
 *
 * @author Alexander Pishchala
 */

public class SingInCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(SingInCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.SIGN_IN);
            UserService userService = new UserServiceImpl();
            HttpSession session = req.getSession();
            String email = req.getParameter(Constant.USER_EMAIL);
            String password = req.getParameter(Constant.USER_PASSWORD);
            if (email != "" && email != null && password != "" && password != null) {
                Optional<User> optionalUser = userService.verification(email, password);
                if (optionalUser.isPresent()) {
                    User user = new User();
                    user.setUserId(optionalUser.get().getUserId());
                    user.setRole(optionalUser.get().getRole());
                    user.setFirstName(optionalUser.get().getFirstName());
                    user.setLastName(optionalUser.get().getLastName());
                    user.setStatus(optionalUser.get().getStatus());
                    session.setAttribute(Constant.USER, user);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_HOME);
                } else {
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1023, Constant.MESSAGE_ERROR_CODE_1023);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                resp.sendRedirect(PathJsp.INDEX_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("Verification error.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
