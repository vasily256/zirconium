package com.bellintegrator.zirconium.model.mapper;

import com.bellintegrator.zirconium.model.Organization;
import com.bellintegrator.zirconium.view.OrganizationView;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationMapper implements Mapper<Organization, OrganizationView> {

    private final MapperFacade fullMapperFacade;
    private final MapperFacade shortMapperFacade;

    @Autowired
    public OrganizationMapper(MapperFactory mapperFactory1,
                              MapperFactory mapperFactory2,
                              PhoneConverter converter) {

        mapperFactory1
                .getConverterFactory()
                .registerConverter("PhoneToPhone", converter);

        mapperFactory1
                .classMap(Organization.class, OrganizationView.class)
                .fieldMap("phones", "phone").converter("PhoneToPhone").bToA().add()
                .fieldAToB("phones{phone}", "phone{}")
                .byDefault()
                .register();

        fullMapperFacade = mapperFactory1.getMapperFacade();

        mapperFactory2
                .classMap(Organization.class, OrganizationView.class)
                .exclude("fullName")
                .exclude("inn")
                .exclude("kpp")
                .exclude("address")
                .exclude("phones")
                .byDefault()
                .register();

        shortMapperFacade = mapperFactory2.getMapperFacade();
    }

    @Override
    public Organization toEntity(OrganizationView orgView) {
        return fullMapperFacade.map(orgView, Organization.class);
    }

    @Override
    public OrganizationView toView(Organization org) {
        return fullMapperFacade.map(org, OrganizationView.class);
    }

    @Override
    public List<Organization> toEntityList(Iterable<OrganizationView> views) {
        return shortMapperFacade.mapAsList(views, Organization.class);
    }

    @Override
    public List<OrganizationView> toViewList(Iterable<Organization> views) {
        return shortMapperFacade.mapAsList(views, OrganizationView.class);
    }
}
