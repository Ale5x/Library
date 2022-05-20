package com.epam.library.dao;

/**
 * The class {@code DaoException} is subclass of {@code Exception}. Class
 * represent an exception that may occur on a Dao layer of the application
 *
 * @author Alexander Pishchala
 *
 */

public class DaoException extends RuntimeException {

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    protected DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
