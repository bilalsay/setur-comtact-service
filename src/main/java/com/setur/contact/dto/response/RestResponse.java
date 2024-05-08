package com.setur.contact.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestResponse<T> {

    private int status;

    @JsonProperty("isSuccess")
    private boolean isSuccess;

    private String error;

    private String errorCode;

    private String message = "No message available";

    private T data;
}
