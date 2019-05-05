package com.bellintegrator.zirconium.dao;

import java.util.List;

public interface ContentDao<E> {
    List<E> findAll(E entity);

    E findById(long id);

    long save(E entity);

    void update(E entity);
}
