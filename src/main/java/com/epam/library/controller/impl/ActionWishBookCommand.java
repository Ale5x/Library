package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.entity.User;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.WishBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used for actions with favorite books.
 *
 * @author Alexander Pishchala
 */

public class ActionWishBookCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ActionWishBookCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.ACTION_WISH_BOOK);
            WishBookService wishBookService = ServiceFactory.getInstance().getWishBookService();
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute(Constant.USER);
            String bookId = req.getParameter(Constant.BOOK_ID);
            String actionAdd = req.getParameter(Constant.WISH_BOOK_ADD);
            String actionDelete = req.getParameter(Constant.WISH_BOOK_DELETE);
            String wishBookId = req.getParameter(Constant.WISH_BOOK_ID);
            if(bookId != null && actionAdd != null || actionDelete != null) {
                if (actionAdd != null) {
                    wishBookService.create(user.getUserId() + "", bookId);
                } else if (actionDelete != null){
                    wishBookService.delete(wishBookId);
                }
            }
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.WISH_BOOKS_USER_PAGE);
        }catch (ServiceException e) {
            logger.error("Error during action with selected books, when deleting or adding.", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
