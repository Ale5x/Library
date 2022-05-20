package com.epam.library.service;

import com.epam.library.entity.dto.WishBookDto;

import java.util.List;

/**
 * Interface {@link WishBookDtoService} is service interface which provide operations the wish book
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public interface WishBookDtoService {

    /**
     * The method is used to get the number of wish books by user.
     *
     * @param userId   specified id of user.
     * @return Returns the number of wish books in the system.
     * @throws ServiceException if method has catched
     *                          {@link com.epam.library.dao.DaoException
     *                          DaoException}
     */
    List<WishBookDto> showBooksUser(String userId) throws ServiceException;
}
