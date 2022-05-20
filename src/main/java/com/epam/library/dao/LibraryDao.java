package com.epam.library.dao;

import com.epam.library.entity.*;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@link LibraryDao} provides operation with data of database table 'libraries'.
 *
 * @author Alexander Pishchala
 */

public interface LibraryDao {

    /**
     * The method creates new record in database table.
     *
     * @param library entity that specifies creation of new records in database table.
     * @return <tt>true</tt> if new row in database table was created successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    boolean create(Library library) throws DaoException;

    /**
     * The method updates specified record in database table.
     *
     * @param library that will be updated in database table.
     * @return count row if row in database table corresponding to the entity was updated successfully.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    int update(Library library) throws DaoException;

    /**
     * The method returns specified Library by id.
     *
     * @param id Library by id.
     * @return specified Optional Library by id.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<Library> getLibraryById(long id) throws DaoException;

    /**
     * The method returns specified Library by city.
     *
     * @param city Library by city.
     * @return specified Optional Library by city.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    Optional<Library> getLibraryByCity(String city) throws DaoException;

    /**
     * The method will return list Libraries.
     *
     * @return Libraries count.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<Library> getLibraries() throws DaoException;

    /**
     * The method will return list Libraries by status.
     *
     * @param status library status.
     * @return Libraries count.
     * @throws DaoException if method has catched {@link java.sql.SQLException
     *                      SQLException}
     */
    List<Library> getLibrariesByStatus(LibraryStatus status) throws DaoException;

}
