package com.setur.contact.enums;

import lombok.Getter;

public enum ErrorCode {

    STORE_NOT_FOUND_EXCEPTION("No store found with this store id");

    @Getter
    private final String description;

    ErrorCode(String description) {
        this.description = description;
    }

}
