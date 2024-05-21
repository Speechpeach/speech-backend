package com.speechpeach.speech.login.resolver;

import com.speechpeach.speech.auth.annotation.Auth;
import com.speechpeach.speech.auth.domain.AuthMember;
import com.speechpeach.speech.auth.domain.PrincipalDetails;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;
import java.util.UUID;

/**
 * 로그인한 회원 정보를 AuthMember 객체로 매핑해주는 Argument Resolver
 */
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Auth.class) &&
                parameter.getParameterType().equals(AuthMember.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication) && authentication.getPrincipal() instanceof PrincipalDetails principalDetails) {
            UUID memberId = UUID.fromString(principalDetails.getName());
            return AuthMember.member(memberId);
        }
        return AuthMember.guest();
    }
}
