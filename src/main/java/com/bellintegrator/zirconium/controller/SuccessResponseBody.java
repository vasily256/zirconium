package com.bellintegrator.zirconium.controller;

/**
 * Класс-обёртка сообщений об успешном завершении запросов
 */
public class SuccessResponseBody implements ResponseBodyMarker {

    public static final SuccessResponseBody SUCCESS_RESPONSE_BODY
            = new SuccessResponseBody();

    private final String result = "success";

    public String getResult() {
        return result;
    }
}
