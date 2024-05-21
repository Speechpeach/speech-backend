package com.speechpeach.speech.login.service;

import com.speechpeach.speech.login.entity.RefreshToken;
import com.speechpeach.speech.login.repository.RefreshTokenRepository;
import com.speechpeach.speech.member.entity.Member;
import com.speechpeach.speech.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberService memberService;

    public boolean existsByToken(String refreshToken) {
        return refreshTokenRepository.existsByToken(refreshToken);
    }

    public void save(UUID memberId, String newRefreshToken) {
        Member member = memberService.findById(memberId);

        RefreshToken refreshToken = refreshTokenRepository.findByMember(member)
                .map(entity -> entity.updateToken(newRefreshToken))
                .orElse(RefreshToken.builder()
                        .member(member)
                        .token(newRefreshToken)
                        .build());
        refreshTokenRepository.save(refreshToken);
    }

    public void delete(String refreshToken) {
        refreshTokenRepository.deleteByToken(refreshToken);
    }
}
