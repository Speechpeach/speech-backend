package com.speechpeach.speech.member.entity;

import jakarta.persistence.*;
import com.speechpeach.speech.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "members")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID memberId;

    @Column(nullable = false)
    private String socialLoginProvider;

    @Column(nullable = false)
    private String socialLoginId;

    @Column(name = "member_nickname", nullable = false)
    private String nickname;

    @Column(name = "member_profile_image_url", nullable = false)
    private String profileImageUrl;

    @Column(name = "member_email", nullable = false)
    private String email;

    @Builder
    public Member(String socialLoginProvider, String socialLoginId, String nickname, String profileImageUrl, String email) {
        this.socialLoginProvider = socialLoginProvider;
        this.socialLoginId = socialLoginId;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.email = email;
    }
}

