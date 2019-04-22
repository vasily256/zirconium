package com.bellintegrator.zirconium.controller;

public class ErrorResponseBody implements ResponseBodyMarker {
    private final String error;

    public ErrorResponseBody(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
