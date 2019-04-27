package com.bellintegrator.zirconium.service.impl;

import com.bellintegrator.zirconium.exception.EntityNotFoundException;
import com.bellintegrator.zirconium.service.ViewService;
import com.bellintegrator.zirconium.view.OfficeView;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Сервис для работы с mock-объектами офисов
 */
@Service
public class ViewServiceImpl implements ViewService {

    private final Map<Long, Object> offices = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public ViewServiceImpl() {
        OfficeView office = new OfficeView(
                1,
                1,
                "Исследовательский центр",
                "г. Москва, ул. Вербная, д. 5",
                "74957870544",
                true
        );

        save("office", office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Collection<?> list(String viewName, Object office) {
        return offices.values(); // переменная office пока не использутся
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Object get(String viewName, long id) {
        Object office = offices.get(id);
        if (office == null) {
            throw new EntityNotFoundException("office id " + id + " not found");
        }
        return offices.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(String viewName, Object office) {
        long id = 0;
        try {
            id = ((OfficeView) office).getId();
        } catch (Exception e) {
            throw new RuntimeException(
                "******" + office.getClass().getSimpleName() + "*******"
            );
        }
        if (!offices.containsKey(id)) {
            throw new EntityNotFoundException("can't update: office id " + id + " not found");
        }
        offices.put(id, office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long save(String viewName, Object office) {
        long id = counter.incrementAndGet();
        ((OfficeView) office).setId(id);
        offices.putIfAbsent(id, office);
        return id;
    }
}
