package com.speechpeach.speech.auth.config;

import com.speechpeach.speech.auth.jwt.JwtTokenProvider;
import com.speechpeach.speech.auth.oauth.OAuth2SuccessHandler;
import com.speechpeach.speech.auth.oauth.PrincipalOauth2UserService;
import com.speechpeach.speech.login.filter.LogoutFilter;
import com.speechpeach.speech.login.filter.TokenAuthenticationFilter;
import com.speechpeach.speech.login.service.RefreshTokenService;
import com.speechpeach.speech.member.repository.MemberRepository;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * security 관련 설정을 명시하는 클래스
 */
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private static final String[] STATIC_RESOURCES = {
            "/swagger-ui/**"
    };

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final MemberRepository memberRepository;

    /**
     * security filter를 거치지 않을 uri를 명시한다.
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(STATIC_RESOURCES);
    }

    /**
     * security filter에 대한 설정을 명시한다.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(a -> a
                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/token/reissue").permitAll()
                        .requestMatchers("/api/**").hasRole("MEMBER")
                        .anyRequest().denyAll()
                )

                .addFilterBefore(new LogoutFilter(jwtTokenProvider, refreshTokenService), org.springframework.security.web.authentication.logout.LogoutFilter.class)

                .addFilterBefore(new TokenAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)

                .oauth2Login(o -> o
                        .userInfoEndpoint(u -> u.userService(new PrincipalOauth2UserService(memberRepository)))
                        .successHandler(new OAuth2SuccessHandler(jwtTokenProvider, refreshTokenService))
                )

                .build();
    }
}

