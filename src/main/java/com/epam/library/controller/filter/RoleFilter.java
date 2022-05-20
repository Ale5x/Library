package com.epam.library.controller.filter;

import com.epam.library.controller.CommandType;
import com.epam.library.controller.Constant;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.User;
import com.epam.library.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is an implementation of a {@link Filter} interface and
 * is used to check the user's access to the requested resource.
 *
 * @author Alexander Pishchala
 */

public class RoleFilter implements Filter {

    private final static Map<String, List<UserRole>> commandsUser = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        List<UserRole> userAdminManager = Arrays.asList(UserRole.USER, UserRole.ADMIN, UserRole.MANAGER);
        List<UserRole> adminManager = Arrays.asList(UserRole.ADMIN, UserRole.MANAGER);
        List<UserRole> manager = Arrays.asList(UserRole.MANAGER);
        List<UserRole> allUsers = Arrays.asList(UserRole.GUEST, UserRole.USER, UserRole.ADMIN, UserRole.MANAGER);

        commandsUser.put(CommandType.SIGN_IN, allUsers);
        commandsUser.put(CommandType.SEARCH_BOOKS, allUsers);
        commandsUser.put(CommandType.CHANGE_LOCALE, allUsers);
        commandsUser.put(CommandType.GO_TO_MESSAGE_PAGE, allUsers);
        commandsUser.put(CommandType.ERROR_404, allUsers);
        commandsUser.put(CommandType.ERROR_500, allUsers);

        commandsUser.put(CommandType.GO_TO_HOME, userAdminManager);
        commandsUser.put(CommandType.GO_TO_CATALOG, userAdminManager);
        commandsUser.put(CommandType.GO_TO_ORDER, userAdminManager);
        commandsUser.put(CommandType.GO_TO_UPDATE_ORDER, userAdminManager);
        commandsUser.put(CommandType.ORDER_BOOK, userAdminManager);
        commandsUser.put(CommandType.UPDATE_ORDER, userAdminManager);
        commandsUser.put(CommandType.SHOW_CATALOG_BY_PAGE, userAdminManager);
        commandsUser.put(CommandType.LOAN_CARD_USER, userAdminManager);
        commandsUser.put(CommandType.UPDATE_USER, userAdminManager);
        commandsUser.put(CommandType.UPDATE_USER_PASSWORD, userAdminManager);
        commandsUser.put(CommandType.ORDER_USER, userAdminManager);
        commandsUser.put(CommandType.WISH_BOOKS_USER_PAGE, userAdminManager);
        commandsUser.put(CommandType.ACTION_USER_ORDER, userAdminManager);
        commandsUser.put(CommandType.SIGN_OUT, userAdminManager);
        commandsUser.put(CommandType.ACTION_WISH_BOOK, userAdminManager);
        commandsUser.put(CommandType.GO_TO_CATALOG_BY_PAGE, userAdminManager);
        commandsUser.put(CommandType.GO_TO_BOOK_DETAILS_PAGE, userAdminManager);

        commandsUser.put(CommandType.MANAGER_CATALOG, manager);
        commandsUser.put(CommandType.ACTION_ADMIN_COMMAND, manager);
        commandsUser.put(CommandType.LIBRARY_CATALOG, manager);
        commandsUser.put(CommandType.GO_TO_UPDATE_LIBRARY, manager);
        commandsUser.put(CommandType.ACTION_LIBRARY, manager);
        commandsUser.put(CommandType.UPDATE_LIBRARY, manager);
        commandsUser.put(CommandType.CREATE_LIBRARY, manager);

        commandsUser.put(CommandType.ACTION_GIVE_OUT_BOOK, adminManager);
        commandsUser.put(CommandType.ACTION_RETURN_BOOK, adminManager);
        commandsUser.put(CommandType.GO_TO_ADMIN_PAGE, adminManager);
        commandsUser.put(CommandType.ADMIN_PAGE, adminManager);
        commandsUser.put(CommandType.GIVE_OUT_BOOK_USER, adminManager);
        commandsUser.put(CommandType.RETURN_BOOK_CATALOG, adminManager);
        commandsUser.put(CommandType.CATALOG_BOOK, adminManager);
        commandsUser.put(CommandType.GO_TO_UPDATE_BOOK, adminManager);
        commandsUser.put(CommandType.UPDATE_BOOK, adminManager);
        commandsUser.put(CommandType.CATALOG_AUTHOR, adminManager);
        commandsUser.put(CommandType.GO_TO_STATISTIC_AUTHOR, adminManager);
        commandsUser.put(CommandType.CATALOG_GENRE, adminManager);
        commandsUser.put(CommandType.GO_TO_STATISTIC_GENRE, adminManager);
        commandsUser.put(CommandType.LOAN_CARD_CATALOG, adminManager);
        commandsUser.put(CommandType.USER_CATALOG, adminManager);
        commandsUser.put(CommandType.ACTION_USER, adminManager);
        commandsUser.put(CommandType.ORDER_CATALOG, adminManager);
        commandsUser.put(CommandType.CREATE_BOOK, adminManager);
        commandsUser.put(CommandType.ACTION_ORDER, adminManager);
        commandsUser.put(CommandType.GO_TO_STATISTICS, adminManager);
        commandsUser.put(CommandType.CREATE_AUTHOR, adminManager);
        commandsUser.put(CommandType.CREATE_GENRE, adminManager);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String command = request.getParameter(CommandType.COMMAND);
        User user = (User) request.getSession().getAttribute(Constant.USER);

        if (user != null) {
            UserRole userRole = user.getRole();
            List<UserRole> roles = commandsUser.get(command);
            if (roles != null && !roles.contains(userRole)) {
                request.getRequestDispatcher(PathJsp.ERROR_404_PAGE).forward(request, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
