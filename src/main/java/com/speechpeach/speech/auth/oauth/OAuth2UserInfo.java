package com.speechpeach.speech.auth.oauth;

public interface OAuth2UserInfo {
    String getSocialLoginProvider();
    String getSocialLoginId();
    String getNickname();
    String getImageUrl();
    String getEmail();
}
