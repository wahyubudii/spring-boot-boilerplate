package com.spring.boilerplate.spring_boilerplate.response;

import com.spring.boilerplate.spring_boilerplate.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalResponse implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true; // implement in all endpoint
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        // Jika respons sudah berupa ResponseEntity, biarkan seperti itu
        if (body instanceof ResponseEntity) {
            return body;
        }

        // Jika respons adalah GlobalResponseDto, biarkan seperti itu
        if (body instanceof GlobalResponseDto || body instanceof GlobalException.ErrorResponse) {
            return body;
        }

        // Jika respons adalah string, kita perlu menanganinya secara khusus
        if (body instanceof String) {
            return body;
        }

        return new GlobalResponseDto<>(
                HttpStatus.OK.value(), // Status
                serverHttpRequest.getURI().getPath(), // Path
                null,
                "", // Pesan (bisa disesuaikan)
                body // Data
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GlobalResponseDto<Object> handleException(Exception ex, HttpServletRequest request) {
        return new GlobalResponseDto<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getRequestURI(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                null
        );
    }
}