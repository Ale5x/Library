package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to prepare for navigating to the home page.
 *
 * @author Alexander Pishchala
 */

public class GoToHomeCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_HOME);
        req.getRequestDispatcher(PathJsp.HOME_PAGE).forward(req, resp);
    }
}
