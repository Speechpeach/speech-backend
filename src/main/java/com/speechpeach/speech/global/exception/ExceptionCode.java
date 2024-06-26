package com.speechpeach.speech.global.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionCode {
    HttpStatus getHttpStatus();
    String getErrorCode();
    String getMessage();
}
