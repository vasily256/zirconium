package com.bellintegrator.zirconium.service.impl;

import com.bellintegrator.zirconium.service.OfficeService;
import com.bellintegrator.zirconium.view.OfficeView;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Сервис для работы с mock-объектами офисов
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final Map<Long, OfficeView> offices = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public OfficeServiceImpl() {
        OfficeView office = new OfficeView(
                1,
                1,
                "Исследовательский центр",
                "г. Москва, ул. Вербная, д. 5",
                "74957870544",
                true
        );

        save(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Collection<OfficeView> list(OfficeView office) {
        return offices.values(); // переменная office пока не использутся
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeView office(long id) {
        OfficeView office = offices.get(id);
        if (office == null) {
            throw new NoSuchElementException("office id " + id + " not found");
        }
        return offices.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeView office) {
        long id = office.getId();
        if (!offices.containsKey(id)) {
            throw new NoSuchElementException("office id " + id + " not found");
        }
        offices.put(id, office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long save(OfficeView office) {
        long id = counter.incrementAndGet();
        office.setId(id);
        offices.putIfAbsent(id, office);
        return id;
    }
}
