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
    private String memberNickname;

    @Column(name = "member_profile_image")
    private String memberProfileImage;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_email")
    private String memberEmail;

    private Member(UUID memberId, String memberNickname,
            String memberProfileImage, String memberName, String memberEmail) {
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.memberProfileImage = memberProfileImage;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
    }

    public static Member createMember(UUID memberId, String memberNickname,
            String memberProfileImage, String memberName, String memberEmail) {
        return new Member(memberId, memberNickname, memberProfileImage, memberName, memberEmail);
    }
}

