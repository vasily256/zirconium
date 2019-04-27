package com.bellintegrator.zirconium.service;

import java.util.Collection;

/**
 * Сервис для получения, создания и обновления офисов
 */
public interface ViewService {

    /**
     * Получить список офисов по заданным критериям
     * @param view - критерии отбора
     * @return список офисов
     */
    Collection<?> list(String viewName, Object view);

    /**
     * Получить офис с заданным идентификатором
     * @param id уникальный идентификатор офиса
     * @return офис
     * @throws java.util.NoSuchElementException если не существует
     *         офиса с саданным идентификатором
     */
    Object get(String viewName, long id);

    /**
     * Обновить сведения об офисе
     * @param view офис
     * @throws java.util.NoSuchElementException если не существует
     *         офиса с саданным идентификатором
     */
    void update(String viewName, Object view);

    /**
     * Добавить новый офис
     * @param view офис
     * @return уникальный идентификатор нового офиса
     */
    long save(String viewName, Object view);
}
