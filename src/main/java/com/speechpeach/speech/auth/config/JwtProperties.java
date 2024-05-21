package com.speechpeach.speech.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * JWT의 정보를 저장하는 클래스
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("spring.jwt")
public class JwtProperties {
    private String issuer;
    private String secret;
    private Long refreshTokenValidityInSeconds;
    private Long accessTokenValidityInSeconds;
}
