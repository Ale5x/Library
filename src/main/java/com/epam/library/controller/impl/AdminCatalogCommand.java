package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;
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

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to display the admin directory.
 *
 * @author Alexander Pishchala
 */

public class AdminCatalogCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(AdminCatalogCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.MANAGER_CATALOG);
            UserService userService = ServiceFactory.getInstance().getUserService();
            String userIdFind = req.getParameter(Constant.USER_ID);
            String email = req.getParameter(Constant.USER_EMAIL);
            String admins = req.getParameter(Constant.USER_ALL_ADMIN);
            List<User> users = new ArrayList<>();
            if (userIdFind != null && userIdFind != "") {
               users.add(userService.showUserById(userIdFind).get());
            } else if (email != null && email != "") {
                users.addAll(userService.showUserByEmail(email));
            } else if (admins != null && admins != "") {
                users.addAll(userService.showUserByRole(UserRole.ADMIN.name()));
                users.addAll(userService.showUserByRole(UserRole.MANAGER.name()));
            }
            req.setAttribute(Constant.USERS, users);
            req.setAttribute(Constant.USERS_SIZE, users.size());
            req.getRequestDispatcher(PathJsp.MANAGER_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while searching for administrators.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
