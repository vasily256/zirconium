package com.bellintegrator.zirconium.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class ZirconiumMapperFactory extends AbstractFactoryBean<MapperFactory> {
    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    protected MapperFactory createInstance() throws Exception {
        return new DefaultMapperFactory.Builder().build();
    }
}
