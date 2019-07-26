package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.dao.ContentDao;
import com.bellintegrator.zirconium.dao.OfficeRepository;
import com.bellintegrator.zirconium.dao.OrganizationRepository;
import com.bellintegrator.zirconium.exception.EntityNotFoundException;
import com.bellintegrator.zirconium.model.Office;
import com.bellintegrator.zirconium.model.Phone;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class OfficeDao implements ContentDao<Office> {
    private final OfficeRepository officeRepository;
    private final OrganizationRepository organizationRepository;
    private final EntityManager entityManager;

    @Override
    public List<Office> findAll(Office office) {
        if (office == null) {
            throw new IllegalArgumentException("office can not be null");
        }

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
        if (office == null) {
            throw new IllegalArgumentException("office can not be null");
        }

        Long orgId = office.getOrgId();
        if (!organizationRepository.existsById(orgId)) {
            throw new EntityNotFoundException("organization id " + orgId + " not found");
        }


        officeRepository.save(office);
        return office.getId();
    }

    @Override
    public void update(Office office) {
        if (office == null) {
            return;
        }

        long id = office.getId();
        Optional<Office> container = officeRepository.findById(id);
        if (!container.isPresent()) {
            throw new EntityNotFoundException("can't update: office id " + id + " not found");
        }

        Office currentOffice = container.get();

        if (office.getName() != null) {
            currentOffice.setName(office.getName());
        }

        if (office.getAddress() != null) {
            currentOffice.setAddress(office.getAddress());
        }

        if (office.getIsActive() != null) {
            currentOffice.setIsActive(office.getIsActive());
        }

        Set<Phone> oldPhones = new HashSet<>(currentOffice.getPhones());

        if (office.getPhones() != null) {
            currentOffice.setPhones(office.getPhones());
        }

        officeRepository.saveAndFlush(currentOffice);

        if (oldPhones.size() > 0) {
            Query query = entityManager.createNativeQuery(CustomQueries.DELETE_UNUSED_PHONES);
            List<Long> phoneIds = oldPhones.stream().map(Phone::getId).collect(Collectors.toList());
            query.setParameter("id", phoneIds);
            query.executeUpdate();
        }
    }
}
