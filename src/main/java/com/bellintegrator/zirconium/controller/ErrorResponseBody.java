package com.bellintegrator.zirconium.controller;

/**
 * Класс-обёртка сообщений об ошибках
 */
public class ErrorResponseBody implements ResponseBodyMarker {
    private final String error;

    public ErrorResponseBody(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
