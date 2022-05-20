package com.epam.library.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class {@link WishBook} is an entity that represents concrete records from 'WishBook' table.
 *
 * @author Alexander Pishchala
 *
 */

public class WishBook implements Serializable {

    private static final long serialVersionUID = 4876598467912932060L;

    private long wishBookId;
    private long bookId;
    private long userId;
    private LocalDate added;

    public long getWishBookId() {
        return wishBookId;
    }

    public void setWishBookId(long wishBooksId) {
        this.wishBookId = wishBooksId;
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

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        WishBook wishBook = (WishBook) obj;

        if (wishBookId != wishBook.wishBookId) {
            return false;
        }
        if (bookId != wishBook.bookId) {
            return false;
        }
        if (userId != wishBook.userId) {
            return false;
        }
        return added != null ? added.equals(wishBook.added) : wishBook.added == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (wishBookId ^ (wishBookId >>> 32));
        result = 31 * result + (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (added != null ? added.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        line.append("WishBook{").append("wishBookId=").append(wishBookId)
                .append(", bookId=").append(bookId)
                .append(", userId=").append(userId)
                .append(", added=").append(added)
                .append('}');
        return line.toString();
    }
}