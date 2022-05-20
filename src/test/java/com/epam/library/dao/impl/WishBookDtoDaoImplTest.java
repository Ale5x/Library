package com.epam.library.dao.impl;
import com.epam.library.dao.WishBookDtoDao;
import com.epam.library.entity.dto.WishBookDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class WishBookDtoDaoImplTest {

    private WishBookDtoDao wishBookDtoDao;

    @BeforeEach
    public void setUp() throws Exception {
        wishBookDtoDao = new WishBookDtoDaoImpl();
    }

    @Test
    public void getBooks() {
        long userId = 1;
        List<WishBookDto> wishBooksDto = wishBookDtoDao.getBooks(userId);

        for (WishBookDto wishBook : wishBooksDto) {
            System.out.println(wishBook);
        }
        assertFalse(wishBooksDto.isEmpty());
    }
}