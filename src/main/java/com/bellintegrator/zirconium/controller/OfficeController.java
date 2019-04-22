package com.bellintegrator.zirconium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bellintegrator.zirconium.view.OfficeView;
import com.bellintegrator.zirconium.service.OfficeService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

import static com.bellintegrator.zirconium.controller.SuccessResponseBody.*;

/**
 * Контроллер офисов
 */
@RestController
@RequestMapping("/api/office")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Получить список офисов по заданным критериям
     * @param office - критерии отбора
     * @return список офисов
     */
    @PostMapping("/list")
    public Collection<OfficeView> list(@RequestBody OfficeView office) {
        return officeService.list(office);
    }

    /**
     * Получить офис с заданным идентификатором
     * @param id уникальный идентификатор офиса
     * @return офис
     */
    @GetMapping(value = "/{id}")
    public OfficeView office(@PathVariable long id) {
        return officeService.office(id);
    }

    /**
     * Обновить сведения об офисе
     * @param office офис
     * @return ответ на запрос
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody OfficeView office) {
        officeService.update(office);

        return ResponseEntity.ok(SUCCESS_RESPONSE_BODY);
    }

    /**
     * Добавить новый офис
     * @param office офис
     * @return ответ на запрос
     */
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody OfficeView office) {
        long id = officeService.save(office);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
            "/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(location).body(SUCCESS_RESPONSE_BODY);
    }
}
