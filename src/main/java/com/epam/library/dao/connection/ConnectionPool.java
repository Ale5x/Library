package com.epam.library.dao.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *
 * @author Alexander Pishchala
 *
 */

public enum ConnectionPool {
    INSTANCE;

    private final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> usedConnections;

    private static final String pathDateBaseProperties = "database.properties";
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private int DEFAULT_POOL_SIZE = 5;
    private int poolSize;

    ConnectionPool() {
        logger.info("Load properties for connection.");
        String driverName = null;
        try  {
            ResourceBundle bundle = ResourceBundle.getBundle("database");

            driverName = bundle.getString("driverDatabase");
            URL = bundle.getString("url");
            USER = bundle.getString("userName");
            PASSWORD = bundle.getString("password");
            poolSize = bundle.getString("poolSize") != null
                    ? Integer.parseInt(bundle.getString("poolSize")) : DEFAULT_POOL_SIZE;
            Class.forName(driverName);

        } catch (ClassNotFoundException e) {
            logger.error("Class not found.");
            throw new ConnectionException("Class not found: " + pathDateBaseProperties, e);
        }
        logger.info("Connection settings upload complete");
        freeConnections = new LinkedBlockingDeque<>(poolSize);
        usedConnections = new ArrayDeque<>();
        createPool();
    }

    private void createPool() {
        logger.info("Create connection pool.");
        for(int i = 0; i < poolSize; i++) {
            try {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                ProxyConnection proxy = new ProxyConnection(connection);
                freeConnections.offer(proxy);
            } catch (SQLException e) {
                logger.error("Error while creating Connection Pool.");
                throw new ConnectionException(e);
            }
        }
    }

    public Connection getConnection() throws ConnectionException {
        logger.info("Create connection.");
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            usedConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error("Unable to establish connection.");
            throw new ConnectionException("Unable to establish connection.", e);
        }
        logger.info("Creating a connection");
        return new ProxyConnection(connection);
    }


    public void releaseConnection(Connection connection) {
        logger.info("Check release connection start.");
        if (connection instanceof ProxyConnection) {
            usedConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.error("Wrong connection is detected");
            throw new ConnectionException("Wrong connection is detected:" + connection.getClass() +
                    "should be ProxyConnection.class ");
        }
        logger.info("Check release connection finish.");
    }

    public void destroyPool() {
        logger.info("Start destruction of the pool.");
        for (int i = 0; i < poolSize; i++) {
            try {
                freeConnections.take().reallyCloseConnection();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Something wrong with current thread", e);
            } catch (SQLException sqlE) {
                logger.error("Exception in connection close method", sqlE);
            }
            deregisterDrivers();
            logger.info("End of pool destruction.");
        }

    }

    private void deregisterDrivers() {
        logger.info("Start deregister Drivers.");
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException sqlE) {
                logger.error("Driver deregistration did not occur.", sqlE);
            }
        }
        logger.info("Finish deregister Drivers.");
    }
}
