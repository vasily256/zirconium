package com.bellintegrator.zirconium.dao;

import java.util.List;

public interface ClassifierDao<E> {
    List<E> findAll();
}
