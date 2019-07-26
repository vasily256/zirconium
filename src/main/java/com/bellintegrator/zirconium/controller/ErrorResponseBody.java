package com.bellintegrator.zirconium.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс-обёртка сообщений об ошибках
 */
@AllArgsConstructor
@Getter
public class ErrorResponseBody implements ResponseBodyMarker {
    private final String error;
}
