package com.speechpeach.speech.login.controller;


import com.speechpeach.speech.login.dto.AccessTokenResponse;
import com.speechpeach.speech.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.speechpeach.speech.auth.jwt.JwtTokenProvider.REFRESH_TOKEN_COOKIE_NAME;
import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "Login", description = "로그인 관련 api")
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;

    @Operation(summary = "엑세스토큰 재발급", description = "리프레시토큰으로 만료한 엑세스토큰을 재발급한다.")
    @PostMapping("/api/token/reissue")
    public ResponseEntity<AccessTokenResponse> extendLogin(
            @CookieValue(REFRESH_TOKEN_COOKIE_NAME) final String refreshToken
    ) {
        String newAccessToken = loginService.renewAccessToken(refreshToken);
        return ResponseEntity
                .status(CREATED)
                .body(new AccessTokenResponse(newAccessToken));
    }
}
