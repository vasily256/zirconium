package com.bellintegrator.zirconium.model.mapper;

import ma.glasnost.orika.MapperFactory;

import java.util.List;

public interface MapperFacade {

    <S, D> D map(S sourceObject, Class<D> destinationClass);

    <S, D> List<D> mapAsList(Iterable<S> sourceObject, Class<D> destinationClass);

    MapperFactory getMapperFactory();
}
