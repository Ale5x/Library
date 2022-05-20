package com.epam.library.service;

import com.epam.library.entity.Book;

import java.util.Optional;

/**
 * Interface {@link BookService} is service interface which provide operations a book
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public interface BookService {

    /**
     * The method is used to create the book.
     *
     * @param title    specified title of new book.
     * @param isbn    specified isbn of new book.
     * @param publisher    specified publisher of new book.
     * @param year    specified year of new book.
     * @param quantity    specified quantity of new book.
     * @param library    specified library of new book.
     * @param shelf    specified shelf of new book.
     * @param description    specified description of new book.
     * @return <tt>true</tt> if a book with specified parameter was created.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean create(String title, String isbn, String publisher, String year, String quantity,
                   String shelf, String description, String library) throws ServiceException;

    /**
     * The method is used to update the book.
     *
     * @param bookId    specified id of book.
     * @param book    specified entity of book.
     * @param quantity    specified quantity of book.
     * @param cityLibrary    specified city of library.
     * @return <tt>true</tt> if a book with specified parameter was updated.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean update(String bookId, Book book, String quantity, String cityLibrary) throws ServiceException;

    /**
     * The method is used to add borrowing for the book.
     *
     * @param bookId    specified id of book.
     * @return <tt>true</tt> if a book with specified parameter was updated.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean addBorrow(String bookId) throws ServiceException;

    /**
     * The method is used to remove borrowing  for the book.
     *
     * @param bookId    specified id of book.
     * @return <tt>true</tt> if a book with specified parameter was updated.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean deleteBorrow(String bookId) throws ServiceException;

    /**
     * The method is used to get the book by id.
     *
     * @param bookId    specified id of book.
     * @return Return book.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    Optional<Book> showBookById(String bookId) throws ServiceException;

    /**
     * The method is used to get the number of books.
     *
     * @return Returns the number of books in the system.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    long getCountBooks() throws ServiceException;
}
