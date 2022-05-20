package com.epam.library.controller;

import com.epam.library.controller.impl.*;
import com.epam.library.controller.impl.go_command.*;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alexander Pishchala
 *
 */

public final class CommandProvider {

    private  final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandType.CATALOG_AUTHOR, new CatalogAuthorCommand());
        commands.put(CommandType.CATALOG_GENRE, new CatalogGenreCommand());
        commands.put(CommandType.ACTION_GIVE_OUT_BOOK, new ActionGiveOutBookCommand());
        commands.put(CommandType.ACTION_USER_ORDER, new ActionUserOrderCommand());
        commands.put(CommandType.ACTION_ADMIN_COMMAND, new ActionAdminCommand());
        commands.put(CommandType.ACTION_ORDER, new ActionOrderCommand());
        commands.put(CommandType.ACTION_USER, new ActionUserCommand());
        commands.put(CommandType.ACTION_LIBRARY, new ActionLibraryCommand());
        commands.put(CommandType.ACTION_RETURN_BOOK, new ActionReturnBookCommand());
        commands.put(CommandType.ACTION_WISH_BOOK, new ActionWishBookCommand());

        commands.put(CommandType.SIGN_IN, new SingInCommand());
        commands.put(CommandType.SIGN_OUT, new SingOutCommand());
        commands.put(CommandType.REGISTRATION, new RegistrationCommand());

        commands.put(CommandType.SEARCH_BOOKS, new SearchBookCommand());
        commands.put(CommandType.ORDER_BOOK, new OrderBookCommand());

        commands.put(CommandType.CREATE_LIBRARY, new CreateLibraryCommand());
        commands.put(CommandType.CREATE_BOOK, new CreateBookCommand());
        commands.put(CommandType.CREATE_AUTHOR, new CreateAuthorCommand());
        commands.put(CommandType.CREATE_GENRE, new CreateGenreCommand());

        commands.put(CommandType.UPDATE_BOOK, new UpdateBookCommand());
        commands.put(CommandType.UPDATE_USER_PASSWORD, new UpdateUserPasswordCommand());
        commands.put(CommandType.UPDATE_USER, new UpdateUserCommand());
        commands.put(CommandType.UPDATE_LIBRARY, new UpdateLibraryCommand());
        commands.put(CommandType.UPDATE_AUTHOR, new UpdateAuthorCommand());
        commands.put(CommandType.UPDATE_GENRE, new UpdateGenreCommand());
        commands.put(CommandType.UPDATE_ORDER, new UpdateOrderCommand());

        commands.put(CommandType.ADMIN_PAGE, new AdminPageCommand());

        commands.put(CommandType.MANAGER_CATALOG, new AdminCatalogCommand());
        commands.put(CommandType.LIBRARY_CATALOG, new LibraryCatalogCommand());
        commands.put(CommandType.LOAN_CARD_CATALOG, new CatalogLoanCardCommand());
        commands.put(CommandType.SHOW_CATALOG_BY_PAGE, new ShowCatalogByPageCommand());

        commands.put(CommandType.USER_CATALOG, new UserCatalogCommand());
        commands.put(CommandType.CATALOG_BOOK, new CatalogBookCommand());
        commands.put(CommandType.ORDER_CATALOG, new OrderCatalogCommand());
        commands.put(CommandType.RETURN_BOOK_CATALOG, new CatalogReturnBookCommand());

        commands.put(CommandType.GO_TO_CATALOG_BY_PAGE, new GoToCatalogByPageCommand());
        commands.put(CommandType.GO_TO_ORDER, new GoToOrderBookCommand());
        commands.put(CommandType.ORDER_USER, new OrderUserCommand());
        commands.put(CommandType.WISH_BOOKS_USER_PAGE, new WishBooksUserCommand());  //+
        commands.put(CommandType.LOAN_CARD_USER, new LoanCardUserCommand());
        commands.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandType.ERROR_500, new Error500Command());
        commands.put(CommandType.ERROR_404, new Error404Command());
        commands.put(CommandType.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(CommandType.GO_TO_HOME, new GoToHomeCommand());
        commands.put(CommandType.GO_TO_MESSAGE_PAGE, new GoToMessagePageCommand());
        commands.put(CommandType.GO_TO_ADMIN_PAGE, new GoToAdminPageCommand());
        commands.put(CommandType.GO_TO_UPDATE_BOOK, new GoToUpdateBookCommand());
        commands.put(CommandType.GO_TO_UPDATE_ORDER, new GoToUpdateOrderCommand());
        commands.put(CommandType.GO_TO_STATISTICS, new GoToStatisticLibraryCommand());
        commands.put(CommandType.GO_TO_UPDATE_LIBRARY, new GoToUpdateLibraryCommand());
        commands.put(CommandType.GIVE_OUT_BOOK_USER, new CatalogGiveOutBookCommand());
        commands.put(CommandType.GO_TO_UPDATE_AUTHOR, new GoToUpdateAuthorCommand());
        commands.put(CommandType.GO_TO_UPDATE_GENRE, new GoToUpdateGenreCommand());

        commands.put(CommandType.GO_TO_STATISTIC_AUTHOR, new GoToStatisticAuthorCommand());
        commands.put(CommandType.GO_TO_STATISTIC_GENRE, new GoToStatisticGenreCommand());
        commands.put(CommandType.GO_TO_BOOK_DETAILS_PAGE, new GoToBookDetailsCommand());

    }

    public final Command getCommand(String command) {
        return commands.get(command);
    }
}
