package com.speechpeach.speech.member.exception;

import com.speechpeach.speech.global.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RequiredArgsConstructor
@Getter
public enum MemberExceptionCode implements ExceptionCode {

    MEMBER_NOT_FOUND(BAD_REQUEST, "0000", "존재하지 않는 회원입니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;
}