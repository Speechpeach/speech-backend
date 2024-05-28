package com.speechpeach.speech.auth.exception;

import com.speechpeach.speech.global.exception.BaseException;

public class AuthException extends BaseException {
    private static final String ERROR_CODE_PREFIX = "AUTH_";

    public AuthException(final AuthExceptionCode exceptionCode) {
        super(exceptionCode.getHttpStatus(), ERROR_CODE_PREFIX + exceptionCode.getErrorCode(), exceptionCode.getMessage());
    }
}
