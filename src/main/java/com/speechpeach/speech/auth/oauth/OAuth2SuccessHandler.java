package com.speechpeach.speech.auth.oauth;

import com.speechpeach.speech.auth.jwt.JwtTokenProvider;
import com.speechpeach.speech.global.utils.CookieUtils;
import com.speechpeach.speech.login.service.RefreshTokenService;
import com.speechpeach.speech.auth.domain.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.UUID;

import static com.speechpeach.speech.auth.jwt.JwtTokenProvider.REFRESH_TOKEN_COOKIE_NAME;

/**
 * OAuth 인증 성공시, 리프레시와 엑세스 토큰을 발급하여 응답하는 클래스
 */
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UUID id = UUID.fromString(authentication.getName());
        Role role = Role.valueOf(authentication.getAuthorities().iterator().next().getAuthority());

        String accessToken = jwtTokenProvider.generateAccessToken(id, role);
        response.addHeader(JwtTokenProvider.HEADER_AUTHORIZATION, JwtTokenProvider.TOKEN_PREFIX + accessToken);

        String refreshToken = jwtTokenProvider.generateRefreshToken(id, role);
        refreshTokenService.save(id, refreshToken);
        CookieUtils.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);
        response.addHeader(HttpHeaders.SET_COOKIE, createRefreshTokenCookieValue(
                refreshToken,
                jwtTokenProvider.getJwtProperties().getRefreshTokenValidityInSeconds()
        ));

        response.setStatus(HttpStatus.CREATED.value());
    }

    /**
     * 쿠키 값으로 입력될 리프레시 토큰 쿠키 문자열을 반환한다.
     *
     * @param refreshToken 리프레시 토큰 값
     * @param maxAge       만료기간(초)
     * @return 리프레시 토큰 쿠키 문자열
     */
    public static String createRefreshTokenCookieValue(String refreshToken, long maxAge) {
        ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN_COOKIE_NAME, refreshToken)
                .maxAge(maxAge)
                .path("/")
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .build();
        return cookie.toString();
    }
}
