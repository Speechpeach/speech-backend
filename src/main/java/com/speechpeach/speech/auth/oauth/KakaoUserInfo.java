package com.speechpeach.speech.auth.oauth;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo {

    private final Map<String, Object> attributes;
    private final Map<String, Object> attributesKaKaoAccount;
    private final Map<String, Object> attributesProfile;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.attributesKaKaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        this.attributesProfile = (Map<String, Object>) attributesKaKaoAccount.get("profile");
    }

    @Override
    public String getSocialLoginProvider() {
        return "kakao";
    }

    @Override
    public String getSocialLoginId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getNickname() {
        return (String) attributesProfile.get("nickname");
    }

    @Override
    public String getImageUrl() {
        return (String) attributesProfile.get("profile_image_url");
    }

    @Override
    public String getEmail() {
        return (String) attributesKaKaoAccount.get("email");
    }
}
