package com.speechpeach.speech.login.repository;

import com.speechpeach.speech.login.entity.RefreshToken;
import com.speechpeach.speech.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    boolean existsByTokenAndIsDelete(String token, boolean isDelete);

    Optional<RefreshToken> findByMemberAndIsDelete(Member member, boolean isDelete);

    Optional<RefreshToken> findByTokenAndIsDelete(String refreshToken, boolean isDelete);
}
