package com.epam.library.service;

/**
 * Interface {@link WishBookService} is service interface which provide operations the wish book
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public interface WishBookService {

    /**
     * The method is used to create the wish book.
     *
     * @param userId    specified id of user.
     * @param bookId    specified id of book.
     * @return <tt>true</tt> if a wish book with specified parameter was created.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean create(String userId, String bookId) throws ServiceException;

    /**
     * The method is used to remove the wish book.
     *
     * @param wishBookId    specified id of wish book.
     * @return <tt>true</tt> if a wish book with specified parameter was removed.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean delete(String wishBookId) throws ServiceException;

    /**
     * The method is used to get the number of wish book.
     *
     * @return Returns the number of wish book in the system.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    long showCountBooks() throws ServiceException;
}
