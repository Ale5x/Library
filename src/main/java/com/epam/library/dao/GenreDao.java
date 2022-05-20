package com.epam.library.dao;

import com.epam.library.entity.Genre;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@link GenreDao} provides operation with data of database table 'genres'.
 *
 * @author Alexander Pishchala
 */

public interface GenreDao {

    /**
     * The method creates new record in database table.
     *
     * @param genre entity that specifies creation of new records in database table.
     * @return <tt>true</tt> if new row in database table was created successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean create(Genre genre) throws DaoException;

    /**
     * The method updates specified record in database table.
     *
     * @param genre that will be updated in database table.
     * @return <tt>true</tt> if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean update(Genre genre) throws DaoException;

    /**
     * The method returns specified Genre by id.
     *
     * @param genreId Genre by id.
     * @return specified Optional Genre by id.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<Genre> getGenreById(long genreId) throws DaoException;

    /**
     * The method returns specified Genre by genre.
     *
     * @param genre Genre by genre.
     * @return specified Optional Genre by genre.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<Genre> getGenreByGenre(String genre) throws DaoException;

    /**
     * The method deletes specified record in database table.
     *
     * @param bookId that will be deleted in database table.
     * @return <tt>true</tt> if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean deleteGenreByBookId(long bookId) throws DaoException;

    /**
     * The method will return list Genres.
     *
     * @return list of genres.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<Genre> getGenres() throws DaoException;

    /**
     * The method will return the number of genres.
     *
     * @return Genres count.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    long getCount() throws DaoException;

    /**
     * The method will return the number of books by the genre id.
     *
     * @param genreId entity id.
     * @return Books count.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    long getCountBookByIdGenre(int genreId) throws DaoException;

}
