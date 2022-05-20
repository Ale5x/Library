package com.epam.library.controller;

/**
 *
 * @author Alexander Pishchala
 *
 */

public class Constant {

    public static final String URL = "url";
    public static final String SUCCESSFUL_MESSAGE = "successfulMessage";
    public static final String NEGATIVE_MESSAGE = "negativeMessage";

    public static final String USER = "user";
    public static final String USER_PASSWORD = "password";
    public static final String USER_OLD_PASSWORD = "old_password";
    public static final String USER_NEW_PASSWORD = "new_password";
    public static final String USER_FIRST_NAME = "firstName";
    public static final String USER_LAST_NAME = "lastName";
    public static final String USER_EMAIL = "email";
    public static final String USER_ID = "userId";
    public static final String USER_ADD_VIOLATION = "addViolation";
    public static final String USER_REMOVE_VIOLATION = "removeViolation";
    public static final String USER_ROLE = "role";
    public static final String USER_ALL_ADMIN = "allAdmins";
    public static final String USER_ALL = "getAll";
    public static final String USERS = "users"; //REQUEST
    public static final String USERS_SIZE = "userSize"; //REQUEST
    public static final String USERS_DELETE = "delete"; //REQUEST

    public static final String AUTHOR_ID = "authorId";
    public static final String AUTHOR_NAME = "name";
    public static final String AUTHOR_ALL = "all";
    public static final String AUTHORS_STAT = "statAuthors"; //REQUEST
    public static final String AUTHORS_COUNT_BOOKS = "authorCountBooks"; //REQUEST
    public static final String AUTHORS = "authors"; //REQUEST
    public static final String AUTHORS_SIZE = "authorsSize"; //REQUEST

    public static final String GENRE_ID = "genreId";
    public static final String GENRE_NAME = "category";
    public static final String GENRE_ALL = "all";
    public static final String GENRE_STAT = "statGenre"; //REQUEST
    public static final String GENRE_COUNT_BOOKS = "genreCountBooks"; //REQUEST
    public static final String GENRES = "genre"; //REQUEST
    public static final String GENRES_SIZE = "genreSize"; //REQUEST

    public static final String ORDER_ID = "orderId";

    public static final String ORDER_USER_ID = "orderUserId";
    public static final String ORDER_TYPE_USE = "type_use";
    public static final String ORDER_ALL = "getAll";
    public static final String ORDERS = "orders";       //REQUEST
    public static final String ORDERS_SIZE = "ordersSize"; //REQUEST

    public static final String ORDER_BOOK_ID = "orderBookId";
    public static final String ORDER_BOOK_COMMENT = "comment";

    public static final String STATUS = "status";
    public static final String ACTION = "action";

    public static final String LOAN_CARD_ID = "loanCardId";
    public static final String LOAN_CARDS = "loanCards"; //REQUEST
    public static final String LOAN_CARDS_SIZE = "loanCardsSize"; //REQUEST
    public static final String LOAN_ALL = "getAll";

    public static final String RETURN_BOOK_ALL = "getAll";
    public static final String RETURN_BOOK_WITH_VIOLATION = "violation";

    public static final String UPDATE_BOOK_ID = "updateBookId";
    public static final String UPDATE_LIBRARY_ID = "updateLibraryId";
    public static final String UPDATE_AUTHOR_ID = "updateAuthorId";
    public static final String UPDATE_GENRE_ID = "updateGenreId";


    public static final String BOOK_ID = "bookId";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_ISBN = "isbn";
    public static final String BOOK_PUBLISHER = "publisher";
    public static final String BOOK_YEAR = "year";
    public static final String BOOK_QUANTITY = "quantity";
    public static final String BOOK_SHELF = "shelf";
    public static final String BOOK_DESCRIPTION = "description";
    public static final String BOOK_AUTHOR = "author";
    public static final String BOOK_GENRE = "genre";
    public static final String BOOK_ALL_BOOKS = "all";
    public static final String WISH_BOOK_ADD = "add";
    public static final String WISH_BOOK_DELETE = "delete";
    public static final String BOOK_SIZE = "booksSize"; //REQUEST
    public static final String BOOKS = "books"; //REQUEST
    public static final String WISH_BOOK_ID = "wish_book_id";
    public static final String BOOK_CATEGORY = "category";

    public static final String LIBRARY_ID = "libraryId";
    public static final String LIBRARY_CITY = "city";
    public static final String LIBRARY_STREET = "street";
    public static final String LIBRARY_ALL = "getAll";
    public static final String LIBRARIES = "libraries";
    public static final String LIBRARY = "library";

    public static final String NEXT = "next";
    public static final String BACK = "back";
    public static final String JUMP_PAGE = "jumpPage";
    public static final String PAGE_CATALOG = "pageCatalog";

    //Request
    public static final String STATISTIC_COUNT_BOOKS = "countBooks";

    public static final String STATISTIC_COUNT_USERS = "countUsers";
    public static final String STATISTIC_COUNT_USERS_BLOCKED = "countUsersBlocked";
    public static final String STATISTIC_COUNT_USERS_DELETE = "countUsersDelete";
    public static final String STATISTIC_COUNT_USERS_ACTIVE = "countUsersActive";

    public static final String STATISTIC_COUNT_AUTHORS = "countAuthors";
    public static final String STATISTIC_COUNT_GENRES = "countGenres";

    public static final String STATISTIC_COUNT_LOAN_CARD_OPEN = "countLoanCardOpen";
    public static final String STATISTIC_COUNT_LOAN_CARD = "countLoanCard";

    public static final String STATISTIC_COUNT_ORDERS_OPEN = "ordersOpen";
    public static final String STATISTIC_COUNT_ORDERS_APPROVED = "ordersApproved";
    public static final String STATISTIC_COUNT_ORDERS_REJECTED = "ordersRejected";
    public static final String STATISTIC_COUNT_ORDERS_ARRIVED = "ordersArrived";
    public static final String STATISTIC_COUNT_ORDERS_CLOSED = "ordersClosed";

    //Message
    //Successful messages
    public static final String MESSAGE_CODE_1001 = "MESSAGE_CODE_1001";
    public static final String MESSAGE_CODE_1002 = "MESSAGE_CODE_1002";
    public static final String MESSAGE_CODE_10021 = "MESSAGE_CODE_10021";
    public static final String MESSAGE_CODE_1003 = "MESSAGE_CODE_1003";
    public static final String MESSAGE_CODE_10031 = "MESSAGE_CODE_10031";
    public static final String MESSAGE_CODE_1004 = "MESSAGE_CODE_1004";
    public static final String MESSAGE_CODE_1005 = "MESSAGE_CODE_1005";
    public static final String MESSAGE_CODE_1006 = "MESSAGE_CODE_1006";
    public static final String MESSAGE_CODE_1007 = "MESSAGE_CODE_1007";
    public static final String MESSAGE_CODE_1008 = "MESSAGE_CODE_1008";
    public static final String MESSAGE_CODE_1009 = "MESSAGE_CODE_1009";
    public static final String MESSAGE_CODE_1010 = "MESSAGE_CODE_1010";
    public static final String MESSAGE_CODE_1011 = "MESSAGE_CODE_1011";
    public static final String MESSAGE_CODE_1012 = "MESSAGE_CODE_1012";
    public static final String MESSAGE_CODE_1013 = "MESSAGE_CODE_1013";
    public static final String MESSAGE_CODE_1014 = "MESSAGE_CODE_1014";
    public static final String MESSAGE_CODE_1015 = "MESSAGE_CODE_1015";
    public static final String MESSAGE_CODE_1016 = "MESSAGE_CODE_1016";
    public static final String MESSAGE_CODE_1017 = "MESSAGE_CODE_1017";
    public static final String MESSAGE_CODE_1018 = "MESSAGE_CODE_1018";
    public static final String MESSAGE_CODE_1019 = "MESSAGE_CODE_1019";
    public static final String MESSAGE_CODE_1020 = "MESSAGE_CODE_1020";


    //Unsuccessful messages
    public static final String MESSAGE_ERROR_CODE_1001 = "MESSAGE_ERROR_CODE_1001";
    public static final String MESSAGE_ERROR_CODE_1002 = "MESSAGE_ERROR_CODE_1002";
    public static final String MESSAGE_ERROR_CODE_1003 = "MESSAGE_ERROR_CODE_1003";
    public static final String MESSAGE_ERROR_CODE_1004 = "MESSAGE_ERROR_CODE_1004";
    public static final String MESSAGE_ERROR_CODE_1005 = "MESSAGE_ERROR_CODE_1005";
    public static final String MESSAGE_ERROR_CODE_1006 = "MESSAGE_ERROR_CODE_1006";
    public static final String MESSAGE_ERROR_CODE_1007 = "MESSAGE_ERROR_CODE_1007";
    public static final String MESSAGE_ERROR_CODE_1008 = "MESSAGE_ERROR_CODE_1008";
    public static final String MESSAGE_ERROR_CODE_1009 = "MESSAGE_ERROR_CODE_1009";
    public static final String MESSAGE_ERROR_CODE_1010 = "MESSAGE_ERROR_CODE_1010";
    public static final String MESSAGE_ERROR_CODE_1011 = "MESSAGE_ERROR_CODE_1011";
    public static final String MESSAGE_ERROR_CODE_1012 = "MESSAGE_ERROR_CODE_1012";
    public static final String MESSAGE_ERROR_CODE_1013 = "MESSAGE_ERROR_CODE_1013";
    public static final String MESSAGE_ERROR_CODE_1014 = "MESSAGE_ERROR_CODE_1014";
    public static final String MESSAGE_ERROR_CODE_1015 = "MESSAGE_ERROR_CODE_1015";
    public static final String MESSAGE_ERROR_CODE_1016 = "MESSAGE_ERROR_CODE_1016";
    public static final String MESSAGE_ERROR_CODE_1017 = "MESSAGE_ERROR_CODE_1017";
    public static final String MESSAGE_ERROR_CODE_1018 = "MESSAGE_ERROR_CODE_1018";
    public static final String MESSAGE_ERROR_CODE_1019 = "MESSAGE_ERROR_CODE_1019";
    public static final String MESSAGE_ERROR_CODE_1020 = "MESSAGE_ERROR_CODE_1020";
    public static final String MESSAGE_ERROR_CODE_1021 = "MESSAGE_ERROR_CODE_1021";
    public static final String MESSAGE_ERROR_CODE_1022 = "MESSAGE_ERROR_CODE_1022";
    public static final String MESSAGE_ERROR_CODE_1023 = "MESSAGE_ERROR_CODE_1023";
    public static final String MESSAGE_ERROR_CODE_1024 = "MESSAGE_ERROR_CODE_1024";
    public static final String MESSAGE_ERROR_CODE_1025 = "MESSAGE_ERROR_CODE_1025";


    /*
    //Message decryption
    public static final String MESSAGE_CODE_1001 = "Password changed.";
    public static final String MESSAGE_CODE_1002 = "The author's information has been changed.";
    public static final String MESSAGE_CODE_10021 = "The author has been added to the system.";
    public static final String MESSAGE_CODE_1003 = "The genre information has been changed.";
    public static final String MESSAGE_CODE_10031 = "The genre has been added to the system.";
    public static final String MESSAGE_CODE_1004 = "The book has been added to the system.";
    public static final String MESSAGE_CODE_1005 = "The library has been added to the system.";
    public static final String MESSAGE_CODE_1006 = "Order is processed. Wait for administrator confirmation.";
    public static final String MESSAGE_CODE_1007 = "User role changed.";
    public static final String MESSAGE_CODE_1008 = "Book issued to user.";
    public static final String MESSAGE_CODE_1009 = "Library status changed.";
    public static final String MESSAGE_CODE_1010 = "Order status changed.";
    public static final String MESSAGE_CODE_1011 = "The book was returned with a defect. The issuing card is closed.";
    public static final String MESSAGE_CODE_1012 = "The book has been returned. Issuing card status closed.";
    public static final String MESSAGE_CODE_1013 = "User status changed.";
    public static final String MESSAGE_CODE_1014 = "The violation has been added to the user.";
    public static final String MESSAGE_CODE_1015 = "The penalty was removed from the user successfully.";
    public static final String MESSAGE_CODE_1016 = "Order deleted successfully.";
    public static final String MESSAGE_CODE_1017 = "The book has been updated.";
    public static final String MESSAGE_CODE_1018 = "The library data has been changed.";
    public static final String MESSAGE_CODE_1019 = "The user data has been updated.";
    public static final String MESSAGE_CODE_1020 = "The order has been changed.";


    public static final String MESSAGE_ERROR_CODE_1001 = "Email busy.";
    public static final String MESSAGE_ERROR_CODE_1002 = "Too long name or surname.";
    public static final String MESSAGE_ERROR_CODE_1003 = "Wrong email or password. Password must be at least 6 " +
            "characters, at least one big letter and one number";
    public static final String MESSAGE_ERROR_CODE_1004 = "Invalid email. User is not found. Please enter a valid email.";
    public static final String MESSAGE_ERROR_CODE_1005 = "Password must be at least 6 characters, at least one big " +
            "letter and one number.";
    public static final String MESSAGE_ERROR_CODE_1006 = "An author with such data already exists in the system. " +
            "Please check your input.";
    public static final String MESSAGE_ERROR_CODE_1007 = "The value is too long.";
    public static final String MESSAGE_ERROR_CODE_1008 = "This genre is already present in the system.";
    public static final String MESSAGE_ERROR_CODE_1009 = "The book has not been added to the system. The data " +
            "length is too long.";
    public static final String MESSAGE_ERROR_CODE_1010 = "The library already exists in the system, change its status.";
    public static final String MESSAGE_ERROR_CODE_1011 = "The order has not been placed. There are no free books.";
    public static final String MESSAGE_ERROR_CODE_1012 = "The user role has not changed.";
    public static final String MESSAGE_ERROR_CODE_1013 = "The book has not been checked out to the user.";
    public static final String MESSAGE_ERROR_CODE_1014 = "Library status has not changed.";
    public static final String MESSAGE_ERROR_CODE_1015 = "Order status has not changed.";
    public static final String MESSAGE_ERROR_CODE_1016 = "The book was returned with a defect. Issuing card is not closed.";
    public static final String MESSAGE_ERROR_CODE_1017 = "User status has not changed.";
    public static final String MESSAGE_ERROR_CODE_1018 = "The violation has not been added to the user.";
    public static final String MESSAGE_ERROR_CODE_1019 = "The violation has not been removed from the user.";
    public static final String MESSAGE_ERROR_CODE_1020 = "Error when deleting order. An order can only be deleted if " +
            "the status is \"Open\".";
    public static final String MESSAGE_ERROR_CODE_1021 = "The book has not been updated. Too long values.";
    public static final String MESSAGE_ERROR_CODE_1022 = The library has not been updated.";
    public static final String MESSAGE_ERROR_CODE_1023 = "User is not found.";
    public static final String MESSAGE_ERROR_CODE_1024 = "Invalid email.";
    public static final String MESSAGE_ERROR_CODE_1025 = "The order has not been changed.";
     */

}
