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
    public ResponseEntity<Object> handleContactNotFoundException(ContactNotFoundException e, WebRequest request) {
        var httpStatus = HttpStatus.BAD_REQUEST;
        var errorMessage = e.getMessage();
        var restResponse = buildResponse(httpStatus, errorMessage, ErrorCode.CONTACT_NOT_FOUND_EXCEPTION.name());
        return new ResponseEntity<>(restResponse, httpStatus);
    }

    @ExceptionHandler(CommunicationNotFoundException.class)
    public ResponseEntity<Object> handleCommunicationNotFoundException(CommunicationNotFoundException e, WebRequest request) {
        var httpStatus = HttpStatus.BAD_REQUEST;
        var errorMessage = e.getMessage();
        var restResponse = buildResponse(httpStatus, errorMessage, ErrorCode.COMMUNICATION_NOT_FOUND_EXCEPTION.name());
        return new ResponseEntity<>(restResponse, httpStatus);
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<Object> handleReportNotFoundException(ReportNotFoundException e, WebRequest request) {
        var httpStatus = HttpStatus.BAD_REQUEST;
        var errorMessage = e.getMessage();
        var restResponse = buildResponse(httpStatus, errorMessage, ErrorCode.REPORT_NOT_FOUND_EXCEPTION.name());
        return new ResponseEntity<>(restResponse, httpStatus);
    }

    private RestResponse<Object> buildResponse(HttpStatus httpStatus, String errorMessage, String errorCode) {
        return RestResponse.builder()
                .status(httpStatus.value())
                .isSuccess(false)
                .errorCode(errorCode)
                .message(errorMessage)
                .build();
    }
}
