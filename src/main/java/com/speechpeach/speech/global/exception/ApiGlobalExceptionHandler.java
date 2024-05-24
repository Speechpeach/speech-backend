package com.speechpeach.speech.global.exception;

import com.speechpeach.speech.auth.exception.AuthException;
import com.speechpeach.speech.member.exception.MemberException;
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
    public ResponseEntity<ErrorResponse> handleAuthException(final AuthException e) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMemberException(final MemberException e) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(final Exception e) {
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(INTERNAL_SERVER_ERROR.getCode(), INTERNAL_SERVER_ERROR.getMessage()));
    }
}