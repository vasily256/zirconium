package com.bellintegrator.zirconium.service.content.impl;

import com.bellintegrator.zirconium.exception.EntityNotFoundException;
import com.bellintegrator.zirconium.model.Address;
import com.bellintegrator.zirconium.model.Office;
import com.bellintegrator.zirconium.model.mapper.MapperFacade;
import com.bellintegrator.zirconium.repository.OfficeRepository;
import com.bellintegrator.zirconium.service.content.ContentService;
import com.bellintegrator.zirconium.view.OfficeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Сервис для работы с объектами офисов
 */
@Service("office")
public class OfficeService implements ContentService<OfficeView> {

    private final OfficeRepository officeRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeService(OfficeRepository officeRepository,
                         MapperFacade mapperFacade) {

        this.officeRepository = officeRepository;
        this.mapperFacade = mapperFacade;

        mapperFacade.getMapperFactory()
                .classMap(Office.class, OfficeView.class)
                .fieldAToB("address.address", "address")
                .fieldAToB("phone{phone}", "phone{}")
                .byDefault()
                .register();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Collection<OfficeView> list(OfficeView officeView) {
        Office office = mapperFacade.map(officeView, Office.class);

        List<Office> offices = officeRepository.findAll(Example.of(office));
        return mapperFacade.mapAsList(offices, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeView get(long id) {
        Optional<Office> office = officeRepository.findById(id);
        if (!office.isPresent()) {
            throw new EntityNotFoundException("office id " + id + " not found");
        }

        return mapperFacade.map(office.get(), OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeView officeView) {

        long id = officeView.getId();
        Optional<Office> container = officeRepository.findById(id);
        if (!container.isPresent()) {
            throw new EntityNotFoundException("can't update: office id " + id + " not found");
        }
        Office office = container.get();

        office.getAddress().setAddress(officeView.getAddress());
        office.setName(officeView.getName());
        officeRepository.save(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long save(OfficeView officeView) {
        Office office = mapperFacade.map(officeView, Office.class);
        Address address = new Address(officeView.getAddress());
        office.setAddress(address);
        officeRepository.save(office);
        return office.getId();
    }
}
