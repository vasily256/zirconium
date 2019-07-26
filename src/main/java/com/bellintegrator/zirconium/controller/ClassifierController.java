package com.bellintegrator.zirconium.controller;

import com.bellintegrator.zirconium.service.classifier.ClassifierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Контроллер справочников
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ClassifierController {

    private final Map<String, ClassifierService> classifierServices;

    @GetMapping("/{serviceName}")
    public List<?> get(@PathVariable String serviceName) {
        return classifierServices.get(serviceName).list();
    }
}
