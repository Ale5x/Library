package com.epam.library.service.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoFactory;
import com.epam.library.dao.WishBookDtoDao;
import com.epam.library.entity.dto.WishBookDto;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.ServiceValidator;
import com.epam.library.service.WishBookDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class {@link WishBookDtoServiceImpl} is service class which provide operations the wish book
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public class WishBookDtoServiceImpl implements WishBookDtoService {

    private static final Logger logger = LoggerFactory.getLogger(WishBookDtoServiceImpl.class);

    @Override
    public List<WishBookDto> showBooksUser(String userId) throws ServiceException {
        try {
            WishBookDtoDao wishBookDtoDao = DaoFactory.getInstance().getWishBookDtoDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(userId)) {
                return wishBookDtoDao.getBooks(Long.parseLong(userId.trim()));
            }else {
                throw new ServiceException("Invalid type value ID");
            }
        }catch (DaoException e) {
            logger.error("Error in services when retrieving selected user books.");
            throw new ServiceException("Error in services when retrieving selected user books.", e);
        }
    }
}
