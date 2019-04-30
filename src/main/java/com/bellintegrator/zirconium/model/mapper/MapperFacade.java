package com.bellintegrator.zirconium.model.mapper;

import java.util.List;

public interface MapperFacade {
    <S, D> List<D> mapAsList(Iterable<S> sourceObject, Class<D> destinationClass);
}
