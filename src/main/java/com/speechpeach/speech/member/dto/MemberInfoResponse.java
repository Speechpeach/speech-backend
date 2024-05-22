package com.speechpeach.speech.member.dto;

import com.speechpeach.speech.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberInfoResponse {

    private UUID memberId;
    private String nickname;
    private String profileImageUrl;
    private String email;

    public static MemberInfoResponse from(final Member member) {
        return new MemberInfoResponse(
                member.getMemberId(),
                member.getNickname(),
                member.getProfileImageUrl(),
                member.getEmail()
        );
    }
}
