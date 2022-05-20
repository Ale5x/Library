package com.epam.library.entity.dto;

import com.epam.library.entity.OrderStatus;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class {@link OrderDto} is an entity that represents the ability to compose multiple 'Book', 'Library', 'User' table entities.
 *
 * @author Alexander Pishchala
 *
 */

public class OrderDto implements Serializable {

    private static final long serialVersionUID = 9099702526178990582L;

    private long orderDtoId;
    private long bookId;
    private long adminId;
    private long userId;
    private LocalDate date;
    private int countViolations;
    private String title;
    private String year;
    private String isbn;
    private String cityLibrary;
    private OrderStatus status;
    private String comment;

    public OrderDto() {
    }

    public long getOrderDtoId() {
        return orderDtoId;
    }

    public void setOrderDtoId(long orderDtoId) {
        this.orderDtoId = orderDtoId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCountViolations() {
        return countViolations;
    }

    public void setCountViolations(int countViolations) {
        this.countViolations = countViolations;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getCityLibrary() {
        return cityLibrary;
    }

    public void setCityLibrary(String cityLibrary) {
        this.cityLibrary = cityLibrary;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        OrderDto orderDto = (OrderDto) obj;

        if (orderDtoId != orderDto.orderDtoId) {
            return false;
        }
        if (bookId != orderDto.bookId) {
            return false;
        }
        if (adminId != orderDto.adminId) {
            return false;
        }
        if (userId != orderDto.userId) {
            return false;
        }
        if (countViolations != orderDto.countViolations) {
            return false;
        }
        if (!date.equals(orderDto.date)) {
            return false;
        }
        if (!title.equals(orderDto.title)) {
            return false;
        }
        if (!year.equals(orderDto.year)) {
            return false;
        }
        if (!isbn.equals(orderDto.isbn)) {
            return false;
        }
        if (!cityLibrary.equals(orderDto.cityLibrary)) {
            return false;
        }
        if (status != orderDto.status) {
            return false;
        }
        return comment.equals(orderDto.comment);
    }

    @Override
    public int hashCode() {
        int result = (int) (orderDtoId ^ (orderDtoId >>> 32));
        result = 31 * result + (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (int) (adminId ^ (adminId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + countViolations;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (cityLibrary != null ? cityLibrary.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuffer line = new StringBuffer();
        line.append("RequestMaterial{").append("orderDtoId='").append(orderDtoId)
                .append("', bookId='").append(bookId)
                .append("', adminId='").append(adminId)
                .append("', userId='").append(userId)
                .append("', date='").append(date)
                .append("', count violations='" ).append(countViolations)
                .append("', title='").append(title)
                .append("', year='").append(year)
                .append("', isbn='").append(isbn)
                .append("', cityLibrary='").append(cityLibrary)
                .append("', status=").append(status)
                .append("', description='").append(comment).append('}');
        return line.toString();
    }
}
