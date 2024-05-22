package com.speechpeach.speech.auth.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

/**
 * 사용자의 정보가 담긴 클래스
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthMember {

    private static final UUID GUEST_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    private final UUID memberId;
    private final Role role;

    public static AuthMember member(UUID memberId) {
        return new AuthMember(memberId, Role.ROLE_MEMBER);
    }

    public static AuthMember guest() {
        return new AuthMember(GUEST_ID, Role.ROLE_GUEST);
    }
}
