package com.setur.contact.exception;

import java.io.Serializable;

public class ContactNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1327148031832611886L;

    public ContactNotFoundException(String message) {
        super(message);
    }


}
