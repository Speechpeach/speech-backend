package com.speechpeach.speech.member.exception;

import com.speechpeach.speech.global.exception.BaseException;

public class MemberException extends BaseException {
    private static final String ERROR_CODE_PREFIX = "MEMBER_";

    public MemberException(final MemberExceptionCode exceptionCode) {
        super(exceptionCode.getHttpStatus(), ERROR_CODE_PREFIX + exceptionCode.getErrorCode(), exceptionCode.getMessage());
    }
}
