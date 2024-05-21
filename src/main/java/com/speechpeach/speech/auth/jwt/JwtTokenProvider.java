package com.speechpeach.speech.auth.jwt;

import com.speechpeach.speech.auth.config.JwtProperties;
import com.speechpeach.speech.auth.exception.AuthException;
import com.speechpeach.speech.auth.exception.AuthExceptionCode;
import com.speechpeach.speech.auth.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
@Service
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public String generateRefreshToken(UUID id, Role role) {
        return generateToken(id, role, Duration.ofSeconds(jwtProperties.getRefreshTokenValidityInSeconds()));
    }

    public String generateAccessToken(UUID id, Role role) {
        return generateToken(id, role, Duration.ofSeconds(jwtProperties.getAccessTokenValidityInSeconds()));
    }

    private String generateToken(UUID id, Role role, Duration expiredAt) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expiredAt.toMillis());

        return Jwts.builder()
                .claim("id", id.toString())
                .claim("role", role.name())
                .issuer(jwtProperties.getIssuer())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigningKey(jwtProperties.getSecret()))
                .compact();
    }

    /**
     * Http 요청 헤더 값에서 access token 값을 읽어온다.
     * @param authorizationHeader Http 요청 헤더 중 Authorization 헤더 값
     * @return access token 값
     */
    public static Optional<String> extractAccessToken(String authorizationHeader) {
        if (Objects.isNull(authorizationHeader)) {
            return Optional.empty();
        }
        if (!authorizationHeader.startsWith(TOKEN_PREFIX)) {
            throw new AuthException(AuthExceptionCode.INVALID_ACCESS_TOKEN);
        }
        return Optional.of(authorizationHeader.substring(TOKEN_PREFIX.length()).trim());
    }

    /**
     * 토큰 훼손 유무와 만료 유무를 확인하여, 토큰의 유효성을 판단한다.
     *
     * @param token
     * @return 토큰이 유효하면 true, 유효하지 않으면 false를 반환한다.
     */
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (AuthException e) {
            return false;
        }
    }

    public UUID getMemberId(String token) {
        return UUID.fromString(
                getClaims(token).get("id", String.class)
        );
    }

    public Role getRole(String token) {
        return Role.valueOf(
                getClaims(token).get("role", String.class)
        );
    }

    private Claims getClaims(String token) {
        try {
            JwtParser jwtParser = Jwts.parser()
                    .verifyWith(getSigningKey(jwtProperties.getSecret()))
                    .build();
            return jwtParser.parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            throw new AuthException(AuthExceptionCode.INVALID_TOKEN);
        }
    }

    /**
     * JWT 암호화 방식
     */
    private SecretKey getSigningKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
