package org.lmh.common.template;

import org.springframework.stereotype.Component;

@Component
public class EmailTemplate {

    private static final String FROM = "cineinfo00@gmail.com";
    private static final String SIGNUP_TITLE = "유저피드 서비스 회원 가입 인증 이메일입니다.";
    private static final String SIGNUP_CONTENT = "아래는 유저피드 서비스 회원 가입 인증번호 입니다. <BR><BR> %s <BR>";

    public String getFrom() {
        return FROM;
    }

    public String getSignupTitle() {
        return SIGNUP_TITLE;
    }

    public String getSignupContent(String token) {
        return String.format(SIGNUP_CONTENT, token);
    }
}
