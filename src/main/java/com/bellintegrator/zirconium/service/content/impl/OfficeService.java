package com.bellintegrator.zirconium.service.content.impl;

import com.bellintegrator.zirconium.exception.EntityNotFoundException;
import com.bellintegrator.zirconium.model.Office;
import com.bellintegrator.zirconium.model.Phone;
import com.bellintegrator.zirconium.model.mapper.Mapper;
import com.bellintegrator.zirconium.repository.OfficeRepository;
import com.bellintegrator.zirconium.repository.OfficeSpecification;
import com.bellintegrator.zirconium.repository.PhoneRepository;
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
    private final PhoneRepository phoneRepository;
    private final Mapper<Office, OfficeView> mapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OfficeService(OfficeRepository officeRepository,
                         PhoneRepository phoneRepository,
                         Mapper<Office, OfficeView> mapper) {

        this.officeRepository = officeRepository;
        this.phoneRepository = phoneRepository;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Collection<OfficeView> list(OfficeView officeView) {
        OfficeSpecification officeSpec = new OfficeSpecification(officeView);
        List<Office> offices = officeRepository.findAll(officeSpec);

        return mapper.toViewList(offices);
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
        return mapper.toView(office);
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

        Set<Phone> oldPhones = office.getPhone();
        phoneRepository.deleteAll(oldPhones);
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
        Office office = mapper.toEntity(officeView);
        officeRepository.save(office);
        return office.getId();
    }
}
