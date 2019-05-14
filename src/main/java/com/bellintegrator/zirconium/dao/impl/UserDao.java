package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.dao.*;
import com.bellintegrator.zirconium.exception.EntityNotFoundException;
import com.bellintegrator.zirconium.model.Country;
import com.bellintegrator.zirconium.model.Document;
import com.bellintegrator.zirconium.model.DocumentType;
import com.bellintegrator.zirconium.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class UserDao implements ContentDao<User> {
    private final UserRepository userRepository;
    private final OfficeRepository officeRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final CountryRepository countryRepository;
    private final EntityManager entityManager;

    @Autowired
    public UserDao(UserRepository userRepository,
                   OfficeRepository officeRepository,
                   DocumentTypeRepository documentTypeRepository,
                   CountryRepository countryRepository,
                   EntityManager entityManager) {
        this.userRepository = userRepository;
        this.officeRepository = officeRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.countryRepository = countryRepository;
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

        log.debug("LOADING: " + container.get().toString());
        return container.get();
    }

    @Override
    public long save(User user) {
        log.debug("SAVING: " + user.toString());

        Long officeId = user.getOfficeId();

        if (!officeRepository.existsById(officeId)) {
            throw new EntityNotFoundException("office id " + officeId + " not found");
        }

        Country country = user.getCountry();

        if (country != null) {
            String countryCode = country.getCode();
            if (countryCode != null) {
                Country countryFromRepository = countryRepository.findByCode(countryCode);
                if (countryFromRepository == null) {
                    throw new EntityNotFoundException("Illegal country code: " + countryCode);
                }
                user.setCountry(countryFromRepository);
            } else {
                user.setCountry(null);
            }
        }

        Document document = user.getDocument();

        if (document != null && document.getDocumentType() != null) {
            String docCode = document.getDocumentType().getCode();
            if (docCode != null) {
                DocumentType documentType = documentTypeRepository.findByCode(docCode);
                if (documentType == null) {
                    throw new EntityNotFoundException("Illegal document code: " + docCode);
                }
                document.setDocumentType(documentType);
            } else {
                document.setDocumentType(null);
            }
        }

        user.setDocument(null);
        userRepository.save(user);

        if (document != null) {
            document.setUserId(user.getId());
            user.setDocument(document);
        }

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
