package com.setur.contact.exception;

import com.setur.contact.dto.response.RestResponse;
import com.setur.contact.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class ClientExceptionHandler {

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<Object> handleStoreHasNotActiveZoneException(ContactNotFoundException e, WebRequest request) {
        var httpStatus = HttpStatus.BAD_REQUEST;
        var errorMessage = e.getMessage();
        var restResponse = RestResponse.builder()
                .status(httpStatus.value())
                .isSuccess(false)
                .error(ErrorCode.STORE_NOT_FOUND_EXCEPTION.getDescription())
                .errorCode(ErrorCode.STORE_NOT_FOUND_EXCEPTION.name())
                .message(errorMessage)
                .build();
        return new ResponseEntity<>(restResponse, httpStatus);
    }
}
