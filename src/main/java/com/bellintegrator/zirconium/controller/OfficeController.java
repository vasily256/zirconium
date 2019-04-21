package com.bellintegrator.zirconium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bellintegrator.zirconium.view.OfficeView;
import com.bellintegrator.zirconium.service.OfficeService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

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
     */
    @PostMapping("/update")
    public void update(@RequestBody OfficeView office) {
        officeService.update(office);
    }

    /**
     * Добавить новый офис
     * @param office офис
     */
    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody OfficeView office) {
        long id = officeService.save(office);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
            "/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();
    }
}
