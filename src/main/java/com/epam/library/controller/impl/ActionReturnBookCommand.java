package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.service.LoanCardService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used for book return activities.
 *
 * @author Alexander Pishchala
 */

public class ActionReturnBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionReturnBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.ACTION_RETURN_BOOK);
            String loanCardId = req.getParameter(Constant.LOAN_CARD_ID);
            String violation = req.getParameter(Constant.RETURN_BOOK_WITH_VIOLATION);
            LoanCardService loanCardService = ServiceFactory.getInstance().getLoanCardService();
            if (loanCardId != null) {
                boolean resultOperation = loanCardService.closeLoanCard(loanCardId);
                if (resultOperation) {
                    req.getSession().setAttribute(Constant.MESSAGE_CODE_1012, Constant.MESSAGE_CODE_1012);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                } else {
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1017, Constant.MESSAGE_ERROR_CODE_1017);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            } else if (violation != null) {
                boolean resultOperation = loanCardService.closeLoanCardWithViolation(loanCardId);
                if (resultOperation) {
                    req.getSession().setAttribute(Constant.MESSAGE_CODE_1011, Constant.MESSAGE_CODE_1011);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                } else {
                    req.getSession().setAttribute(Constant.MESSAGE_ERROR_CODE_1016, Constant.MESSAGE_ERROR_CODE_1016);
                    resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_MESSAGE_PAGE);
                }
            }else {
                req.getRequestDispatcher(PathJsp.RETURN_BOOK_PAGE).forward(req, resp);
            }
        }catch (ServiceException e) {
            logger.error("Error closing the issue book.", e);
            e.printStackTrace();
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
