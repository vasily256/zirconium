package com.bellintegrator.zirconium.service.content;

import java.util.Collection;

/**
 * Сервис для получения, создания и обновления офисов
 */
public interface ContentService {

    /**
     * Получить список офисов по заданным критериям
     * @param view - критерии отбора
     * @return список офисов
     */
    Collection<?> list(Object view);

    /**
     * Получить офис с заданным идентификатором
     * @param id уникальный идентификатор офиса
     * @return офис
     * @throws java.util.NoSuchElementException если не существует
     *         офиса с саданным идентификатором
     */
    Object get(long id);

    /**
     * Обновить сведения об офисе
     * @param view офис
     * @throws java.util.NoSuchElementException если не существует
     *         офиса с саданным идентификатором
     */
    void update(Object view);

    /**
     * Добавить новый офис
     * @param view офис
     * @return уникальный идентификатор нового офиса
     */
    long save(Object view);
}
