package com.mall.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CommonResponse<T> {
    private T data;

    public static <T> ResponseEntity<CommonResponse<T>> of(final HttpStatus status, final T data) {
        return ResponseEntity.status(status).body(new CommonResponse<>(data));
    }

    public static <T> ResponseEntity<CommonResponse<T>> ok(final T data) {
        return of(HttpStatus.OK, data);
    }
}

