package com.epam.library.service;

import com.epam.library.service.impl.*;

/**
 * Class {@link ServiceFactory}. Factory class for service's.
 *
 *
 * @author Alexander Pishchala
 */

public class ServiceFactory {

    private ServiceFactory() {
    }

    private static ServiceFactory instance = new ServiceFactory();

    private BookDtoService bookDtoService = new BookDtoServiceImpl();
    private UserService userService = new UserServiceImpl();
    private BookService bookService = new BookServiceImpl();
    private OrderDtoService orderDtoService = new OrderDtoServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private WishBookService wishBookService = new WishBookServiceImpl();
    private WishBookDtoService wishBookDtoService = new WishBookDtoServiceImpl();
    private AuthorService authorService = new AuthorServiceImpl();
    private GenreService genreService = new GenreServiceImpl();
    private LoanCardDtoService loanCardDtoService = new LoanCardDtoServiceImpl();
    private LoanCardService loanCardService = new LoanCardServiceImpl();
    private LibraryService libraryService = new LibraryServiceImpl();
    private ServiceValidator serviceValidator = new ServiceValidator();


    public static ServiceFactory getInstance() {
        return instance;
    }

    public BookDtoService getBookDtoService() {
        return bookDtoService;
    }

    public WishBookService getWishBookService() {
        return wishBookService;
    }

    public AuthorService getAuthorService() {
        return authorService;
    }

    public GenreService getGenreService() {
        return genreService;
    }

    public UserService getUserService() {
        return userService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public OrderDtoService getOrderDtoService() {
        return orderDtoService;
    }

    public OrderService getOrderService() {
        return orderService;
    }



    public LibraryService getLibraryService() {
        return libraryService;
    }

    public ServiceValidator getServiceValidator() {
        return serviceValidator;
    }

    public WishBookDtoService getWishBookDtoService() {
        return wishBookDtoService;
    }

    public LoanCardDtoService getLoanCardDtoService() {
        return loanCardDtoService;
    }

    public LoanCardService getLoanCardService() {
        return loanCardService;
    }
}
