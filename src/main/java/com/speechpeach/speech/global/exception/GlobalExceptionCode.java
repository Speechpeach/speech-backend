package com.speechpeach.speech.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GlobalExceptionCode {

    INTERNAL_SERVER_ERROR("GLOBAL_000", "서버 에러가 발생하였습니다.<br/>문제가 반복되면 관리자에게 문의해주세요.");

    private final String code;
    private final String message;
}
