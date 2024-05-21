package com.speechpeach.speech.login.filter;

import com.speechpeach.speech.auth.domain.PrincipalDetails;
import com.speechpeach.speech.auth.exception.AuthException;
import com.speechpeach.speech.auth.domain.Role;
import com.speechpeach.speech.auth.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static com.speechpeach.speech.auth.jwt.JwtTokenProvider.HEADER_AUTHORIZATION;
import static com.speechpeach.speech.auth.jwt.JwtTokenProvider.extractAccessToken;
import static com.speechpeach.speech.auth.exception.AuthExceptionCode.INVALID_ACCESS_TOKEN;

/**
 * 토큰 인증을 담당하는 Filter
 */
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> accessTokenOptional = extractAccessToken(request.getHeader(HEADER_AUTHORIZATION));
        if (accessTokenOptional.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = accessTokenOptional.get();
        if (!jwtTokenProvider.validateToken(accessToken)) {
            throw new AuthException(INVALID_ACCESS_TOKEN);
        }

        Authentication authentication = getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    /**
     * 엑세스토큰으로부터 Security Context에 저장할 인증정보를 생성한다.
     *
     * @param accessToken
     * @return 인증정보
     */
    private Authentication getAuthentication(String accessToken) {
        UUID memberId = jwtTokenProvider.getMemberId(accessToken);
        Role role = jwtTokenProvider.getRole(accessToken);
        PrincipalDetails principalDetails = PrincipalDetails.createPrincipalDetails(memberId, role);

        return new UsernamePasswordAuthenticationToken(
                principalDetails,
                null,
                Collections.singletonList(new SimpleGrantedAuthority(role.toString()))
        );
    }
}
