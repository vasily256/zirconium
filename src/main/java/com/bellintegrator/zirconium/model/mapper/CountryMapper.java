package com.bellintegrator.zirconium.model.mapper;

import com.bellintegrator.zirconium.model.Country;
import com.bellintegrator.zirconium.view.CountryView;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryMapper implements Mapper<Country, CountryView> {
    private final MapperFacade mapperFacade;

    @Autowired
    public CountryMapper(MapperFactory mapperFactory) {
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public Country toEntity(CountryView view) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CountryView toView(Country entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Country> toEntityList(Iterable<CountryView> views) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<CountryView> toViewList(Iterable<Country> entities) {
        return mapperFacade.mapAsList(entities, CountryView.class);
    }
}
