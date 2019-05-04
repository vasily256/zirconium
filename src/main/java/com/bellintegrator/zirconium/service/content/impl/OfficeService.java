package com.bellintegrator.zirconium.service.content.impl;

import com.bellintegrator.zirconium.exception.EntityNotFoundException;
import com.bellintegrator.zirconium.model.Office;
import com.bellintegrator.zirconium.model.Phone;
import com.bellintegrator.zirconium.model.mapper.MapperFacade;
import com.bellintegrator.zirconium.repository.OfficeRepository;
import com.bellintegrator.zirconium.repository.OfficeSpecification;
import com.bellintegrator.zirconium.service.content.ContentService;
import com.bellintegrator.zirconium.view.OfficeView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Сервис для работы с объектами офисов
 */
@Service("office")
public class OfficeService implements ContentService<OfficeView> {

    private final OfficeRepository officeRepository;
    private final MapperFacade mapperFacade;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OfficeService(OfficeRepository officeRepository,
                         MapperFacade mapperFacade) {

        this.officeRepository = officeRepository;
        this.mapperFacade = mapperFacade;

        mapperFacade.getMapperFactory()
                .classMap(Office.class, OfficeView.class)
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
        OfficeSpecification officeSpec = new OfficeSpecification(officeView);
        List<Office> offices = officeRepository.findAll(officeSpec);

        return mapperFacade.mapAsList(offices, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeView get(long id) {
        Optional<Office> container = officeRepository.findById(id);
        if (!container.isPresent()) {
            throw new EntityNotFoundException("office id " + id + " not found");
        }

        Office office = container.get();
        return mapperFacade.map(office, OfficeView.class);
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

        String name = officeView.getName();
        office.setName(name);

        String address = officeView.getAddress();
        office.setAddress(address);

        Set<Phone> phones = officeView.getPhone()
                                       .stream()
                                       .map(Phone::new)
                                       .collect(Collectors.toSet());
        office.setPhone(phones);

        officeRepository.save(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long save(OfficeView officeView) {
        Office office = mapperFacade.map(officeView, Office.class);

        String address = officeView.getAddress();
        if (address != null) {
            office.setAddress(address);
        }

        Set<String> strPhones = officeView.getPhone();
        if (strPhones != null) {
            Set<Phone> phones = strPhones.stream()
                                          .map(Phone::new)
                                          .collect(Collectors.toSet());
            office.setPhone(phones);
        }

        officeRepository.save(office);
        return office.getId();
    }
}
