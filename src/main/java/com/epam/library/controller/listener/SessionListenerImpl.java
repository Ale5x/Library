package com.epam.library.controller.listener;

import com.epam.library.controller.Constant;
import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;

import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * This class is an implementation of a {@link HttpSessionListener} interface and
 * is used to check the role of the user, if the user is not authorized, setting the value to 'Guest'.
 *
 * @author Alexander Pishchala
 */

public class SessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        HttpSession session = se.getSession();
        User user = (User) session.getAttribute(Constant.USER);
        if (user == null) {
            user = new User();
            user.setRole(UserRole.GUEST);
            session.setAttribute(Constant.USER, user);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
    }
}
