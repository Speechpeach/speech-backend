package com.speechpeach.speech.global.exception;

public interface ExceptionCode {
    String getHttpStatus();
    String getErrorCode();
    String getMessage();
}
