package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.dao.ContentDao;
import com.bellintegrator.zirconium.dao.UserRepository;
import com.bellintegrator.zirconium.exception.EntityNotFoundException;
import com.bellintegrator.zirconium.model.User;
import com.bellintegrator.zirconium.model.Phone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Slf4j
public class UserDao implements ContentDao<User> {
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Autowired
    public UserDao(UserRepository userRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAll(User user) {
//        UserSpecification userSpec = new UserSpecification(user);
//        return userRepository.findAll(userSpec);
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        Optional<User> container = userRepository.findById(id);
        if (!container.isPresent()) {
            throw new EntityNotFoundException("user id " + id + " not found");
        }

        return container.get();
    }

    @Override
    public long save(User user) {
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public void update(User user) {
//        long id = user.getId();
//        Optional<User> container = userRepository.findById(id);
//        if (!container.isPresent()) {
//            throw new EntityNotFoundException("can't update: user id " + id + " not found");
//        }
//
//        User currentUser = container.get();
//
//        currentUser.setName(user.getName());
//        currentUser.setAddress(user.getAddress());
//        currentUser.setActive(user.isActive());
//
//        Set<Phone> newPhones = user.getPhone();
//        Set<Phone> currentPhones = new HashSet<>(currentUser.getPhone());
//        if (newPhones != null) {
//            currentUser.setPhone(newPhones);
//        }
//
//        userRepository.saveAndFlush(currentUser);
//
//        Query query = entityManager.createNativeQuery(CustomQueries.DELETE_UNUSED_PHONES);
//        for (Phone phone : currentPhones) {
//            query.setParameter("id", phone.getId());
//        }
//        query.executeUpdate();
    }
}
