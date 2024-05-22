package com.speechpeach.speech.auth.exception;

import com.speechpeach.speech.global.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AuthExceptionCode implements ExceptionCode {
    NOT_SUPPORTED_OAUTH_SERVICE("AUTH_001", "해당 OAuth 서비스는 제공하지 않습니다."),

    INVALID_TOKEN("AUTH_011", "유효하지않은 토큰입니다."),
    INVALID_REFRESH_TOKEN("AUTH_012", "유효하지않은 리프레시토큰입니다."),
    INVALID_ACCESS_TOKEN("AUTH_013", "유효하지않은 엑세스토큰입니다."),

    INVALID_AUTHORITY("AUTH_021", "접근 권한이 존재하지않습니다.");

    private final String code;
    private final String message;
}
