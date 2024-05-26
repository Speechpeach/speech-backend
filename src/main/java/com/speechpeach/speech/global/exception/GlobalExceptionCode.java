package com.speechpeach.speech.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum GlobalExceptionCode implements ExceptionCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GLOBAL_000", "서버 에러가 발생하였습니다.<br/>문제가 반복되면 관리자에게 문의해주세요.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;
}
