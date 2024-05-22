package com.speechpeach.speech.auth.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

/**
 * Authentication의 Principal이자, OAuth 사용자 정보를 담고 있는 클래스
 */
public class PrincipalDetails implements OAuth2User {

    private final UUID memberId;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Map<String, Object> attributes;

    public static PrincipalDetails createPrincipalDetails(UUID memberId, Role role) {
        return new PrincipalDetails(memberId, role, null);
    }

    public static PrincipalDetails createPrincipalOauth2User(UUID memberId, Role role, Map<String, Object> attributes) {
        return new PrincipalDetails(memberId, role, attributes);
    }

    private PrincipalDetails(UUID memberId, Role role, Map<String, Object> attributes) {
        this.memberId = memberId;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(role.toString()));
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return memberId.toString();
    }
}
