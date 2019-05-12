package com.bellintegrator.zirconium.model.mapper;

import com.bellintegrator.zirconium.model.User;
import com.bellintegrator.zirconium.view.UserView;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper implements Mapper<User, UserView> {

    private final MapperFacade fullMapperFacade;
    private final MapperFacade shortMapperFacade;

    @Autowired
    public UserMapper(MapperFactory mapperFactory1,
                      MapperFactory mapperFactory2,
                      PhoneConverter converter) {
//        mapperFactory1
//            .getConverterFactory()
//            .registerConverter("PhoneToPhone", converter);
//
//        mapperFactory1
//            .classMap(User.class, UserView.class)
//            .fieldAToB("phones{phone}", "phone{}")
//            .fieldMap("phone", "phones").converter("PhoneToPhone").bToA().add()
//            .byDefault()
//            .register();

        fullMapperFacade = mapperFactory1.getMapperFacade();

//        mapperFactory2
//            .classMap(User.class, UserView.class)
//            .exclude("orgId")
//            .exclude("address")
//            .exclude("phone")
//            .byDefault()
//            .register();

        shortMapperFacade = mapperFactory2.getMapperFacade();
    }

    @Override
    public User toEntity(UserView userView) {
        return fullMapperFacade.map(userView, User.class);
    }

    @Override
    public UserView toView(User user) {
        return fullMapperFacade.map(user, UserView.class);
    }

    @Override
    public List<User> toEntityList(Iterable<UserView> views) {
        return fullMapperFacade.mapAsList(views, User.class);
    }

    @Override
    public List<UserView> toViewList(Iterable<User> views) {
        return shortMapperFacade.mapAsList(views, UserView.class);
    }
}
