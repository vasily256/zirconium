package com.bellintegrator.zirconium.service.content.impl;

import com.bellintegrator.zirconium.dao.ContentDao;
import com.bellintegrator.zirconium.model.User;
import com.bellintegrator.zirconium.model.mapper.Mapper;
import com.bellintegrator.zirconium.service.content.ContentService;
import com.bellintegrator.zirconium.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Сервис для работы с объектами пользователей
 */
@Service
public class UserService implements ContentService<UserView> {

    private final ContentDao<User> dao;
    private final Mapper<User, UserView> mapper;

    @Autowired
    public UserService(ContentDao<User> dao,
                       Mapper<User, UserView> mapper) {

        this.dao = dao;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Collection<UserView> list(UserView userView) {
        User user = mapper.toEntity(userView);
        List<User> users = dao.findAll(user);
        return mapper.toViewList(users);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public UserView get(long id) {
        User user = dao.findById(id);
        return mapper.toView(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(UserView userView) {
        User user = mapper.toEntity(userView);
        dao.update(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long save(UserView userView) {
        User user = mapper.toEntity(userView);
        return dao.save(user);
    }
}
