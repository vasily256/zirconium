package com.bellintegrator.zirconium.controller;

import com.bellintegrator.zirconium.service.content.ContentService;
import com.bellintegrator.zirconium.view.UserView;
import com.bellintegrator.zirconium.view.validation.group.ListViews;
import com.bellintegrator.zirconium.view.validation.group.SaveView;
import com.bellintegrator.zirconium.view.validation.group.UpdateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

import static com.bellintegrator.zirconium.controller.SuccessResponseBody.SUCCESS_RESPONSE_BODY;

/**
 * Контроллер пользователей
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final ContentService<UserView> userService;

    @Autowired
    public UserController(ContentService<UserView> userService) {
        this.userService = userService;
    }

    /**
     * Получить список пользователей по заданным критериям
     * @param userView - критерии отбора
     * @return список пользователей
     */
    @PostMapping("/list")
    public Collection<UserView> list(@Validated(ListViews.class) @RequestBody UserView userView) {
        return userService.list(userView);
    }

    /**
     * Получить пользователя с заданным идентификатором
     * @param id уникальный идентификатор пользователя
     * @return пользователь
     */
    @GetMapping("/{id}")
    public UserView get(@PathVariable long id) {
        return userService.get(id);
    }

    /**
     * Обновить сведения о пользователе
     * @param userView пользователь
     * @return ответ на запрос
     */
    @PostMapping("/update")
    public ResponseEntity<?> update(@Validated(UpdateView.class) @RequestBody UserView userView) {

        userService.update(userView);

        return ResponseEntity.ok(SUCCESS_RESPONSE_BODY);
    }

    /**
     * Добавить нового пользователя
     * @param userView пользователь
     * @return ответ на запрос
     */
    @PostMapping("/save")
    public ResponseEntity<?> save(@Validated(SaveView.class) @RequestBody UserView userView) {
        long id = userService.save(userView);

        URI location = ServletUriComponentsBuilder
                               .fromCurrentContextPath()
                               .path("/api/user/{id}")
                               .buildAndExpand(id)
                               .toUri();

        return ResponseEntity.created(location).body(SUCCESS_RESPONSE_BODY);
    }
}
