package com.speechpeach.speech.auth.oauth;

import com.speechpeach.speech.auth.domain.Role;
import com.speechpeach.speech.auth.domain.PrincipalDetails;
import com.speechpeach.speech.auth.exception.AuthException;
import com.speechpeach.speech.member.entity.Member;
import com.speechpeach.speech.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import static com.speechpeach.speech.auth.exception.AuthExceptionCode.NOT_SUPPORTED_OAUTH_SERVICE;

@Transactional
@RequiredArgsConstructor
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo oAuth2UserInfo = createOAuth2UserInfo(oAuth2User, provider);

        Member member = findOrCreateMember(oAuth2UserInfo);
        return PrincipalDetails.createPrincipalOauth2User(member.getMemberId(), Role.ROLE_MEMBER, oAuth2User.getAttributes());
    }

    private OAuth2UserInfo createOAuth2UserInfo(OAuth2User oAuth2User, String provider) {
        OAuth2UserInfo oAuth2UserInfo = null;
        if (provider.equals("kakao")) {
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        } else {
            throw new AuthException(NOT_SUPPORTED_OAUTH_SERVICE);
        }
        return oAuth2UserInfo;
    }

    private Member findOrCreateMember(OAuth2UserInfo oAuth2UserInfo) {
        return memberRepository.findBySocialLoginProviderAndSocialLoginId(oAuth2UserInfo.getSocialLoginProvider(), oAuth2UserInfo.getSocialLoginId())
                .orElseGet(() -> saveMember(oAuth2UserInfo));
    }

    private Member saveMember(OAuth2UserInfo oAuth2UserInfo) {
        Member member = Member.builder()
                .socialLoginProvider(oAuth2UserInfo.getSocialLoginProvider())
                .socialLoginId(oAuth2UserInfo.getSocialLoginId())
                .nickname(oAuth2UserInfo.getNickname())
                .profileImageUrl(oAuth2UserInfo.getImageUrl())
                .email(oAuth2UserInfo.getEmail())
                .build();
        return memberRepository.save(member);
    }
}
