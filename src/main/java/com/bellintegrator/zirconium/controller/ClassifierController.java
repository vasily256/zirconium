package com.bellintegrator.zirconium.controller;

import com.bellintegrator.zirconium.service.classifier.ClassifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Контроллер справочников
 */
@RestController
@RequestMapping("/api")
public class ClassifierController {

    private final Map<String, ClassifierService> classifierServices;

    @Autowired
    public ClassifierController(Map<String, ClassifierService> classifierServices) {
        this.classifierServices = classifierServices;
    }

    @GetMapping("/{serviceName}")
    public List<?> get(@PathVariable String serviceName) {
        return classifierServices.get(serviceName).list();
    }
}
