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

    private final MapperFacade reverseMapperFacade;
    private final MapperFacade mapperFacade;
    private final MapperFacade shortMapperFacade;

    @Autowired
    public OfficeMapper(MapperFactory mapperFactory1,
                        MapperFactory mapperFactory2,
                        MapperFactory mapperFactory3,
                        PhoneConverter converter) {

        mapperFactory1
                .getConverterFactory()
                .registerConverter("PhoneToPhone", converter);

        mapperFactory1
                .classMap(OfficeView.class, Office.class)
                .fieldMap("phone", "phones").converter("PhoneToPhone").add()
                .byDefault()
                .register();

        mapperFacade = mapperFactory1.getMapperFacade();

        mapperFactory2
            .classMap(Office.class, OfficeView.class)
            .fieldAToB("phones{phone}", "phone{}")
            .byDefault()
            .register();

        reverseMapperFacade = mapperFactory2.getMapperFacade();

        mapperFactory3
            .classMap(Office.class, OfficeView.class)
            .exclude("orgId")
            .exclude("address")
            .exclude("phones")
            .byDefault()
            .register();

        shortMapperFacade = mapperFactory3.getMapperFacade();

    }

    @Override
    public Office toEntity(OfficeView officeView) {
        return mapperFacade.map(officeView, Office.class);
    }

    @Override
    public OfficeView toView(Office office) {
        return reverseMapperFacade.map(office, OfficeView.class);
    }

    @Override
    public List<Office> toEntityList(Iterable<OfficeView> views) {
        return shortMapperFacade.mapAsList(views, Office.class);
    }

    @Override
    public List<OfficeView> toViewList(Iterable<Office> views) {
        return shortMapperFacade.mapAsList(views, OfficeView.class);
    }
}
