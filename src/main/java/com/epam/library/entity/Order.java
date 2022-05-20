package com.epam.library.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class {@link Order} is an entity that represents concrete records from 'Order' table.
 *
 * @author Alexander Pishchala
 *
 */

public class Order implements Serializable {

    private static final long serialVersionUID = -6704147301500964288L;

    private long orderId;
    private long bookId;
    private long adminId;
    private long userId;
    private String libraryCity;
    private LocalDate date;
    private String comment;
    private OrderStatus status;

    public Order() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
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

    public String getLibraryCity() {
        return libraryCity;
    }

    public void setLibraryCity(String libraryCity) {
        this.libraryCity = libraryCity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Order order = (Order) obj;

        if (orderId != order.orderId) {
            return false;
        }
        if (bookId != order.bookId) {
            return false;
        }
        if (adminId != order.adminId) {
            return false;
        }
        if (userId != order.userId) {
            return false;
        }
        if (!libraryCity.equals(order.libraryCity)){
            return false;
        }
        if (!date.equals(order.date)){
            return false;
        }
        if (!comment.equals(order.comment)) {
            return false;
        }
        return status == order.status;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (int) (adminId ^ (adminId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (libraryCity != null ? libraryCity.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        line.append("Order{").append("orderId=").append(orderId)
                .append("', bookId='").append(bookId)
                .append("', adminId='").append(adminId)
                .append("', userId='").append(userId)
                .append("', setLibraryCity='").append(libraryCity)
                .append("', date='").append(date)
                .append("', comment='").append(comment)
                .append("', status='").append(status).append('}');
        return line.toString();
    }
}
