package com.bellintegrator.zirconium.controller;

import com.bellintegrator.zirconium.service.classifier.ClassifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bellintegrator.zirconium.service.content.ContentService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.bellintegrator.zirconium.controller.SuccessResponseBody.*;

/**
 * Контроллер офисов
 */
@RestController
@RequestMapping("/api")
public class Controller {

    private final Map<String, ContentService> contentServices;

    private final Map<String, ClassifierService> classifierServices;

    @Autowired
    public Controller(Map<String, ContentService> contentServices,
                      Map<String, ClassifierService> classifierServices) {
        this.contentServices = contentServices;
        this.classifierServices = classifierServices;
    }

    /**
     * Получить список офисов по заданным критериям
     * @param view - критерии отбора
     * @return список офисов
     */
    @PostMapping("/{serviceName}/list")
    public Collection<?> list(@PathVariable String serviceName, @RequestBody Object view) {
        return contentServices.get(serviceName).list(view);
    }

    /**
     * Получить офис с заданным идентификатором
     * @param id уникальный идентификатор офиса
     * @return офис
     */
    @GetMapping("/{serviceName}/{id}")
    public Object get(@PathVariable String serviceName, @PathVariable long id) {
        return contentServices.get(serviceName).get(id);
    }

    /**
     * Обновить сведения об офисе
     * @param view офис
     * @return ответ на запрос
     */
    @PostMapping("/{serviceName}/update")
    public ResponseEntity<?> update(@PathVariable String serviceName, @RequestBody Object view) {
        contentServices.get(serviceName).update(view);

        return ResponseEntity.ok(SUCCESS_RESPONSE_BODY);
    }

    /**
     * Добавить новый офис
     * @param view офис
     * @return ответ на запрос
     */
    @PostMapping("/{serviceName}/save")
    public ResponseEntity<?> save(@PathVariable String serviceName, @RequestBody Object view) {
        long id = contentServices.get(serviceName).save(view);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
            "/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(location).body(SUCCESS_RESPONSE_BODY);
    }

    /**
     * Получить офис с заданным идентификатором
     * @param id уникальный идентификатор офиса
     * @return офис
     */
    @GetMapping("/{serviceName}")
    public List<?> get(@PathVariable String serviceName) {
        return classifierServices.get(serviceName).list();
    }
}
