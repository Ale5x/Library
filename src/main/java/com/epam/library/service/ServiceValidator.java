package com.epam.library.service;

/**
 * Class {@link ServiceValidator}. Validator class for services.
 *
 *
 * @author Alexander Pishchala
 */

public class ServiceValidator {

    private static final int MAX_LENGTH = 30;
    private static final int MAX_LENGTH_TITLE = 70;
    private static final int MAX_LENGTH_STREET = 50;
    private static final String REGEX_EMAIL = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
    private final String REGEX_PASSWORD  = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";

    public boolean isLength(String line) {
        if (line != null && line.length() <= MAX_LENGTH && line != "") {
            return true;
        }
        return false;
    }

    public boolean isLengthTitle(String line) {
        if (line != null && line.length() <= MAX_LENGTH_TITLE && line != "") {
            return true;
        }
        return false;
    }

    public boolean isLengthStreet(String line) {
        if (line != null && line.length() <= MAX_LENGTH_STREET && line != "") {
            return true;
        }
        return false;
    }

    public boolean isLengthForUpdateStreet(String line) {
        if (line != null && line.length() <= MAX_LENGTH_STREET) {
            return true;
        }
        return false;
    }

    public boolean isLengthForUpdate(String line) {
        if (line != null && line.length() <= MAX_LENGTH) {
            return true;
        }
        return false;
    }

    public boolean isNumber(String line) {
        if (line != "" && line != null) {
            return line.trim().matches("\\d+");
        }
        return false;
    }

    public boolean isEmail(String email) {
        if (isLength(email) && email != null) {
            return email.trim().matches(REGEX_EMAIL);
        }
        return false;
    }

    public boolean isPassword(String password) {
        if (isLength(password) && password != null) {
            return password.trim().matches(REGEX_PASSWORD);
        }
        return false;
    }
}
