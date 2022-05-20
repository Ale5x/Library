package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.LoanCardStatus;
import com.epam.library.entity.OrderStatus;
import com.epam.library.entity.UserStatus;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is an implementation of a {@link com.epam.library.controller.Command} interface and
 * is used to prepare the display of library statistics.
 *
 * @author Alexander Pishchala
 */

public class GoToStatisticLibraryCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToStatisticLibraryCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Getting statistics library");
            req.getSession().setAttribute(Constant.URL, CommandType.CONTROLLER_COMMAND + CommandType.GO_TO_STATISTICS);
            UserService userService = ServiceFactory.getInstance().getUserService();
            BookService bookService = ServiceFactory.getInstance().getBookService();
            LoanCardService loanCardService = ServiceFactory.getInstance().getLoanCardService();
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
            GenreService genreService = ServiceFactory.getInstance().getGenreService();

            long countBooks = bookService.getCountBooks();

            long countLoanCardOpen = loanCardService.showCountCards(LoanCardStatus.OPEN.name());
            long countLoanCardClosed = loanCardService.showCountCards(LoanCardStatus.CLOSED.name());
            long countAuthor = authorService.getCountAuthors();
            long countGenre = genreService.getCountGenres();

            long ordersOpen = orderService.showCountByStatus(OrderStatus.OPENED.name());
            long ordersApproved = orderService.showCountByStatus(OrderStatus.APPROVED.name());
            long ordersRejected = orderService.showCountByStatus(OrderStatus.REJECTED.name());
            long ordersArrived = orderService.showCountByStatus(OrderStatus.ARRIVED.name());
            long ordersClosed = orderService.showCountByStatus(OrderStatus.CLOSED.name());

            long usersBlocked = userService.showCountByStatus(UserStatus.BLOCKED.name());
            long usersActive = userService.showCountByStatus(UserStatus.ACTIVE.name());
            long usersDelete = userService.showCountByStatus(UserStatus.DELETE.name());
            long countUsers = usersActive + usersBlocked + usersDelete;

            req.setAttribute(Constant.STATISTIC_COUNT_USERS, countUsers);
            req.setAttribute(Constant.STATISTIC_COUNT_BOOKS, countBooks);

            req.setAttribute(Constant.STATISTIC_COUNT_USERS_BLOCKED, usersBlocked);
            req.setAttribute(Constant.STATISTIC_COUNT_USERS_DELETE, usersDelete);
            req.setAttribute(Constant.STATISTIC_COUNT_USERS_ACTIVE, usersActive);

            req.setAttribute(Constant.STATISTIC_COUNT_LOAN_CARD_OPEN, countLoanCardOpen);
            req.setAttribute(Constant.STATISTIC_COUNT_LOAN_CARD, countLoanCardClosed + countLoanCardOpen);

            req.setAttribute(Constant.STATISTIC_COUNT_AUTHORS, countAuthor);
            req.setAttribute(Constant.STATISTIC_COUNT_GENRES, countGenre);

            req.setAttribute(Constant.STATISTIC_COUNT_ORDERS_OPEN, ordersOpen);
            req.setAttribute(Constant.STATISTIC_COUNT_ORDERS_APPROVED, ordersApproved);
            req.setAttribute(Constant.STATISTIC_COUNT_ORDERS_REJECTED, ordersRejected);
            req.setAttribute(Constant.STATISTIC_COUNT_ORDERS_ARRIVED, ordersArrived);
            req.setAttribute(Constant.STATISTIC_COUNT_ORDERS_CLOSED, ordersClosed);

            req.getRequestDispatcher(PathJsp.STATISTICS_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while getting library statistics", e);
            resp.sendRedirect(CommandType.CONTROLLER_COMMAND + CommandType.ERROR_500);
        }
    }
}
