package com.bellintegrator.zirconium.service.content.impl;

import com.bellintegrator.zirconium.exception.EntityNotFoundException;
import com.bellintegrator.zirconium.service.content.ContentService;
import com.bellintegrator.zirconium.view.OfficeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Сервис для работы с mock-объектами офисов
 */
@Service("mock-office")
public class MockOfficeService implements ContentService<OfficeView> {

    private final Map<Long, OfficeView> views = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    public MockOfficeService() {
        OfficeView view = new OfficeView(
                1L,
                1L,
                "Исследовательский центр",
                "г. Москва, ул. Вербная, д. 5",
                Arrays.asList("74957870544", "74957870545"),
                true
        );

        save(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Collection<OfficeView> list(OfficeView officeView) {
        return views.values(); // переменная view не использутся
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeView get(long id) {
        OfficeView view = views.get(id);
        if (view == null) {
            throw new EntityNotFoundException("office id " + id + " not found");
        }
        return views.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeView officeView) {
        long id = officeView.getId();
        if (!views.containsKey(id)) {
            throw new EntityNotFoundException("can't update: office id " + id + " not found");
        }
        views.put(id, officeView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long save(OfficeView officeView) {
        long id = counter.incrementAndGet();
        officeView.setId(id);
        views.putIfAbsent(id, officeView);
        return id;
    }
}
