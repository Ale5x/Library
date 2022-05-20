package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to display a catalog of book lending cards.
 *
 * @author Alexander Pishchala
 */

public class CatalogLoanCardCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(CatalogLoanCardCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.LOAN_CARD_CATALOG);
            LoanCardDtoService loanCardService = ServiceFactory.getInstance().getLoanCardDtoService();
            List<LoanCardDto> loanCards = new ArrayList<>();
            String userId = req.getParameter(Constant.USER_ID);
            String bookId = req.getParameter(Constant.BOOK_ID);
            String city = req.getParameter(Constant.LIBRARY_CITY);
            String all = req.getParameter(Constant.LOAN_ALL);
            String cardId = req.getParameter(Constant.LOAN_CARD_ID);
            String status = req. getParameter(Constant.STATUS);
            if (userId != null && userId != "") {
                loanCards = loanCardService.showCardsByUser(userId);
            } else if (bookId != null && bookId != "") {
                loanCards = loanCardService.showCardsByBook(bookId);
            } else if (city != null && status == null) {
                loanCards = loanCardService.showCardsByCity(city);
            } else if (all != null) {
                loanCards = loanCardService.showAll();
            } else if (cardId != null && cardId != "") {
                loanCards.add(loanCardService.showCardsById(cardId).orElse(new LoanCardDto()));
            } else if (status != null && city == null) {
                loanCards = loanCardService.showCardsByStatus(status);
            } else if (status != null && city != null) {
                loanCards = loanCardService.showCardsByCityAndStatus(city, status);
            }
            req.setAttribute(Constant.LOAN_CARDS, loanCards);
            req.setAttribute(Constant.LOAN_CARDS_SIZE, loanCards.size());
            req.getRequestDispatcher(PathJsp.LOAN_CARD_CATALOG_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error working with the loan cards directory.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
