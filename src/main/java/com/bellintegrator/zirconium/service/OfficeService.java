package com.bellintegrator.zirconium.service;

import com.bellintegrator.zirconium.view.OfficeView;

import java.util.Collection;

/**
 * Сервис для получения, создания и обновления офисов
 */
public interface OfficeService {

    /**
     * Получить список офисов по заданным критериям
     * @param office - критерии отбора
     * @return список офисов
     */
    Collection<OfficeView> list(OfficeView office);

    /**
     * Получить офис с заданным идентификатором
     * @param id уникальный идентификатор офиса
     * @return офис
     * @throws java.util.NoSuchElementException если не существует
     *         офиса с саданным идентификатором
     */
    OfficeView office(long id);

    /**
     * Обновить сведения об офисе
     * @param office офис
     * @throws java.util.NoSuchElementException если не существует
     *         офиса с саданным идентификатором
     */
    void update(OfficeView office);

    /**
     * Добавить новый офис
     * @param office офис
     * @return уникальный идентификатор нового офиса
     */
    long save(OfficeView office);
}
