package com.bellintegrator.zirconium.model.mapper;

import com.bellintegrator.zirconium.model.Office;
import com.bellintegrator.zirconium.view.OfficeView;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeMapper implements Mapper<Office, OfficeView> {

    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeMapper(MapperFactory mapperFactory,
                        PhoneConverter converter) {
        mapperFactory
            .getConverterFactory()
            .registerConverter("PhoneToPhone", converter);

        mapperFactory
            .classMap(Office.class, OfficeView.class)
            .fieldAToB("phone{phone}", "phone{}")
            .fieldMap("phone", "phone").converter("PhoneToPhone").bToA().add()
            .byDefault()
            .register();

        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public Office toEntity(OfficeView officeView) {
        return mapperFacade.map(officeView, Office.class);
    }

    @Override
    public OfficeView toView(Office office) {
        return mapperFacade.map(office, OfficeView.class);
    }

    @Override
    public List<Office> toEntityList(Iterable<OfficeView> views) {
        return mapperFacade.mapAsList(views, Office.class);
    }

    @Override
    public List<OfficeView> toViewList(Iterable<Office> views) {
        return mapperFacade.mapAsList(views, OfficeView.class);
    }
}
