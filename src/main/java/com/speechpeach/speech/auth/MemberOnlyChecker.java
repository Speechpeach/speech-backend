package com.speechpeach.speech.auth;

import com.speechpeach.speech.auth.domain.AuthMember;
import com.speechpeach.speech.auth.domain.Role;
import com.speechpeach.speech.auth.exception.AuthException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.speechpeach.speech.auth.exception.AuthExceptionCode.INVALID_AUTHORITY;

/**
 * Member 권한을 가지는지 검증하는 클래스
 */
@Aspect
@Component
public class MemberOnlyChecker {

    @Before("@annotation(com.speechpeach.speech.auth.annotation.MemberOnly)")
    public void check(final JoinPoint joinPoint) {
        AuthMember authMember = Arrays.stream(joinPoint.getArgs())
                .filter(AuthMember.class::isInstance)
                .map(AuthMember.class::cast)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("@MemberOnly 어노테이션이 붙은 메소드는 반드시 AuthMember 타입의 파라미터를 가져야합니다."));

        if (!authMember.getRole().equals(Role.ROLE_MEMBER)) {
            throw new AuthException(INVALID_AUTHORITY);
        }
    }
}