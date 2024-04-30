//package com.speechpeach.speech.member.service;
//
//import com.speechpeach.speech.member.entity.Member;
//import com.speechpeach.speech.member.repository.MemberRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//import static com.speechpeach.speech.global.exception.ExceptionCode.NOT_FOUND_USER_ID;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class MemberService {
//
//    private final MemberRepository memberRepository;
//
//    public UUID createUser() {
//        Member user = new Member();
//        Member savedUser = memberRepository.save(user);
//        return savedUser.getMemberId();
//    }
//
//    public Member getUser(final UUID userId) {
//        return memberRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException(NOT_FOUND_USER_ID.getMessage()));
//    }
//}
