package com.epam.library.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class {@link Book} is an entity that represents concrete records from 'Book' table.
 *
 * @author Alexander Pishchala
 *
 */

public class Book implements Serializable {

    private static final long serialVersionUID = 1900551704855601119L;

    private long bookId;
    private String cityLibrary;
    private String isbn;
    private String title;
    private int quantity;
    private int borrow;
    private String publisher;
    private String description;
    private String year;
    private LocalDate added;
    private String shelf;

    public Book() {
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getCityLibrary() {
        return cityLibrary;
    }

    public void setCityLibrary(String cityLibrary) {
        this.cityLibrary = cityLibrary;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Book book = (Book) obj;

        if (bookId != book.bookId) {
            return false;
        }
        if (quantity != book.quantity) {
            return false;
        }
        if (borrow != book.borrow) {
            return false;
        }
        if (cityLibrary != null ? !cityLibrary.equals(book.cityLibrary) : book.cityLibrary != null) {
            return false;
        }
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) {
            return false;
        }
        if (title != null ? !title.equals(book.title) : book.title != null) {
            return false;
        }
        if (publisher != null ? !publisher.equals(book.publisher) : book.publisher != null) {
            return false;
        }
        if (description != null ? !description.equals(book.description) : book.description != null) {
            return false;
        }
        if (year != null ? !year.equals(book.year) : book.year != null) {
            return false;
        }
        if (added != null ? !added.equals(book.added) : book.added != null) {
            return false;
        }
        return shelf != null ? shelf.equals(book.shelf) : book.shelf == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (cityLibrary != null ? cityLibrary.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + borrow;
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (added != null ? added.hashCode() : 0);
        result = 31 * result + (shelf != null ? shelf.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        line.append("Book{").append("bookId='").append(bookId)
                .append("', cityLibrary='").append(cityLibrary)
                .append("', title='").append(title)
                .append("', isbn='").append(isbn)
                .append("', quantity='").append(quantity)
                .append("', borrow='").append(borrow)
                .append("', publisher='").append(publisher)
                .append("', year='").append(year)
                .append("', shelf='").append(shelf)
                .append("', description='").append(description)
                .append(", added=").append(added).append('}');
        return line.toString();
    }
}
