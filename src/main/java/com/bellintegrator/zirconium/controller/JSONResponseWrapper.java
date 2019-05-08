package com.bellintegrator.zirconium.controller;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Обработчик ответов с использованием класса-обёртки
 */
@ControllerAdvice
public class JSONResponseWrapper implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (body instanceof ResponseBodyMarker) {
            return body;
        }

        return new Wrapper<>(body);
    }

    public static class Wrapper<T> {
        private final T data;

        public Wrapper(T o) {
            this.data = o;
        }

        public T getData() {
            return data;
        }
    }
}