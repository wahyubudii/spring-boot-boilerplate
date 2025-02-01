package com.spring.boilerplate.spring_boilerplate.response;

import lombok.Getter;

@Getter
public class GlobalResponseDto<T> {
    private final int status;
    private final String path;
    private final String error;
    private final String message;
    private final T data;

    public GlobalResponseDto(int status, String path, String error, String message, T data) {
        this.status = status;
        this.path = path;
        this.error = error;
        this.message = message;
        this.data = data;
    }
}
