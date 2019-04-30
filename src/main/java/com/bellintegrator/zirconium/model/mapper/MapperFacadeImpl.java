package com.bellintegrator.zirconium.model.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapperFacadeImpl implements MapperFacade {

    private final MapperFactory mapperFactory;

    @Autowired
    public MapperFacadeImpl(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    @Override
    public <S, D> List<D> mapAsList(Iterable<S> sourceObject, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().mapAsList(sourceObject, destinationClass);
    }
}
