package com.setur.contact.exception;

import java.io.Serializable;

public class ReportNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1327148031832611886L;

    public ReportNotFoundException(String message) {
        super(message);
    }


}
