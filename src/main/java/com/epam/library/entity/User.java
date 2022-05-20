package com.epam.library.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class {@link User} is an entity that represents concrete records from 'User' table.
 *
 * @author Alexander Pishchala
 *
 */

public class User implements Serializable {

    private static final long serialVersionUID = -1590356788525112801L;

    private long userId;
    private UserRole role;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateRegistration;
    private int countViolations;
    private UserStatus status;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(LocalDate dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public int getCountViolations() {
        return countViolations;
    }

    public void setCountViolations(int countViolations) {
        this.countViolations = countViolations;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
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

        User user = (User) obj;

        if (userId != user.userId) {
            return false;
        }
        if (countViolations != user.countViolations) {
            return false;
        }
        if (role != user.role) {
            return false;
        }
        if (password != null ? !password.equals(user.password) : user.password != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        if (dateRegistration != null ? !dateRegistration.equals(user.dateRegistration) : user.dateRegistration != null) {
            return false;
        }
        return status == user.status;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Long.hashCode(userId);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (dateRegistration != null ? dateRegistration.hashCode() : 0);
        result = 31 * result + countViolations;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuffer line = new StringBuffer();
        line.append("User{").append("userId='").append(userId)
                .append("', role='").append(role)
                .append("', password='").append(password)
                .append("', secondName='").append(firstName)
                .append("', lastName='").append(lastName)
                .append("', email='").append(email)
                .append("', dateRegistration='").append(dateRegistration)
                .append("', countViolations='").append(countViolations)
                .append("', status='").append(status).append('}');
        return line.toString();
    }
}
