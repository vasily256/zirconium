package com.bellintegrator.zirconium.controller;

public class SuccessResponseBody implements ResponseBodyMarker {

    public static final SuccessResponseBody SUCCESS_RESPONSE_BODY
            = new SuccessResponseBody();

    private final String result = "success";

    public String getResult() {
        return result;
    }
}
