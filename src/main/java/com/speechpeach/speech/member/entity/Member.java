package com.speechpeach.speech.member.entity;

import com.speechpeach.speech.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.UUID;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "members")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "member_id")
    private UUID memberId;

    @Column(name = "member_nickname")
    private String nickname;

    @Column(name = "member_profile_image")
    private String profileImage;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_email")
    private String email;

    private Member(UUID memberId, String nickname,
            String profileImage, String name, String email) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.name = name;
        this.email = email;
    }

    public static Member createMember(UUID memberId, String nickname,
            String profileImage, String name, String email) {
        return new Member(memberId, nickname, profileImage, name, email);
    }
}

