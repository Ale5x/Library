package com.epam.library.service.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoFactory;
import com.epam.library.dao.WishBookDao;
import com.epam.library.entity.WishBook;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.ServiceValidator;
import com.epam.library.service.WishBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Class {@link WishBookService} is service class which provide operations the wish book
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public class WishBookServiceImpl implements WishBookService {

    private static final Logger logger = LoggerFactory.getLogger(WishBookServiceImpl.class);

    @Override
    public boolean create(String userId, String bookId) throws ServiceException {
        try {
            WishBookDao wishBookDao = DaoFactory.getInstance().getWishBookDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(userId) && validator.isNumber(bookId)) {
                Optional<WishBook> optionalWishBook = wishBookDao
                        .getWishBookByBookAndByUser(Long.parseLong(bookId.trim()), Long.parseLong(userId.trim()));
                if (optionalWishBook.isEmpty()) {
                    WishBook wishBook = new WishBook();
                    wishBook.setUserId(Long.parseLong(userId.trim()));
                    wishBook.setBookId(Long.parseLong(bookId.trim()));
                    wishBookDao.create(wishBook);
                }
            } else {
                throw new ServiceException("Invalid ID values.");
            }
        } catch (DaoException e) {
            logger.error("Error adding book to favorites.");
            throw new ServiceException("Error adding book to favorites.", e);
        }
        return true;
    }

    @Override
    public boolean delete(String wishBookId) throws ServiceException {
        try {
            WishBookDao wishBookDao = DaoFactory.getInstance().getWishBookDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(wishBookId)) {
                Optional<WishBook> optionalWishBook = wishBookDao.getWishBookById(Long.parseLong(wishBookId.trim()));
                if (optionalWishBook.isPresent()) {
                    int result = wishBookDao.delete(Long.parseLong(wishBookId.trim()));
                    if (result == 1) {
                        return true;
                    }
                }
            } else {
                throw new ServiceException("Invalid ID value.");
            }
        } catch (DaoException e) {
            logger.error("Error deleting book from favorites.");
            throw new ServiceException("Error deleting book from favorites.", e);
        }
        return false;
    }

    @Override
    public long showCountBooks() throws ServiceException {
        try {
            WishBookDao wishBookDao = DaoFactory.getInstance().getWishBookDao();
            return wishBookDao.getCountWishBooks();
        }catch (DaoException e) {
            logger.error("Error in services when getting the number of favorites.");
            throw new ServiceException("Error in services when getting the number of favorites.", e);
        }
    }
}
