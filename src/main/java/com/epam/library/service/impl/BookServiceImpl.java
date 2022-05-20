package com.epam.library.service.impl;

import com.epam.library.dao.BookDao;
import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoFactory;
import com.epam.library.entity.*;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Class {@link BookServiceImpl} is service class which provide operations a book
 * part of application.
 *
 * @author Alexander Pishchala
 *
 */

public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public long getCountBooks() throws ServiceException {
        try {
            BookDao bookDao = DaoFactory.getInstance().getBookDao();
            return bookDao.getCountBooks();
        } catch (DaoException e) {
            logger.error("Error in services when getting the number of books.");
            throw new ServiceException("Error in services when getting the number of books.", e);
        }
    }

    @Override
    public boolean create(String title, String isbn, String publisher, String year, String quantity, String shelf,
                          String description, String library) throws ServiceException {
        try {
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            BookDao bookDao = DaoFactory.getInstance().getBookDao();
            if (validator.isLengthTitle(title) && validator.isLength(isbn) && validator.isLength(publisher) &&
                validator.isLength(year) && validator.isLength(shelf) && validator.isNumber(quantity)) {
                Book book = new Book();
                book.setTitle(title);
                book.setIsbn(isbn);
                book.setPublisher(publisher);
                book.setYear(year);
                book.setShelf(shelf);
                book.setQuantity(Integer.parseInt(quantity.trim()));
                book.setCityLibrary(library);
                return bookDao.create(book);
            } else {
                throw new ServiceException("Invalid data.");
            }
        } catch (DaoException e) {
            logger.error("Error in services when adding a book.");
            throw new ServiceException("Error in services when adding a book.", e);
        }
    }

    @Override
    public boolean update(String bookId, Book book, String quantity,  String cityLibrary) throws ServiceException {
        try {
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            BookDao bookDao = DaoFactory.getInstance().getBookDao();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            Book newBook = new Book();
            if (validator.isLengthForUpdate(book.getTitle()) && validator.isLengthForUpdate(book.getIsbn()) &&
                    validator.isLengthForUpdate(book.getPublisher()) && validator.isLengthForUpdate(book.getYear()) &&
                    validator.isLengthForUpdate(book.getShelf())) {

                Optional<Book> optionalBook = bookDao.getBookById(Long.parseLong(bookId));
                Optional<Library> optionalLibrary = libraryService.showByCity(cityLibrary);
                newBook.setBookId(optionalBook.get().getBookId());
                newBook.setTitle(book.getTitle() != "" ? book.getTitle() : optionalBook.get().getTitle());
                newBook.setIsbn(book.getIsbn() != "" ? book.getIsbn() : optionalBook.get().getIsbn());
                newBook.setPublisher(book.getPublisher() != "" ? book.getPublisher() :
                        optionalBook.get().getPublisher());
                newBook.setYear(book.getYear() != "" ? book.getYear() : optionalBook.get().getYear());
                newBook.setShelf(book.getShelf() != "" ? book.getShelf() : optionalBook.get().getShelf());
                newBook.setCityLibrary(cityLibrary != "" ? optionalLibrary.get().getCity() :
                        optionalBook.get().getCityLibrary());
                newBook.setDescription(book.getDescription() != "" ? book.getDescription() :
                        optionalBook.get().getDescription());
                if (validator.isNumber(quantity)) {
                    newBook.setQuantity(quantity != "" ? Integer.parseInt(quantity) :
                            optionalBook.get().getQuantity());
                }
                newBook.setAdded(optionalBook.get().getAdded());
                newBook.setBorrow(optionalBook.get().getBorrow());
                bookDao.update(newBook);
                return true;
            } else {
                return false;
            }
        }catch (DaoException e) {
            logger.error("An error in services when preparing to update a book without authors and genres.");
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addBorrow(String bookId) throws ServiceException {
        try {
            BookDao bookDao = DaoFactory.getInstance().getBookDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(bookId)) {
                Optional<Book> optionalBook = bookDao.getBookById(Long.parseLong(bookId.trim()));
                if (optionalBook.isPresent()) {
                    if (optionalBook.get().getBorrow() < optionalBook.get().getQuantity()) {
                        Book book = optionalBook.get();
                        book.setBorrow(optionalBook.get().getBorrow() + 1);
                        bookDao.update(book);
                    } else {
                        return false;
                    }
                } else {
                    throw new ServiceException("The book doesn't exist.");
                }
            }
            return true;
        } catch (DaoException e) {
            logger.error("The column 'On issue' has not been updated.");
            throw new ServiceException("The column 'On issue' has not been updated.", e);
        }
    }

    @Override
    public boolean deleteBorrow(String bookId) throws ServiceException {
        try {
            BookDao bookDao = DaoFactory.getInstance().getBookDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(bookId)) {
                Optional<Book> optionalBook = bookDao.getBookById(Long.parseLong(bookId.trim()));
                if (optionalBook.isPresent()) {
                    Book book = bookDao.getBookById(Long.parseLong(bookId)).get();
                    book.setBorrow(optionalBook.get().getBorrow() - 1);
                    bookDao.update(book);
                    return true;
                } else {
                    throw new ServiceException("The book does not exist.");
                }
            } else {
                throw new ServiceException("Book ID is not valid.");
            }
        } catch (DaoException e) {
            logger.error("The column 'On issue' has not been updated.");
            throw new ServiceException("The column 'On issue' has not been updated.", e);
        }
    }

    @Override
    public Optional<Book> showBookById(String bookId) throws ServiceException {
        try {
            BookDao bookDao = DaoFactory.getInstance().getBookDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(bookId)) {
                return bookDao.getBookById(Long.parseLong(bookId.trim()));
            } else {
                throw new ServiceException("The value is not a number.");
            }
        }catch (DaoException e) {
            logger.error("Error in services when retrieving a book by ID.");
            throw new ServiceException("Error in services when retrieving a book by ID.", e);
        }
    }
}
