package com.epam.library.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class {@link LoanCard} is an entity that represents concrete records from 'LoanCard' table.
 *
 * @author Alexander Pishchala
 *
 */

public class LoanCard implements Serializable {

    private static final long serialVersionUID = -6359287843904176135L;

    private long loanCardId;
    private long bookId;
    private long userId;
    private String cityLibrary;
    private LocalDate takingBook;
    private String returnBook;
    private LocalDate deadline;
    private LoanCardStatus status;
    private BookTypeUse typeUse;

    public LoanCard() {
    }

    public long getLoanCardId() {
        return loanCardId;
    }

    public void setLoanCardId(long loanCardId) {
        this.loanCardId = loanCardId;
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

        LoanCard loanCard = (LoanCard) obj;

        if (loanCardId != loanCard.loanCardId) {
            return false;
        }
        if (bookId != loanCard.bookId) {
            return false;
        }
        if (userId != loanCard.userId) {
            return false;
        }
        if (cityLibrary != null ? !cityLibrary.equals(loanCard.cityLibrary) : loanCard.cityLibrary != null) {
            return false;
        }
        if (takingBook != null ? !takingBook.equals(loanCard.takingBook) : loanCard.takingBook != null) {
            return false;
        }
        if (returnBook != null ? !returnBook.equals(loanCard.returnBook) : loanCard.returnBook != null) {
            return false;
        }
        if (deadline != null ? !deadline.equals(loanCard.deadline) : loanCard.deadline != null) {
            return false;
        }
        if (status != loanCard.status) {
            return false;
        }
        return typeUse == loanCard.typeUse;
    }

    @Override
    public int hashCode() {
        int result = (int) (loanCardId ^ (loanCardId >>> 32));
        result = 31 * result + (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
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
        StringBuilder line = new StringBuilder();
        line.append("LoanCard{").append("loanCardId=").append(loanCardId)
                .append(", bookId=").append(bookId)
                .append(", userId=").append(userId)
                .append(", cityLibrary='").append(cityLibrary)
                .append(", takingBook=").append(takingBook)
                .append(", returnBook='").append(returnBook)
                .append(", deadline=").append(deadline)
                .append(", status=").append(status)
                .append(", typeUse=").append(typeUse).append('}');
        return line.toString();
    }
}
