package com.epam.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceValidatorTest {

    private ServiceValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ServiceValidator();
    }

    @Test
    void isLengthRightTest() {
        String line = "word";
        boolean condition = validator.isLength(line);
        assertTrue(condition);
    }

    @Test
    void isLengthWrongTest() {
        String line = "Beginning in the eighth century B.C., Ancient Rome grew from a small town on central " +
                "Italy’s Tiber River into an empire that at its peak encompassed most of continental Europe, " +
                "Britain, much of western Asia, northern Africa and the Mediterranean islands. Among the many " +
                "legacies of Roman dominance are the widespread use of the Romance languages (Italian, French, " +
                "Spanish, Portuguese and Romanian) derived from Latin, the modern Western alphabet and calendar " +
                "and the emergence of Christianity as a major world religion. ";
        boolean condition = validator.isLength(line);
        assertFalse(condition);
    }

    @Test
    void isLengthNullTest() {
        String line = null;
        boolean condition = validator.isLength(line);
        assertFalse(condition);
    }

    @Test
    void isLengthEmptyTest() {
        String line = "";
        boolean condition = validator.isLength(line);
        assertFalse(condition);
    }

    @Test
    void isLengthStreetRightTest() {
        String line = "ул. Героев обороны Брестской крепости 60";
        boolean condition = validator.isLengthTitle(line);
        assertTrue(condition);
    }

    @Test
    void isLengthStreetWrongTest() {
        String line = "Beginning in the eighth century B.C., Ancient Rome grew from a small town on central " +
                "Italy’s Tiber River into an empire that at its peak encompassed most of continental Europe, " +
                "Britain, much of western Asia, northern Africa and the Mediterranean islands. Among the many " +
                "legacies of Roman dominance are the widespread use of the Romance languages (Italian, French, " +
                "Spanish, Portuguese and Romanian) derived from Latin, the modern Western alphabet and calendar " +
                "and the emergence of Christianity as a major world religion. ";
        boolean condition = validator.isLengthTitle(line);
        assertFalse(condition);
    }

    @Test
    void isLengthStreetEmptyTest() {
        String line = "";
        boolean condition = validator.isLengthTitle(line);
        assertFalse(condition);
    }

    @Test
    void isLengthStreetNullTest() {
        String line = null;
        boolean condition = validator.isLengthTitle(line);
        assertFalse(condition);
    }

    @Test
    void isLengthTitleRightTest() {
        String line = "word";
        boolean condition = validator.isLengthTitle(line);
        assertTrue(condition);
    }

    @Test
    void isLengthTitleWrongTest() {
        String line = "Beginning in the eighth century B.C., Ancient Rome grew from a small town on central " +
                "Italy’s Tiber River into an empire that at its peak encompassed most of continental Europe, " +
                "Britain, much of western Asia, northern Africa and the Mediterranean islands. Among the many " +
                "legacies of Roman dominance are the widespread use of the Romance languages (Italian, French, " +
                "Spanish, Portuguese and Romanian) derived from Latin, the modern Western alphabet and calendar " +
                "and the emergence of Christianity as a major world religion. ";
        boolean condition = validator.isLengthTitle(line);
        assertFalse(condition);
    }

    @Test
    void isLengthTitleEmptyTest() {
        String line = "";
        boolean condition = validator.isLengthTitle(line);
        assertFalse(condition);
    }

    @Test
    void isLengthTitleNullTest() {
        String line = null;
        boolean condition = validator.isLengthTitle(line);
        assertFalse(condition);
    }

    @Test
    void isLengthForUpdateStreetRightTest() {
        String line = "word";
        boolean condition = validator.isLengthForUpdate(line);
        assertTrue(condition);
    }

    @Test
    void isLengthForUpdateStreetWrongTest() {
        String line = "Beginning in the eighth century B.C., Ancient Rome grew from a small town on central " +
                "Italy’s Tiber River into an empire that at its peak encompassed most of continental Europe, " +
                "Britain, much of western Asia, northern Africa and the Mediterranean islands. Among the many " +
                "legacies of Roman dominance are the widespread use of the Romance languages (Italian, French, " +
                "Spanish, Portuguese and Romanian) derived from Latin, the modern Western alphabet and calendar " +
                "and the emergence of Christianity as a major world religion. ";
        boolean condition = validator.isLengthForUpdate(line);
        assertFalse(condition);
    }

    @Test
    void isLengthForUpdateStreetEmptyTest() {
        String line = "";
        boolean condition = validator.isLengthForUpdate(line);
        assertTrue(condition);
    }

    @Test
    void isLengthForUpdateStreetNullTest() {
        String line = null;
        boolean condition = validator.isLengthForUpdate(line);
        assertFalse(condition);
    }

    @Test
    void isLengthForUpdateRightTest() {
        String line = "word";
        boolean condition = validator.isLengthForUpdate(line);
        assertTrue(condition);
    }

    @Test
    void isLengthForUpdateWrongTest() {
        String line = "Beginning in the eighth century B.C., Ancient Rome grew from a small town on central " +
                "Italy’s Tiber River into an empire that at its peak encompassed most of continental Europe, " +
                "Britain, much of western Asia, northern Africa and the Mediterranean islands. Among the many " +
                "legacies of Roman dominance are the widespread use of the Romance languages (Italian, French, " +
                "Spanish, Portuguese and Romanian) derived from Latin, the modern Western alphabet and calendar " +
                "and the emergence of Christianity as a major world religion. ";
        boolean condition = validator.isLengthForUpdate(line);
        assertFalse(condition);
    }

    @Test
    void isLengthForUpdateEmptyTest() {
        String line = "";
        boolean condition = validator.isLengthForUpdate(line);
        assertTrue(condition);
    }

    @Test
    void isLengthForUpdateNullTest() {
        String line = null;
        boolean condition = validator.isLengthForUpdate(line);
        assertFalse(condition);
    }

    @Test
    void isNumberRightTest() {
        String line = "5";
        boolean condition = validator.isNumber(line);
        assertTrue(condition);
    }

    @Test
    void isNumberWrongTest() {
        String line = "word5";
        boolean condition = validator.isNumber(line);
        assertFalse(condition);
    }

    @Test
    void isNumberNullTest() {
        String line = null;
        boolean condition = validator.isNumber(line);
        assertFalse(condition);
    }

    @Test
    void isNumberEmptyTest() {
        String line = "";
        boolean condition = validator.isNumber(line);
        assertFalse(condition);
    }

    @Test
    void isEmailRightTest() {
        String email = "test@gmail.com";
        boolean condition = validator.isEmail(email);
        assertTrue(condition);
    }

    @Test
    void isEmailWrongTest() {
        String email = "test@gmail";
        boolean condition = validator.isEmail(email);
        assertFalse(condition);
    }

    @Test
    void isEmailNullTest() {
        String email = null;
        boolean condition = validator.isEmail(email);
        assertFalse(condition);
    }

    @Test
    void isPasswordRightTest() {
        String password = "A123456";
        boolean condition = validator.isPassword(password);
        assertTrue(condition);
    }

    @Test
    void isPasswordWrongTest() {
        String password = "password";
        boolean condition = validator.isPassword(password);
        assertFalse(condition);
    }

    @Test
    void isPasswordNullTest() {
        String password = null;
        boolean condition = validator.isPassword(password);
        assertFalse(condition);
    }
}