package com.speechpeach.speech.login.service;


import com.speechpeach.speech.auth.domain.Role;
import com.speechpeach.speech.auth.exception.AuthException;
import com.speechpeach.speech.auth.exception.AuthExceptionCode;
import com.speechpeach.speech.auth.jwt.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Service
public class LoginService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    public String renewAccessToken(final String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken) || !refreshTokenService.existsByToken(refreshToken)) {
            throw new AuthException(AuthExceptionCode.INVALID_REFRESH_TOKEN);
        }

        UUID memberId = jwtTokenProvider.getMemberId(refreshToken);
        Role role = jwtTokenProvider.getRole(refreshToken);
        return jwtTokenProvider.generateAccessToken(memberId, role);
    }
}
