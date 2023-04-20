package jy.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public enum HttpResponse {
    OK(HttpStatus.OK, "OK"),

    INVALID_ID_PASSWORD(HttpStatus.BAD_REQUEST, "잘못된 아이디 혹은 패스워드 입니다"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다"),
    PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자의 프로필 데이터가 존재하지 않습니다"),
    TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다"),
    INVALID_KAKAO_OAUTH(HttpStatus.NOT_FOUND, "잘못된 카카오 사용자 데이터 입니다"),
    INVALID_GOOGLE_OAUTH(HttpStatus.NOT_FOUND, "잘못된 구글 사용자 데이터 입니다");


    private final org.springframework.http.HttpStatus httpStatus;
    private final String message;

    HttpResponse(org.springframework.http.HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ResponseEntity<String> getResponseEntity() {
        return ResponseEntity.status(httpStatus).body(message);
    }
}
