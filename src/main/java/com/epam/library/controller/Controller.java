package com.epam.library.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class {@link Controller} is the main controller of the application.
 *
 * @author Alexander Pishchala
 */

public class Controller extends HttpServlet {

    private final CommandProvider provider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * The method is used to execute main control function in application. Depending
     * on the received parameter "command", the method send the request and response
     * to the corresponding resource.
     *
     *
     * @param req  an instance of {@link HttpServletRequest} that provides
     *                 request information for HTTP servlets.
     * @param resp an instance of {@link HttpServletResponse} that provides
     *                 HTTP-specificfunctionality in sending a response
     * @throws ServletException when servlet encounters difficulty
     * @throws IOException      when failed or interrupted I/O operations have been
     *                          occurred
     */
    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = provider.getCommand(req.getParameter(CommandType.COMMAND));

        if (command == null) {
            command = provider.getCommand(CommandType.ERROR_404);
        }
        command.execute(req, resp);
    }
}
