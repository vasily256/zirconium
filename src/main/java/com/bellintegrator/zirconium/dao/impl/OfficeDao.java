package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.dao.ContentDao;
import com.bellintegrator.zirconium.dao.OfficeRepository;
import com.bellintegrator.zirconium.dao.PhoneRepository;
import com.bellintegrator.zirconium.exception.EntityNotFoundException;
import com.bellintegrator.zirconium.model.Office;
import com.bellintegrator.zirconium.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class OfficeDao implements ContentDao<Office> {
    private final OfficeRepository officeRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public OfficeDao(OfficeRepository officeRepository,
                     PhoneRepository phoneRepository) {
        this.officeRepository = officeRepository;
        this.phoneRepository = phoneRepository;
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

        Office officeDb = container.get();

        officeDb.setName(office.getName());
        officeDb.setAddress(office.getAddress());
        officeDb.setActive(office.isActive());

        Set<Phone> phones = office.getPhone();
        if (phones != null) {
            officeDb.getPhone().clear();
            officeDb.setPhone(office.getPhone());
        }

        officeRepository.save(officeDb);
    }
}
