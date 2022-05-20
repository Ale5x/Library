package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;
import com.epam.library.entity.LoanCardStatus;
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
 * is used to display the book return catalog.
 *
 * @author Alexander Pishchala
 */

public class CatalogReturnBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(CatalogReturnBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.RETURN_BOOK_CATALOG);
            LoanCardDtoService loanCardDtoService = ServiceFactory.getInstance().getLoanCardDtoService();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<LoanCardDto> loanCards = new ArrayList<>();
            List<Library> libraries = libraryService.showByStatus(LibraryStatus.OPENED.name());

            String loanCardId = req.getParameter(Constant.LOAN_CARD_ID);
            String userId = req.getParameter(Constant.USER_ID);
            String bookId = req.getParameter(Constant.BOOK_ID);
            String library = req.getParameter(Constant.LIBRARY_CITY);
            String allOpenLoanCard = req.getParameter(Constant.RETURN_BOOK_ALL);
            if (loanCardId != null && loanCardId != "") {
                loanCards.add(loanCardDtoService.showCardsById(loanCardId).orElse(new LoanCardDto()));
            } else if (userId != null && userId != "") {
                loanCards = loanCardDtoService.showCardsByUser(userId);
            }else if (bookId != null && bookId != "") {
                loanCards = loanCardDtoService.showCardsByBook(bookId);
            } else if (library != null) {
                loanCards = loanCardDtoService.showCardsByCityAndStatus(library, LoanCardStatus.OPEN.name());
            } else if (allOpenLoanCard != null) {
                loanCards = loanCardDtoService.showCardsByStatus(LoanCardStatus.OPEN.name());
            }
            req.setAttribute(Constant.LIBRARIES, libraries);
            req.setAttribute(Constant.LOAN_CARDS, loanCards);
            req.setAttribute(Constant.LOAN_CARDS_SIZE, loanCards.size());
            req.getRequestDispatcher(PathJsp.RETURN_BOOK_PAGE).forward(req, resp);

        } catch (ServiceException e) {
            logger.error("Book return directory error.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
