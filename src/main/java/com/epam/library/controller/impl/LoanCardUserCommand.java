package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.service.LoanCardDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to view the user's issuing card history.
 *
 * @author Alexander Pishchala
 */

public class LoanCardUserCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(LoanCardUserCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.LOAN_CARD_USER);
            LoanCardDtoService loanCardDtoService = ServiceFactory.getInstance().getLoanCardDtoService();
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute(Constant.USER);
            List<LoanCardDto> cardsList;
            if (user != null) {
                cardsList = loanCardDtoService.showCardsByUser(user.getUserId() + "");
                req.setAttribute(Constant.LOAN_CARDS, cardsList);
                req.getRequestDispatcher(PathJsp.USER_LOAN_CARD).forward(req, resp);
            } else {
                resp.sendRedirect(PathJsp.INDEX_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("Error while retrieving the history of borrowed books by the user.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
