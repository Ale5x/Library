package com.epam.library.controller.listener;

import com.epam.library.dao.connection.ConnectionPool;

import javax.servlet.Filter;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * This class is an implementation of a {@link ServletContextListener} interface and
 * is used to create a connection pool and destroy the pool if there are no active users.
 *
 * @author Alexander Pishchala
 */

public class ContextListenerImpl implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.INSTANCE.destroyPool();
    }
}
