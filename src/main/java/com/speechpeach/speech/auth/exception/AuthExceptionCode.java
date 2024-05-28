package com.speechpeach.speech.auth.exception;

import com.speechpeach.speech.global.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Getter
@RequiredArgsConstructor
public enum AuthExceptionCode implements ExceptionCode {
    NOT_SUPPORTED_OAUTH_SERVICE(BAD_REQUEST,"0000", "해당 OAuth 서비스는 제공하지 않습니다."),

    INVALID_TOKEN(BAD_REQUEST, "1000", "유효하지않은 토큰입니다."),
    INVALID_REFRESH_TOKEN(BAD_REQUEST, "1001", "유효하지않은 리프레시토큰입니다."),
    INVALID_ACCESS_TOKEN(BAD_REQUEST, "1002", "유효하지않은 엑세스토큰입니다."),

    INVALID_AUTHORITY(FORBIDDEN, "2000", "접근 권한이 존재하지않습니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;
}
