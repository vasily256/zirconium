package com.bellintegrator.zirconium.controller;

import com.bellintegrator.zirconium.service.content.ContentService;
import com.bellintegrator.zirconium.view.OrganizationView;
import com.bellintegrator.zirconium.view.validation.group.ListViews;
import com.bellintegrator.zirconium.view.validation.group.SaveView;
import com.bellintegrator.zirconium.view.validation.group.UpdateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

import static com.bellintegrator.zirconium.controller.SuccessResponseBody.SUCCESS_RESPONSE_BODY;

/**
 * Контроллер организаций
 */
@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    private final ContentService<OrganizationView> organizationService;

    @Autowired
    public OrganizationController(ContentService<OrganizationView> organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Получить список организаций по заданным критериям
     * @param organizationView - критерии отбора
     * @return список организаций
     */
    @PostMapping("/list")
    public Collection<OrganizationView> list(@Validated(ListViews.class) @RequestBody
                                             OrganizationView organizationView) {

        return organizationService.list(organizationView);
    }

    /**
     * Получить организацию с заданным идентификатором
     * @param id уникальный идентификатор организации
     * @return организация
     */
    @GetMapping("/{id}")
    public OrganizationView get(@PathVariable long id) {
        return organizationService.get(id);
    }

    /**
     * Обновить сведения об организации
     * @param organizationView организация
     * @return ответ на запрос
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@Validated(UpdateView.class) @RequestBody
                                    OrganizationView organizationView) {

        organizationService.update(organizationView);

        return ResponseEntity.ok(SUCCESS_RESPONSE_BODY);
    }

    /**
     * Добавить новую организацию
     * @param organizationView организация
     * @return ответ на запрос
     */
    @PostMapping("/save")
    public ResponseEntity<?> save(@Validated(SaveView.class) @RequestBody
                                  OrganizationView organizationView) {

        long id = organizationService.save(organizationView);

        URI location = ServletUriComponentsBuilder
                               .fromCurrentContextPath()
                               .path("/api/organization/{id}")
                               .buildAndExpand(id)
                               .toUri();

        return ResponseEntity.created(location).body(SUCCESS_RESPONSE_BODY);
    }
}
