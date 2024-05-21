package com.speechpeach.speech.auth.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {
    private final String code;

    public AuthException(final AuthExceptionCode authExceptionCode) {
        super(authExceptionCode.getMessage());
        this.code = authExceptionCode.getCode();
    }
}
