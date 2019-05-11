package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.dao.ContentDao;
import com.bellintegrator.zirconium.dao.OfficeRepository;
import com.bellintegrator.zirconium.exception.EntityNotFoundException;
import com.bellintegrator.zirconium.model.Office;
import com.bellintegrator.zirconium.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class OfficeDao implements ContentDao<Office> {
    private final OfficeRepository officeRepository;
    private final EntityManager entityManager;

    @Autowired
    public OfficeDao(OfficeRepository officeRepository, EntityManager entityManager) {
        this.officeRepository = officeRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Office> findAll(Office office) {
        OfficeSpecification officeSpec = new OfficeSpecification(office);
        return officeRepository.findAll(officeSpec);
    }

    @Override
    public Office findById(long id) {
        Optional<Office> container = officeRepository.findById(id);
        if (!container.isPresent()) {
            throw new EntityNotFoundException("office id " + id + " not found");
        }

        return container.get();
    }

    @Override
    public long save(Office office) {
        officeRepository.save(office);
        return office.getId();
    }

    @Override
    public void update(Office office) {
        long id = office.getId();
        Optional<Office> container = officeRepository.findById(id);
        if (!container.isPresent()) {
            throw new EntityNotFoundException("can't update: office id " + id + " not found");
        }

        Office currentOffice = container.get();

        currentOffice.setName(office.getName());
        currentOffice.setAddress(office.getAddress());
        currentOffice.setActive(office.isActive());

        Set<Phone> newPhones = office.getPhone();
        Set<Phone> currentPhones = new HashSet<>(currentOffice.getPhone());
        if (newPhones != null) {
            currentOffice.setPhone(newPhones);
        }

        officeRepository.saveAndFlush(currentOffice);

        Query query = entityManager.createNativeQuery(CustomQueries.DELETE_UNUSED_PHONES);
        for (Phone phone : currentPhones) {
            query.setParameter("id", phone.getId());
        }
        query.executeUpdate();
    }
}
