package com.speechpeach.speech.login.repository;

import com.speechpeach.speech.login.entity.RefreshToken;
import com.speechpeach.speech.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    boolean existsByToken(String token);

    Optional<RefreshToken> findByMember(Member member);

    void deleteByToken(String token);
}
