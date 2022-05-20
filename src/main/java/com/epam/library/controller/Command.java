package com.epam.library.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.IOException;

/**
 *
 * @author Alexander Pishchala
 *
 */

public interface Command {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
