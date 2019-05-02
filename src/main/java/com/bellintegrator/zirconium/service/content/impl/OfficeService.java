package com.bellintegrator.zirconium.service.content.impl;

import com.bellintegrator.zirconium.exception.EntityNotFoundException;
import com.bellintegrator.zirconium.model.Address;
import com.bellintegrator.zirconium.model.Office;
import com.bellintegrator.zirconium.model.mapper.MapperFacade;
import com.bellintegrator.zirconium.repository.OfficeRepository;
import com.bellintegrator.zirconium.service.content.ContentService;
import com.bellintegrator.zirconium.view.OfficeView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


/**
 * Сервис для работы с mock-объектами офисов
 */
@Service("office")
public class OfficeService implements ContentService<OfficeView> {

    private final Map<Long, OfficeView> views = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    private final ObjectMapper objectMapper;
    private final OfficeRepository officeRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeService(OfficeRepository officeRepository,
                         MapperFacade mapperFacade,
                         ObjectMapper mapper) {

        this.officeRepository = officeRepository;
        this.mapperFacade = mapperFacade;
        this.objectMapper = mapper;

        mapperFacade.getMapperFactory()
                .classMap(Office.class, OfficeView.class)
                .fieldAToB("address.address", "address")
                .fieldAToB("phone{phone}", "phone{}")
                .byDefault()
                .register();

        OfficeView view = new OfficeView(
                1L,
                1L,
                "Исследовательский центр",
                "г. Москва, ул. Вербная, д. 5",
                Arrays.asList("74957870544", "74957870545"),
                true
        );

//        save(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Collection<OfficeView> list(Object view) {
        OfficeView officeView = deserialize(view);
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
    public void update(Object view) {
        OfficeView officeView = deserialize(view);

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
    public long save(Object view) {
        OfficeView officeView = deserialize(view);
        Office office = mapperFacade.map(officeView, Office.class);
        Address address = new Address(officeView.getAddress());
        office.setAddress(address);
        officeRepository.save(office);
        return office.getId();
    }

    private OfficeView deserialize(Object json) {
        return objectMapper.convertValue(json, OfficeView.class);
    }
}
