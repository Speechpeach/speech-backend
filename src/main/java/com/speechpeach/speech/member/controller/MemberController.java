package com.speechpeach.speech.member.controller;

import com.speechpeach.speech.auth.annotation.Auth;
import com.speechpeach.speech.auth.annotation.MemberOnly;
import com.speechpeach.speech.auth.domain.AuthMember;
import com.speechpeach.speech.member.dto.MemberInfoResponse;
import com.speechpeach.speech.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member", description = "회원 관련 api")
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 정보 조회", description = "로그인한 회원의 정보를 조회한다.")
    @MemberOnly
    @GetMapping("/personal-info")
    public ResponseEntity<MemberInfoResponse> getPersonalInfo(@Auth final AuthMember authMember) {
        final MemberInfoResponse memberInfoResponse = memberService.getPersonalInfo(authMember.getMemberId());
        return ResponseEntity.ok(memberInfoResponse);
    }
}
