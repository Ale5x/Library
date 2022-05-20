package com.epam.library.service.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoFactory;
import com.epam.library.dao.LoanCardDtoDao;
import com.epam.library.entity.Library;
import com.epam.library.entity.LoanCardStatus;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Class {@link LoanCardDtoServiceImpl} is service class which provide operations a loan card
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public class LoanCardDtoServiceImpl implements LoanCardDtoService {

    private static final Logger logger = LoggerFactory.getLogger(LoanCardDtoServiceImpl.class);

    @Override
    public List<LoanCardDto> showCardsByStatus(String status) throws ServiceException {
        try {
            LoanCardDtoDao loanCardDtoDao = DaoFactory.getInstance().getLoanCardDtoDao();
            if (status.equalsIgnoreCase(LoanCardStatus.OPEN.name())
                    || status.equalsIgnoreCase(LoanCardStatus.CLOSED.name())) {
                return loanCardDtoDao.getCardsByStatusCard(LoanCardStatus.valueOf(status.toUpperCase()));
            } else {
                throw new ServiceException("Invalid status values");
            }
        } catch (DaoException e) {
            logger.error("Error in services when receiving loan cards by status.");
            throw new ServiceException("Error in services when receiving loan cards by status.", e);
        }
    }

    @Override
    public List<LoanCardDto> showCardsByUser(String userId) throws ServiceException {
        try {
            LoanCardDtoDao loanCardDtoDao = DaoFactory.getInstance().getLoanCardDtoDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(userId)) {
                return loanCardDtoDao.getCardsByIdUser(Long.parseLong(userId));
            } else {
                throw new ServiceException("Invalid user ID value.");
            }
        } catch (DaoException e) {
            logger.error("Error in services when receiving loan cards by user ID.");
            throw new ServiceException("Error in services when receiving loan cards by user ID.", e);
        }
    }

    @Override
    public List<LoanCardDto> showCardsByBook(String bookId) throws ServiceException {
        try {
            LoanCardDtoDao loanCardDtoDao = DaoFactory.getInstance().getLoanCardDtoDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(bookId)) {
                return loanCardDtoDao.getCardsByIdBook(Long.parseLong(bookId));
            } else {
                throw new ServiceException("Invalid book ID value.");
            }
        } catch (DaoException e) {
            logger.error("Error in services when receiving loan cards by book ID.");
            throw new ServiceException("Error in services when receiving loan cards by book ID.", e);
        }
    }

    @Override
    public List<LoanCardDto> showCardsByCityAndStatus(String city, String status) throws ServiceException {
        try {
            LoanCardDtoDao loanCardDtoDao = DaoFactory.getInstance().getLoanCardDtoDao();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            Optional<Library> optionalLibrary = libraryService.showByCity(city);
            if (optionalLibrary.isPresent()) {
                if (status.equalsIgnoreCase(LoanCardStatus.OPEN.name())
                        || status.equalsIgnoreCase(LoanCardStatus.CLOSED.name())) {
                    return loanCardDtoDao.getCardsByCityAndStatus(optionalLibrary.get().getCity(),
                            LoanCardStatus.valueOf(status.toUpperCase()));
                } else {
                    throw new ServiceException("Invalid status values");
                }
            } else {
                throw new ServiceException("The city does not exist.");
            }
        } catch (DaoException e) {
            logger.error("Error in services when receiving loan cards by city and status.");
            throw new ServiceException("Error in services when receiving loan cards by city and status.", e);
        }
    }

    @Override
    public List<LoanCardDto> showCardsByCity(String city) throws ServiceException {
        try {
            LoanCardDtoDao loanCardDtoDao = DaoFactory.getInstance().getLoanCardDtoDao();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            Optional<Library> optionalLibrary = libraryService.showByCity(city);
            if (optionalLibrary.isPresent()) {
                return loanCardDtoDao.getCardsByCity(optionalLibrary.get().getCity());
            } else {
                throw new ServiceException("The city does not exist.");
            }
        } catch (DaoException e) {
            logger.error("Error in services when receiving loan cards by city.");
            throw new ServiceException("Error in services when receiving loan cards by city.", e);
        }
    }

    @Override
    public List<LoanCardDto> showAll() throws ServiceException {
        try {
            LoanCardDtoDao loanCardDtoDao = DaoFactory.getInstance().getLoanCardDtoDao();
            return loanCardDtoDao.getAll();
        } catch (DaoException e) {
            logger.error("Services error while getting all loan cards.");
            throw new ServiceException("Services error while getting all loan cards.", e);
        }
    }

    @Override
    public Optional<LoanCardDto> showCardsById(String loanCardId) throws ServiceException {
        try {
            LoanCardDtoDao loanCardDtoDao = DaoFactory.getInstance().getLoanCardDtoDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(loanCardId)) {
                return loanCardDtoDao.getCardsById(Long.parseLong(loanCardId.trim()));
            } else {
                throw new ServiceException("Invalid loan card ID value");
            }
        } catch (DaoException e) {
            logger.error("Services error while getting all loan cards.");
            throw new ServiceException("Services error while getting all loan cards.", e);
        }
    }
}
