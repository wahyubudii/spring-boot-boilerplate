package com.spring.boilerplate.spring_boilerplate.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
}
