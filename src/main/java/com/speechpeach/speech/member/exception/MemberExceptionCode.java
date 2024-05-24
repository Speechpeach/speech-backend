package com.speechpeach.speech.member.exception;

import com.speechpeach.speech.global.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MemberExceptionCode implements ExceptionCode {
    MEMBER_NOT_FOUND("MEMBER_000", "존재하지 않는 회원입니다.");

    private final String code;
    private final String message;
}
