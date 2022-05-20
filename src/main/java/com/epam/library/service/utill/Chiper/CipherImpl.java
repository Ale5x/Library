package com.epam.library.service.utill.Chiper;

import com.epam.library.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Alexander Pishchala
 */

public class CipherImpl implements Cipher {

    private final static Logger logger = LoggerFactory.getLogger(CipherImpl.class);

    @Override
    public String getCipherString(String line) throws ServiceException {
        StringBuilder lineCipher = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(line.getBytes());
            lineCipher = new StringBuilder();
            for (byte shares : bytes) {
                lineCipher.append(String.format("%02X", shares));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("Method getCipherString... Error build CipherString... line = {}", line);
            throw new ServiceException("The data is unencrypted.");
        }
        return lineCipher.toString();
    }
}
