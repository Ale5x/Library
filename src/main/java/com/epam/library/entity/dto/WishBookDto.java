package com.epam.library.entity.dto;

import java.io.Serializable;

/**
 * Class {@link WishBookDto} is an entity that represents the ability to compose multiple 'Book', 'User' table entities.
 *
 * @author Alexander Pishchala
 *
 */

public class WishBookDto implements Serializable {

    private static final long serialVersionUID = 5207396389975261002L;

    private long wishBooksId;
    private long bookId;
    private long userId;
    private String title;
    private int quantity;
    private int borrow;
    private String publisher;
    private String year;
    private String isbn;

    public WishBookDto() {
    }

    public long getWishBooksId() {
        return wishBooksId;
    }

    public void setWishBooksId(long wishBooksId) {
        this.wishBooksId = wishBooksId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBorrow() {
        return borrow;
    }

    public void setBorrow(int borrow) {
        this.borrow = borrow;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        WishBookDto book = (WishBookDto) obj;

        if (wishBooksId != book.wishBooksId) {
            return false;
        }
        if (bookId != book.bookId) {
            return false;
        }
        if (userId != book.userId) {
            return false;
        }
        if (quantity != book.quantity) {
            return false;
        }
        if (borrow != book.borrow) {
            return false;
        }
        if (title != null ? !title.equals(book.title) : book.title != null) {
            return false;
        }
        if (publisher != null ? !publisher.equals(book.publisher) : book.publisher != null) {
            return false;
        }
        if (year != null ? !year.equals(book.year) : book.year != null) {
            return false;
        }
        return isbn != null ? isbn.equals(book.isbn) : book.isbn == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (wishBooksId ^ (wishBooksId >>> 32));
        result = 31 * result + (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + borrow;
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        line.append("WishBookDto{").append("wishBookId='").append(wishBooksId)
                .append("', bookId='").append(bookId)
                .append("', userId='").append(userId)
                .append("', title='").append(title)
                .append("', isbn='").append(isbn)
                .append("', publisher='").append(publisher)
                .append("', quantity='").append(quantity)
                .append("', borrow='").append(borrow)
                .append("', year='").append(year).append('}');
        return line.toString();
    }
}
