package com.epam.library.service.utill;

import com.epam.library.service.utill.Chiper.Cipher;
import com.epam.library.service.utill.Chiper.CipherImpl;

/**
 * Class {@link UtilFactory}. Factory class for util's.
 *
 *
 * @author Alexander Pishchala
 */

public class UtilFactory {

    private UtilFactory(){}

    private static UtilFactory instance = new UtilFactory();
    private static Cipher cipher = new CipherImpl();

    public static UtilFactory getInstance() {
        return instance;
    }

    public Cipher getCipher() {
        return cipher;
    }
}
