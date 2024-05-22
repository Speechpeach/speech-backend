package com.speechpeach.speech.member.service;

import com.speechpeach.speech.member.dto.MemberInfoResponse;
import com.speechpeach.speech.member.entity.Member;
import com.speechpeach.speech.member.exception.MemberException;
import com.speechpeach.speech.member.exception.MemberExceptionCode;
import com.speechpeach.speech.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findById(UUID memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionCode.MEMBER_NOT_FOUND));
    }

    public MemberInfoResponse getPersonalInfo(UUID memberId) {
        final Member member = findById(memberId);
        return MemberInfoResponse.from(member);
    }
}
