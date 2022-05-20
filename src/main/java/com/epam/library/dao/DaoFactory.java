package com.epam.library.dao;

import com.epam.library.dao.impl.*;

/**
 * Class {@link DaoFactory}. Factory class for dao's.
 *
 *
 * @author Alexander Pishchala
 */

public class DaoFactory {

    private static DaoFactory instance = new DaoFactory();

    private DaoFactory(){}

    private BookDtoDao bookDtoDao = new BookDtoDaoImpl();
    public BookDao bookDao = new BookDaoImpl();
    private LibraryDao libraryDAO = new LibraryDaoImpl();
    private UserDao userDAO = new UserDaoImpl();
    private WishBookDao wishBookDao = new WishBookDaoImpl();
    private WishBookDtoDao wishBookDtoDao = new WishBookDtoDaoImpl();
    private OrderDtoDao orderDtoDao = new OrderDtoDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    private AuthorDao authorDAO = new AuthorDaoImpl();
    private GenreDao genreDAO = new GenreDaoImpl();
    private LoanCardDtoDao loanCardDtoDao = new LoanCardDtoDaoImpl();
    private LoanCardDao loanCardDao = new LoanCardDaoImpl();

    /**
     * Method gets instance.
     *
     * @return instance of DaoFactory.
     */
    public static DaoFactory getInstance() {
        return instance;
    }

    public BookDtoDao getBookDtoDao() {
        return bookDtoDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public LibraryDao getLibraryDAO() {
        return libraryDAO;
    }

    public UserDao getUserDAO() {
        return userDAO;
    }

    public WishBookDao getWishBookDao() {
        return wishBookDao;
    }

    public WishBookDtoDao getWishBookDtoDao() {
        return wishBookDtoDao;
    }

    public OrderDtoDao getOrderBookDtoDao() {
        return orderDtoDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public AuthorDao getAuthorDAO() {
        return authorDAO;
    }

    public GenreDao getGenreDAO() {
        return genreDAO;
    }

    public LoanCardDtoDao getLoanCardDtoDao() {
        return loanCardDtoDao;
    }

    public LoanCardDao getLoanCardDao() {
        return loanCardDao;
    }
}
