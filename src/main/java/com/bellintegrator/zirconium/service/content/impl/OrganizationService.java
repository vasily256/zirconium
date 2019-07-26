package com.bellintegrator.zirconium.service.content.impl;

import com.bellintegrator.zirconium.dao.ContentDao;
import com.bellintegrator.zirconium.model.Organization;
import com.bellintegrator.zirconium.model.mapper.Mapper;
import com.bellintegrator.zirconium.service.content.ContentService;
import com.bellintegrator.zirconium.view.OrganizationView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Сервис для работы с объектами организаций
 */
@Service
@AllArgsConstructor
public class OrganizationService implements ContentService<OrganizationView> {

    private final ContentDao<Organization> dao;
    private final Mapper<Organization, OrganizationView> mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Collection<OrganizationView> list(OrganizationView organizationView) {
        Organization organization = mapper.toEntity(organizationView);
        List<Organization> organizations = dao.findAll(organization);
        return mapper.toViewList(organizations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrganizationView get(long id) {
        Organization organization = dao.findById(id);
        return mapper.toView(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationView organizationView) {
        Organization organization = mapper.toEntity(organizationView);
        dao.update(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long save(OrganizationView organizationView) {
        Organization organization = mapper.toEntity(organizationView);
        return dao.save(organization);
    }
}
