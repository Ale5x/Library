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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to display the user directory.
 *
 * @author Alexander Pishchala
 */

public class UserCatalogCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(UserCatalogCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.USER_CATALOG);
            String showAll = req.getParameter(Constant.USER_ALL);
            String userRole = req.getParameter(Constant.USER_ROLE);
            String userStatus = req.getParameter(Constant.STATUS);
            String userId = req.getParameter(Constant.USER_ID);
            String email = req.getParameter(Constant.USER_EMAIL);
            UserService userService = ServiceFactory.getInstance().getUserService();
            List<User> users = new ArrayList<>();
            if (userRole !="" && userRole != null) {
               users = userService.showUserByRole(userRole);
            } else if (showAll != "" && showAll != null) {
                users = userService.getUsers();
            }else if (userId != "" && userId != null) {
                Optional<User> userOptional = Optional.of(userService.showUserById(userId).orElse(new User("-")));
                users.add(userOptional.get());
            }else if(email != "" && email != null) {
                users.addAll(userService.showUserByEmail(email));
            } else if (userStatus !="" && userStatus != null) {
                users.addAll(userService.showUserByStatus(userStatus));
            }
            req.setAttribute(Constant.USERS, users);
            req.setAttribute(Constant.USERS_SIZE, users.size());
            req.getRequestDispatcher(PathJsp.USER_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while working in the user directory.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
