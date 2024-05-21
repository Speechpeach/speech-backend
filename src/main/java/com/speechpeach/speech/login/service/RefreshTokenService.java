package com.speechpeach.speech.login.service;

import com.speechpeach.speech.auth.exception.AuthException;
import com.speechpeach.speech.login.entity.RefreshToken;
import com.speechpeach.speech.login.repository.RefreshTokenRepository;
import com.speechpeach.speech.member.entity.Member;
import com.speechpeach.speech.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.speechpeach.speech.auth.exception.AuthExceptionCode.INVALID_REFRESH_TOKEN;

@RequiredArgsConstructor
@Transactional
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberService memberService;

    public boolean existsByTokenAndNotDeleted(String refreshToken) {
        return refreshTokenRepository.existsByTokenAndIsDelete(refreshToken, false);
    }

    public void save(UUID memberId, String newRefreshToken) {
        Member member = memberService.findById(memberId);

        RefreshToken refreshToken = refreshTokenRepository.findByMemberAndIsDelete(member, false)
                .map(entity -> entity.updateToken(newRefreshToken))
                .orElse(RefreshToken.builder()
                        .member(member)
                        .token(newRefreshToken)
                        .build());
        refreshTokenRepository.save(refreshToken);
    }

    public void delete(String refreshToken) {
        RefreshToken foundRefreshToken = refreshTokenRepository.findByTokenAndIsDelete(refreshToken, false)
                .orElseThrow(() -> new AuthException(INVALID_REFRESH_TOKEN));
        foundRefreshToken.delete();
        refreshTokenRepository.save(foundRefreshToken);
    }
}
