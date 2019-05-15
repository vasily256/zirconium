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

    private final MapperFacade fullMapperFacade;
    private final MapperFacade shortMapperFacade;

    @Autowired
    public OfficeMapper(MapperFactory mapperFactory1,
                        MapperFactory mapperFactory2,
                        PhoneConverter converter) {

        mapperFactory1
                .getConverterFactory()
                .registerConverter("PhoneToPhone", converter);

        mapperFactory1
                .classMap(Office.class, OfficeView.class)
                .fieldMap("phones", "phone").converter("PhoneToPhone").bToA().add()
                .fieldAToB("phones{phone}", "phone{}")
                .byDefault()
                .register();

        fullMapperFacade = mapperFactory1.getMapperFacade();

        mapperFactory2
            .classMap(Office.class, OfficeView.class)
            .exclude("orgId")
            .exclude("address")
            .exclude("phones")
            .byDefault()
            .register();

        shortMapperFacade = mapperFactory2.getMapperFacade();
    }

    @Override
    public Office toEntity(OfficeView officeView) {
        return fullMapperFacade.map(officeView, Office.class);
    }

    @Override
    public OfficeView toView(Office office) {
        return fullMapperFacade.map(office, OfficeView.class);
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
