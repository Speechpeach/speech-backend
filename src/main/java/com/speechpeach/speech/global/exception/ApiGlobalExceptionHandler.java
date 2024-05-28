package com.speechpeach.speech.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.speechpeach.speech.global.exception.GlobalExceptionCode.INTERNAL_SERVER_ERROR;

/**
 * 전역 예외 처리를 담당하는 ControllerAdvice
 */
@RestControllerAdvice
public class ApiGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBaseException(final BaseException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(final Exception e) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(new ErrorResponse(INTERNAL_SERVER_ERROR.getErrorCode(), INTERNAL_SERVER_ERROR.getMessage()));
    }
}