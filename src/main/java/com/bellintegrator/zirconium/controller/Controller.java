package com.bellintegrator.zirconium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bellintegrator.zirconium.service.ViewService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

import static com.bellintegrator.zirconium.controller.SuccessResponseBody.*;

/**
 * Контроллер офисов
 */
@RestController
@RequestMapping("/api")
public class Controller {

    private final ViewService viewService;

    @Autowired
    public Controller(ViewService viewService) {
        this.viewService = viewService;
    }

    /**
     * Получить список офисов по заданным критериям
     * @param view - критерии отбора
     * @return список офисов
     */
    @PostMapping("/{viewName}/list")
    public Collection<?> list(@PathVariable String viewName, @RequestBody Object view) {
        return viewService.list(viewName, view);
    }

    /**
     * Получить офис с заданным идентификатором
     * @param id уникальный идентификатор офиса
     * @return офис
     */
    @GetMapping("/{viewName}/{id}")
    public Object get(@PathVariable String viewName, @PathVariable long id) {
        return viewService.get(viewName, id);
    }

    /**
     * Обновить сведения об офисе
     * @param view офис
     * @return ответ на запрос
     */
    @PostMapping("/{viewName}/update")
    public ResponseEntity<?> update(@PathVariable String viewName, @RequestBody Object view) {
        viewService.update(viewName, view);

        return ResponseEntity.ok(SUCCESS_RESPONSE_BODY);
    }

    /**
     * Добавить новый офис
     * @param view офис
     * @return ответ на запрос
     */
    @PostMapping("/{viewName}/save")
    public ResponseEntity<?> save(@PathVariable String viewName, @RequestBody Object view) {
        long id = viewService.save(viewName, view);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
            "/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(location).body(SUCCESS_RESPONSE_BODY);
    }
}
