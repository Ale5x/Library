package com.epam.library.service.utill.Chiper;

import com.epam.library.service.ServiceException;

/**
 * Interface {@link Cipher}. Interface for data encryption.
 *
 * @author Alexander Pishchala
 */

public interface Cipher {

    String getCipherString(String line) throws ServiceException;
}
