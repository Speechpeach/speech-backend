package com.speechpeach.speech.member.exception;

public class MemberException extends RuntimeException {
    private final String code;

    public MemberException(final MemberExceptionCode memberExceptionCode) {
        super(memberExceptionCode.getMessage());
        this.code = memberExceptionCode.getCode();
    }
}
