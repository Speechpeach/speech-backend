package com.speechpeach.speech.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {
    NOT_FOUND_USER_ID(1001, "요청한 ID에 해당하는 회원이 존재하지 않습니다.");

    private final int code;
    private final String message;
}
