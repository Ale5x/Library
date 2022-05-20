package com.epam.library.entity.dto;

import com.epam.library.entity.BookTypeUse;
import com.epam.library.entity.LoanCardStatus;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class {@link LoanCardDto} is an entity that represents the ability to compose multiple 'Book', 'Library', 'User' table entities.
 *
 * @author Alexander Pishchala
 *
 */

public class LoanCardDto implements Serializable {

    private static final long serialVersionUID = -7940112002348591926L;

    private long loanCardDtoId;
    private long userId;
    private long bookId;
    private String title;
    private String isbn;
    private String cityLibrary;
    private LocalDate takingBook;
    private String returnBook;
    private LocalDate deadline;
    private LoanCardStatus status;
    private BookTypeUse typeUse;

    public LoanCardDto() {
    }

    public long getLoanCardDtoId() {
        return loanCardDtoId;
    }

    public void setLoanCardDtoId(long loanCardDtoId) {
        this.loanCardDtoId = loanCardDtoId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCityLibrary() {
        return cityLibrary;
    }

    public void setCityLibrary(String cityLibrary) {
        this.cityLibrary = cityLibrary;
    }

    public LocalDate getTakingBook() {
        return takingBook;
    }

    public void setTakingBook(LocalDate takingBook) {
        this.takingBook = takingBook;
    }

    public String getReturnBook() {
        return returnBook;
    }

    public void setReturnBook(String returnBook) {
        this.returnBook = returnBook;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LoanCardStatus getStatus() {
        return status;
    }

    public void setStatus(LoanCardStatus status) {
        this.status = status;
    }

    public BookTypeUse getTypeUse() {
        return typeUse;
    }

    public void setTypeUse(BookTypeUse typeUse) {
        this.typeUse = typeUse;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        LoanCardDto that = (LoanCardDto) obj;

        if (loanCardDtoId != that.loanCardDtoId) {
            return false;
        }
        if (userId != that.userId) {
            return false;
        }
        if (bookId != that.bookId) {
            return false;
        }
        if (!title.equals(that.title)) {
            return false;
        }
        if (!isbn.equals(that.isbn)) {
            return false;
        }
        if (!cityLibrary.equals(that.cityLibrary)) {
            return false;
        }
        if (!takingBook.equals(that.takingBook)) {
            return false;
        }
        if (!returnBook.equals(that.returnBook)) {
            return false;
        }
        if (!deadline.equals(that.deadline)) {
            return false;
        }
        if (status != that.status) {
            return false;
        }
        return typeUse == that.typeUse;
    }

    @Override
    public int hashCode() {
        int result = (int) (loanCardDtoId ^ (loanCardDtoId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (cityLibrary != null ? cityLibrary.hashCode() : 0);
        result = 31 * result + (takingBook != null ? takingBook.hashCode() : 0);
        result = 31 * result + (returnBook != null ? returnBook.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (typeUse != null ? typeUse.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuffer line = new StringBuffer();
        line.append("PersonalCard{").append("loanCardDtoId='").append(loanCardDtoId)
                .append("', userId=").append(userId)
                .append("', bookId='").append(bookId)
                .append("', title='").append(title)
                .append("', ISBN='").append(isbn)
                .append("', cityLibrary='").append(cityLibrary)
                .append( "', takingBook='").append(takingBook)
                .append("', returnBook='").append(returnBook)
                .append("', deadline='").append(deadline)
                .append("', status='").append(status)
                .append("', typeUse='").append(typeUse).append('}');
        return line.toString();
    }
}
