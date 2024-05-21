package com.speechpeach.speech.login.filter;

import com.speechpeach.speech.auth.exception.AuthException;
import com.speechpeach.speech.auth.exception.AuthExceptionCode;
import com.speechpeach.speech.auth.jwt.JwtTokenProvider;
import com.speechpeach.speech.global.utils.CookieUtils;
import com.speechpeach.speech.login.service.RefreshTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import static com.speechpeach.speech.auth.jwt.JwtTokenProvider.REFRESH_TOKEN_COOKIE_NAME;
import static jakarta.servlet.http.HttpServletResponse.SC_NO_CONTENT;

/**
 * 로그아웃을 담당하는 Filter
 */
@RequiredArgsConstructor
public class LogoutFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        if (!requestURI.matches("^\\/api\\/logout$")) {
            chain.doFilter(request, response);
            return;
        }

        String requestMethod = request.getMethod();
        if (!requestMethod.equals(HttpMethod.DELETE.toString())) {
            chain.doFilter(request, response);
            return;
        }

        String refreshToken = CookieUtils.getCookie(request, REFRESH_TOKEN_COOKIE_NAME)
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthException(AuthExceptionCode.INVALID_REFRESH_TOKEN));
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new AuthException(AuthExceptionCode.INVALID_REFRESH_TOKEN);
        }
        refreshTokenService.delete(refreshToken);
        CookieUtils.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);

        response.setStatus(SC_NO_CONTENT);
    }
}
