package com.bellintegrator.zirconium.service.content;

import java.util.Collection;

/**
 * Сервис для получения, создания и обновления сущностей
 */
public interface ContentService<T> {

    /**
     * Получить список сущностей по заданным критериям
     * @param view - критерии сущностей
     * @return список сущностей
     */
    Collection<T> list(T view);

    /**
     * Получить сущность с заданным идентификатором
     * @param id уникальный идентификатор офиса
     * @return сущность
     * @throws java.util.NoSuchElementException если не существует
     *         сущности с саданным идентификатором
     */
    T get(long id);

    /**
     * Обновить сведения о сущности
     * @param view сущность
     * @throws java.util.NoSuchElementException если не существует
     *         сущности с саданным идентификатором
     */
    void update(T view);

    /**
     * Добавить новую сущность
     * @param view сущность
     * @return уникальный идентификатор новой сущности
     */
    long save(T view);
}
