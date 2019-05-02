package com.bellintegrator.zirconium.controller;

import com.bellintegrator.zirconium.service.content.ContentService;
import com.bellintegrator.zirconium.view.OfficeView;
import com.bellintegrator.zirconium.view.validationgroup.ListViews;
import com.bellintegrator.zirconium.view.validationgroup.SaveView;
import com.bellintegrator.zirconium.view.validationgroup.UpdateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.Min;
import java.net.URI;
import java.util.Collection;

import static com.bellintegrator.zirconium.controller.SuccessResponseBody.*;

/**
 * Контроллер офисов
 */
@RestController
@RequestMapping("/api/office")
public class OfficeController {

    private final ContentService<OfficeView> officeService;

    @Autowired
    public OfficeController(@Qualifier("office") ContentService<OfficeView> officeService) {
        this.officeService = officeService;
    }

    /**
     * Получить список офисов по заданным критериям
     * @param officeView - критерии отбора
     * @return список офисов
     */
    @PostMapping("/list")
    public Collection<?> list(@Validated(ListViews.class) @RequestBody OfficeView officeView) {
        return officeService.list(officeView);
    }

    /**
     * Получить офис с заданным идентификатором
     * @param id уникальный идентификатор офиса
     * @return офис
     */
    @GetMapping("/{id}")
    public Object get(@PathVariable @Min(1) long id) {
        return officeService.get(id);
    }

    /**
     * Обновить сведения об офисе
     * @param officeView офис
     * @return ответ на запрос
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@Validated(UpdateView.class) @RequestBody OfficeView officeView) {

        officeService.update(officeView);

        return ResponseEntity.ok(SUCCESS_RESPONSE_BODY);
    }

    /**
     * Добавить новый офис
     * @param officeView офис
     * @return ответ на запрос
     */
    @PostMapping("/save")
    public ResponseEntity<?> save(@Validated(SaveView.class) @RequestBody OfficeView officeView) {
        long id = officeService.save(officeView);

        URI location = ServletUriComponentsBuilder
                               .fromCurrentRequest()
                               .path("/{id}")
                               .buildAndExpand(id)
                               .toUri();

        return ResponseEntity.created(location).body(SUCCESS_RESPONSE_BODY);
    }
}
