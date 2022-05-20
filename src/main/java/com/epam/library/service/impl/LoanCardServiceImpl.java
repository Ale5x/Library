package com.epam.library.service.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoFactory;
import com.epam.library.dao.LoanCardDao;
import com.epam.library.entity.*;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Class {@link LoanCardServiceImpl} is service class which provide operations an loan card
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public class LoanCardServiceImpl implements LoanCardService {

    private static final Logger logger = LoggerFactory.getLogger(LoanCardServiceImpl.class);

    private static final int MONTH_LOAN_BOOK = 2;

    @Override
    public boolean create(String orderId, String typeUse) throws ServiceException {
        try {
            LoanCardDao loanCardDao = DaoFactory.getInstance().getLoanCardDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            BookService bookService = ServiceFactory.getInstance().getBookService();
            Optional<Order> optionalOrder = orderService.showById(orderId);
            if (validator.isNumber(orderId)) {
                if (optionalOrder.isPresent()) {
                    if (typeUse.equalsIgnoreCase(BookTypeUse.READ_ROOM.name())
                            || typeUse.equalsIgnoreCase(BookTypeUse.TAKE_HOME.name())) {
                        Optional<Book> optionalBook = bookService.showBookById(optionalOrder.get().getBookId() + "");
                        if (optionalBook.isPresent() && optionalBook.get().getBorrow() < optionalBook.get().getQuantity()) {
                            LoanCard loanCard = new LoanCard();
                            loanCard.setBookId(optionalOrder.get().getBookId());
                            loanCard.setUserId(optionalOrder.get().getUserId());
                            loanCard.setCityLibrary(optionalOrder.get().getLibraryCity());
                            loanCard.setTypeUse(BookTypeUse.valueOf(typeUse.toUpperCase()));
                            loanCard.setTakingBook(LocalDate.now());
                            loanCard.setStatus(LoanCardStatus.OPEN);

                            LocalDate deadline = LocalDate.now();

                            loanCard.setDeadline(deadline.plusMonths(MONTH_LOAN_BOOK));
                            loanCard.setReturnBook("-");
                            return loanCardDao.create(loanCard);
                        } else {
                            return false;
                        }
                    } else {
                        throw new ServiceException("Invalid type use values");
                    }
                } else {
                    throw new ServiceException("Order out of order by ID.");
                }
            } else {
                throw new ServiceException("Invalid order ID values");
            }
        }catch (DaoException e) {
            logger.error("Error in services when creating an issue card.");
            throw new ServiceException("Error in services when creating an issue card.", e);
        }
    }

    @Override
    public boolean update(String cardId, String typeUse, String status) throws ServiceException {
        try {
            LoanCardDao loanCardDao = DaoFactory.getInstance().getLoanCardDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(cardId)) {
                Optional<LoanCard> optionalLoanCard = loanCardDao.getCardById(Long.parseLong(cardId.trim()));
                if (optionalLoanCard.isPresent()) {
                    LoanCard loanCard = optionalLoanCard.get();
                    if (typeUse != null) {
                        if (typeUse.equalsIgnoreCase(BookTypeUse.READ_ROOM.name())
                                || typeUse.equalsIgnoreCase(BookTypeUse.TAKE_HOME.name())) {
                            loanCard.setTypeUse(BookTypeUse.valueOf(typeUse.toUpperCase()));
                        } else {
                            throw new ServiceException("Invalid status values");
                        }
                    }
                    if (status != null) {
                        if (status.equalsIgnoreCase(LoanCardStatus.OPEN.name())
                                || status.equalsIgnoreCase(LoanCardStatus.CLOSED.name())) {
                            loanCard.setStatus(LoanCardStatus.valueOf(status.toUpperCase()));
                        } else {
                            throw new ServiceException("Invalid status values");
                        }
                    }
                    return loanCardDao.update(loanCard);
                }  else {
                    throw new ServiceException("The loan card ID value is empty.");
                }
                } else {
                    throw new ServiceException("The card does not exist.");
                }
        } catch (DaoException e) {
            logger.error("Error while updating the loan card.");
            throw new ServiceException("Error while updating the loan card.", e);
        }
    }

    @Override
    public boolean closeLoanCard(String cardId) throws ServiceException {
        try {
            LoanCardDao loanCardDao = DaoFactory.getInstance().getLoanCardDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            BookService bookService = ServiceFactory.getInstance().getBookService();
            if (validator.isNumber(cardId)) {
                Optional<LoanCard> optionalLoanCard = loanCardDao.getCardById(Long.parseLong(cardId.trim()));
                if (optionalLoanCard.isPresent()) {
                    if (optionalLoanCard.get().getStatus() == LoanCardStatus.OPEN) {
                        bookService.deleteBorrow(optionalLoanCard.get().getBookId() + "");
                        LoanCard loanCard = optionalLoanCard.get();
                        loanCard.setStatus(LoanCardStatus.CLOSED);
                        loanCard.setReturnBook(LocalDate.now().toString());
                        return loanCardDao.update(loanCard);
                    } else {
                        throw new ServiceException("Loan card closed.");
                    }
                } else {
                    throw new ServiceException("The card does not exist.");
                }
            } else {
                throw new ServiceException("The loan card ID value not valid.");
            }
        } catch (DaoException e) {
            logger.error("Error in services when closing the loan card.");
            throw new ServiceException("Error in services when closing the loan card.", e);
        }
    }

    @Override
    public boolean closeLoanCardWithViolation(String cardId) throws ServiceException {
        try {
            LoanCardDao loanCardDao = DaoFactory.getInstance().getLoanCardDao();
            UserService userService = ServiceFactory.getInstance().getUserService();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            BookService bookService = ServiceFactory.getInstance().getBookService();
            if (cardId != null) {
                if (validator.isNumber(cardId)) {
                    Optional<LoanCard> optionalLoanCard = loanCardDao.getCardById(Long.parseLong(cardId.trim()));
                    if (optionalLoanCard.isPresent()) {
                        if (optionalLoanCard.get().getStatus() == LoanCardStatus.OPEN) {
                            LoanCard loanCard = optionalLoanCard.get();

                            loanCard.setStatus(LoanCardStatus.CLOSED);
                            loanCard.setReturnBook(LocalDate.now().toString());
                            userService.addViolation(optionalLoanCard.get().getUserId() + "");
                            return loanCardDao.update(loanCard);
                        } else {
                            throw new ServiceException("Loan card closed.");
                        }
                    } else {
                        throw new ServiceException("The card does not exist.");
                    }
                }
            } else {
                throw new ServiceException("The loan card ID value is empty.");
            }
        } catch (DaoException e) {
            logger.error("Error when closing a card with a violation.");
            throw new ServiceException("Error when closing a card with a violation.", e);
        }
        return false;
    }

    @Override
    public long showCountCards(String status) throws ServiceException {
        try {
            LoanCardDao loanCardDao = DaoFactory.getInstance().getLoanCardDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (status != null) {
                if (validator.isLength(status)) {
                    if (status.equalsIgnoreCase(LoanCardStatus.OPEN.name())
                            || status.equalsIgnoreCase(LoanCardStatus.CLOSED.name())) {
                        return loanCardDao.getCountCardsByStatus(LoanCardStatus.valueOf(status.toUpperCase()));
                    } else {
                        throw new ServiceException("Invalid status values");
                    }
                }
            } else {
                throw new ServiceException("The status value is empty.");
            }
        }catch (DaoException e) {
            logger.error("Error in services when receiving the number of loan cards.");
            throw new ServiceException("Error in services when receiving the number of cards.", e);
        }
        return 0;
    }
}
