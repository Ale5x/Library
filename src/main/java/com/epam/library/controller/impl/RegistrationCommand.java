package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to register a new user.
 *
 * @author Alexander Pishchala
 */

public class RegistrationCommand implements Command {

    private final static Logger logger = LoggerFactory.getLogger(RegistrationCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            logger.info("Start registration.");
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.REGISTRATION);
            UserService userService = ServiceFactory.getInstance().getUserService();
            HttpSession session = req.getSession();
            String email = req.getParameter(Constant.USER_EMAIL);
            String password = req.getParameter(Constant.USER_PASSWORD);
            String firstName = req.getParameter(Constant.USER_FIRST_NAME);
            String lastName = req.getParameter(Constant.USER_LAST_NAME);
            if (email != null && email != "" && password != null && password != ""
                    && firstName != null && firstName != "" && lastName != null && lastName != ""){
                if (userService.isFreeEmail(email)) {
                    boolean flagRegistration = userService.create(email, password, firstName, lastName);
                    if(flagRegistration) {
                        Optional<User> optionalUser = userService.verification(email, password);
                        User user = new User();
                        user.setUserId(optionalUser.get().getUserId());
                        user.setRole(optionalUser.get().getRole());
                        user.setFirstName(optionalUser.get().getFirstName());
                        user.setLastName(optionalUser.get().getLastName());
                        user.setStatus(optionalUser.get().getStatus());
                        session.setAttribute(Constant.USER, user);
                        req.getRequestDispatcher(PathJsp.INDEX_PAGE).forward(req, resp);
                    } else  {
                        session.setAttribute(Constant.MESSAGE_ERROR_CODE_1003, Constant.MESSAGE_ERROR_CODE_1003);
                        resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                    }
                } else {
                    session.setAttribute(Constant.MESSAGE_ERROR_CODE_1001, Constant.MESSAGE_ERROR_CODE_1001);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else {
                req.getRequestDispatcher(PathJsp.REGISTRATION_PAGE).forward(req, resp);
            }
        } catch (ServiceException e) {
            logger.error("An error occured during registration. ", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
