package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.dao.ContentDao;
import com.bellintegrator.zirconium.dao.OrganizationRepository;
import com.bellintegrator.zirconium.exception.EntityNotFoundException;
import com.bellintegrator.zirconium.model.Organization;
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
public class OrganizationDao implements ContentDao<Organization> {
    private final OrganizationRepository orgRepository;
    private final EntityManager entityManager;

    @Override
    public List<Organization> findAll(Organization org) {
        if (org == null) {
            throw new IllegalArgumentException("organization can not be null");
        }

        OrganizationSpecification orgSpec = new OrganizationSpecification(org);
        return orgRepository.findAll(orgSpec);
    }

    @Override
    public Organization findById(long id) {
        Optional<Organization> container = orgRepository.findById(id);
        if (!container.isPresent()) {
            throw new EntityNotFoundException("organization id " + id + " not found");
        }

        return container.get();
    }

    @Override
    public long save(Organization org) {
        if (org == null) {
            throw new IllegalArgumentException("organization can not be null");
        }

        orgRepository.save(org);
        return org.getId();
    }

    @Override
    public void update(Organization org) {
        if (org == null) {
            return;
        }

        long id = org.getId();
        Optional<Organization> container = orgRepository.findById(id);
        if (!container.isPresent()) {
            throw new EntityNotFoundException("can't update: organization id " + id + " not found");
        }

        Organization currentOrganization = container.get();

        if (org.getName() != null) {
            currentOrganization.setName(org.getName());
        }

        if (org.getFullName() != null) {
            currentOrganization.setFullName(org.getFullName());
        }

        if (org.getInn() != null) {
            currentOrganization.setInn(org.getInn());
        }

        if (org.getKpp() != null) {
            currentOrganization.setKpp(org.getKpp());
        }

        if (org.getAddress() != null) {
            currentOrganization.setAddress(org.getAddress());
        }

        if (org.getIsActive() != null) {
            currentOrganization.setIsActive(org.getIsActive());
        }

        Set<Phone> oldPhones = new HashSet<>(currentOrganization.getPhones());

        if (org.getPhones() != null) {
            currentOrganization.setPhones(org.getPhones());
        }

        orgRepository.saveAndFlush(currentOrganization);

        if (oldPhones.size() > 0) {
            Query query = entityManager.createNativeQuery(CustomQueries.DELETE_UNUSED_PHONES);
            List<Long> phoneIds = oldPhones.stream().map(Phone::getId).collect(Collectors.toList());
            query.setParameter("id", phoneIds);
            query.executeUpdate();
        }
    }
}
