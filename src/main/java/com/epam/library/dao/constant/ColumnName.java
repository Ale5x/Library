package com.epam.library.dao.constant;

/**
 *
 * @author Alexander Pishchala
 *
 */

public final class ColumnName {
    private ColumnName() {}

    //User
    public static final String USER_ID_USERS = "id_users";
    public static final String USER_ID_LIBRARY = "id_library";
    public static final String USER_ID_ROLE = "id_role";
    public static final String USER_ACCOUNT_NUMBER = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_EMAIL = "email";
    public static final String USER_REGISTRATION = "registration";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_BIRTHDAY = "birthday";
    public static final String USER_ID_STATUS = "id_status";
    public static final String USER_COUNT_VIOLATIONS = "count_violations";

    //Role
    public static final String ROLE_ID_ROLE = "id_role";
    public static final String ROLE_ROLE = "role";

    //Order
    public static final String ORDER_ID_REQUEST = "id_orders";
    public static final String ORDER_ID_USER = "id_user";
    public static final String ORDER_ID_ADMIN = "id_admin";
    public static final String ORDER_ID_BOOK = "id_book";
    public static final String ORDER_ID_LIBRARY = "id_library";
    public static final String ORDER_DATE = "date";
    public static final String REQUEST_TYPE_USE = "type_use";
    public static final String ORDER_ID_STATUS = "id_status";
    public static final String ORDER_COMMENT = "comment";

    //Library
    public static final String LIBRARY_ID_LIBRARY = "id_library";
    public static final String LIBRARY_STREET = "street";
    public static final String LIBRARY_CITY = "city";
    public static final String LIBRARY_ID_STATUS = "id_status";

    //Library status
    public static final String LIBRARY_STATUS_ID_STATUS = "id_status";
    public static final String LIBRARY_STATUS_STATUS = "status";

    //BookDto
    public static final String BOOK_ID_BOOK = "id_books";
    public static final String BOOK_ID_LIBRARY = "id_library";
    public static final String BOOK_QUANTITY = "quantity";
    public static final String BOOK_BORROW = "borrow";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_PUBLISHER = "publisher";
    public static final String BOOK_DESCRIPTION = "description";
    public static final String BOOK_YEAR = "year";
    public static final String BOOK_ADDED = "added";
    public static final String BOOK_SHELF = "shelf";
    public static final String BOOK_ISBN = "isbn";


    public static final String BOOK_UNION_AUTHORS = "authors";
    public static final String BOOK_UNION_GENRES = "genres";

    //Loan cards
    public static final String LOAN_CARD_ID_CARD = "id_loan_cards";
    public static final String LOAN_CARD_ID_BOOK = "id_book";
    public static final String LOAN_CARD_ID_USER = "id_users";
    public static final String LOAN_CARD_ID_LIBRARY = "id_library";
    public static final String LOAN_CARD_TAKING_BOOK = "taking_book";
    public static final String LOAN_CARD_RETURN_BOOK = "return_book";
    public static final String LOAN_DEADLINE = "deadline";
    public static final String LOAN_CARD_TYPE_USE = "type_use";
    public static final String LOAN_CARD_ID_STATUS = "id_status";

    //Wish_book_has_users
    public static final String WB_H_U_ID_USER = "id_user";
    public static final String WB_H_U_ID_WB = "id_wish_book";

    //Wish_book
    public static final String WISH_BOOK_ID = "id_wish_books";
    public static final String WISH_BOOK_ID_BOOK = "id_books";
    public static final String WISH_BOOK_ID_USER = "id_users";
    public static final String WISH_BOOK_ADDED = "added";

    //Author
    public static final String AUTHOR_NAME = "name";
    public static final String AUTHOR_ID_AUTHOR = "id_authors";

    //Authors_has_book
    public static final String AHB_ID_BOOK = "id_book";
    public static final String AHB_ID_AUTHORS = "id_author";

    //Genres_has_book
    public static final String GHB_ID_BOOK = "id_book";
    public static final String GHB_ID_GENRES = "id_genre";

    //Genres
    public static final String GENRES_ID_GENRE = "id_genres";
    public static final String GENRES_GENRE = "genre";

    //Order status
    public static final String ORDER_STATUS_ID_STATUS = "id_status";
    public static final String ORDER_STATUS_STATUS = "status";

    //Personal Card status
    public static final String CARD_STATUS_ID_STATUS = "id_status";
    public static final String CARD_STATUS_STATUS = "status";

    //User status
    public static final String USER_STATUS_ID_STATUS = "id_status";
    public static final String USER_STATUS_STATUS = "status";

    //Chair
    public static final String CHAR_PERCENT = "%";
    public static final String CHAR_HATCH = "'";

}
