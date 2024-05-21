package com.speechpeach.speech.member.repository;

import com.speechpeach.speech.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findBySocialLoginProviderAndSocialLoginId(String socialLoginProvider, String socialLoginId);
}
