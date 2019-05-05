package com.bellintegrator.zirconium.service.content.impl;

import com.bellintegrator.zirconium.model.Office;
import com.bellintegrator.zirconium.model.mapper.Mapper;
import com.bellintegrator.zirconium.dao.ContentDao;
import com.bellintegrator.zirconium.service.content.ContentService;
import com.bellintegrator.zirconium.view.OfficeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Сервис для работы с объектами офисов
 */
@Service("office")
public class OfficeService implements ContentService<OfficeView> {

    private final ContentDao<Office> dao;
    private final Mapper<Office, OfficeView> mapper;

    @Autowired
    public OfficeService(ContentDao<Office> dao,
                         Mapper<Office, OfficeView> mapper) {

        this.dao = dao;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Collection<OfficeView> list(OfficeView officeView) {
        Office office = mapper.toEntity(officeView);
        List<Office> offices = dao.findAll(office);
        return mapper.toViewList(offices);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeView get(long id) {
        Office office = dao.findById(id);
        return mapper.toView(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeView officeView) {
        Office office = mapper.toEntity(officeView);
        dao.update(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long save(OfficeView officeView) {
        Office office = mapper.toEntity(officeView);
        return dao.save(office);
    }
}
