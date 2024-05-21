package com.speechpeach.speech.global.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.util.SerializationUtils;

import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CookieUtils {

    /**
     * 요청 인자를 바탕으로 쿠키 객체를 생성하여 반환한다.
     *
     * @param name   쿠키명
     * @param value  쿠키값
     * @param maxAge 만료기간(초)
     * @return 생성한 쿠키 객체
     */
    public static Cookie createCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        return cookie;
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Optional<Cookie> cookieOptional = getCookie(request, name);
        if (cookieOptional.isPresent()) {
            Cookie cookie = cookieOptional.get();
            cookie.setValue("");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return Optional.empty();
        }
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(name))
                .findFirst();
    }

    /**
     * 객체를 직렬화해 쿠키의 값에 들어갈 값으로 변환한다.
     *
     * @param obj 직렬화할 객체
     * @return 직렬화된 객체 문자열
     */
    public static String serialize(Object obj) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }

    /**
     * 쿠키의 값을 역직렬화해 객체로 변환한다.
     *
     * @param cookie 쿠키
     * @param cls    변환할 클래스 타입
     * @param <T>    변환할 클래스 타입
     * @return 변환된 객체
     */
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(
                org.apache.commons.lang3.SerializationUtils.deserialize(
                        Base64.getDecoder().decode(cookie.getValue()))
        );
    }
}
