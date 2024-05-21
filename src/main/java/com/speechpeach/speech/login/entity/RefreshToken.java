package com.speechpeach.speech.login.entity;

import com.speechpeach.speech.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false, unique = true, updatable = false)
    private Member member;

    @Column(nullable = false, unique = true)
    private String token;

    @Builder
    public RefreshToken(Member member, String token) {
        this.member = member;
        this.token = token;
    }

    public RefreshToken updateToken(String newRefreshToken) {
        this.token = newRefreshToken;
        return this;
    }
}
