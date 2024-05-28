package com.speechpeach.speech.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 커스텀 Exception의 기반 클래스
 */
@Getter
public abstract class BaseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorCode;

    protected BaseException(HttpStatus httpStatus, String errorCode, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
