package org.lmh.common.ui;

import org.lmh.common.domain.exception.ErrorCode;

// 응답값을 통일시키기 위한 Record 타입 Response
public record Response<T>(Integer code, String message, T value) {

    // 정상 응답
    public static <T> Response<T> ok(T value) {
        return new Response<>(0, "ok", value);
    }

    // 에러 코드 처리
    public static <T> Response<T> error(ErrorCode error) {
        return new Response<>(error.getCode(), error.getMessage(), null);
    }
}
