package com.epam.library.dao;

import com.epam.library.entity.dto.BookDto;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@link BookDtoDao} provides operation with data of database tables 'books', 'authors', 'genres'.
 *
 * @author Alexander Pishchala
 */

public interface BookDtoDao {

    /**
     * The method creates new record in database table.
     *
     * @param bookDto entity that specifies creation of new records in database table.
     * @return <tt>true</tt> if new row in database table was created successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean create(BookDto bookDto) throws DaoException;

    /**
     * The method updates specified record in database table.
     *
     * @param bookDto that will be updated in database table.
     * @return <tt>true</tt> if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean update(BookDto bookDto) throws DaoException;

    /**
     * The method will return list Books Dto.
     *
     * @param isbn book ISBN.
     * @return list of books.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<BookDto> getBookByIsbn(String isbn) throws DaoException;

    /**
     * The method returns specified Book by id.
     *
     * @param bookId Book by id.
     * @return specified Optional Book by id.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<BookDto> getBookById(long bookId) throws DaoException;

    /**
     * The method will return list Books.
     *
     * @return list of books.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<BookDto> getBooks() throws DaoException;

    /**
     * The method returns specified Book by library.
     *
     * @param city Books of the library.
     * @return list of books.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<BookDto> getBooksByCity(String city) throws DaoException;

    /**
     * The method returns specified Book by page.
     *
     * @param limit Number of books per page.
     * @param page page.
     * @return list of books.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<BookDto> getBooksByPage(int limit, int page) throws DaoException;

    /**
     * The method returns specified Book by title.
     *
     * @param title title of the book.
     * @return list of books.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<BookDto> getBooksByTitle(String title) throws DaoException;

    /**
     * The method returns specified Book by author.
     *
     * @param author book author.
     * @return list of books.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<BookDto> getBooksByAuthor(String author) throws DaoException;

    /**
     * The method returns specified Book by genre.
     *
     * @param genre book genre.
     * @return list of books.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<BookDto> getBooksByGenre(String genre) throws DaoException;

}
