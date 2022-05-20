package com.epam.library.service;

import com.epam.library.entity.dto.BookDto;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@link BookDtoService} is service interface which provide operations a book
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public interface BookDtoService {

    /**
     * The method is used to create the book.
     *
     * @param title    specified title of new book.
     * @param isbn    specified isbn of new book.
     * @param publisher    specified publisher of new book.
     * @param year    specified year of new book.
     * @param count    specified count of new book.
     * @param city    specified city of new book.
     * @param shelf    specified shelf of new book.
     * @param author    specified author of new book.
     * @param category    specified category of new book.
     * @param description    specified description of new book.
     * @return <tt>true</tt> if book with specified parameter was created.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean create(String title, String isbn, String publisher, String year, String count, String city,
                   String shelf, String author, String category, String description) throws ServiceException;

    /**
     * The method is used to update the book.
     *
     * @param bookId    specified id of book.
     * @param bookDto    specified entity of book.
     * @param quantity    specified quantity of book.
     * @param author    specified author of new book.
     * @return <tt>true</tt> if book with specified parameter was updated.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    boolean update(String bookId, BookDto bookDto, String author, String genre, String quantity) throws ServiceException;

    /**
     * The method is used to get books.
     *
     * @return list of books.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<BookDto> showCatalog() throws ServiceException;

    /**
     * The method is used to get the book by id.
     *
     * @param bookId    specified id of book.
     * @return Return book.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    Optional<BookDto> showBookById(String bookId) throws ServiceException;

    /**
     * The method is used to get books by city.
     *
     * @param city    specified city of library.
     * @return list of books.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<BookDto> showBookByCity(String city) throws ServiceException;

    /**
     * The method is used to get books.
     *
     * @param title    specified title of book.
     * @param isbn    specified isbn of book.
     * @param genre    specified genre of book.
     * @param author    specified author of book.
     * @return list of books.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<BookDto> showBookByParameter(String title, String isbn, String genre, String author) throws ServiceException;

    /**
     * The method is used to get books by page.
     *
     * @param page    specified page of catalog.
     * @param limit    specified limit books by page.
     * @return list of books.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<BookDto> showByPage(int page, int limit) throws ServiceException;
}
