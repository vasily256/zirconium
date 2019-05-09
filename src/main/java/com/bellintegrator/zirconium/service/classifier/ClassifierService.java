package com.bellintegrator.zirconium.service.classifier;

import java.util.List;

/**
 * Сервис получения справочников
 * @param <T> тип справочника
 */
public interface ClassifierService<T> {

    /**
     * Получение справочника
     * @return List, содержащий справочник
     */
    List<T> list();
}
