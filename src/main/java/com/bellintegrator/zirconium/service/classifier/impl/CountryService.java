package com.bellintegrator.zirconium.service.classifier.impl;

import com.bellintegrator.zirconium.dao.ClassifierDao;
import com.bellintegrator.zirconium.model.Country;
import com.bellintegrator.zirconium.model.mapper.Mapper;
import com.bellintegrator.zirconium.service.classifier.ClassifierService;
import com.bellintegrator.zirconium.view.CountryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис получения справочника стран мира
 */
@Service("countries")
public class CountryService implements ClassifierService<CountryView> {

    private final ClassifierDao<Country> dao;
    private final Mapper<Country, CountryView> mapper;

    @Autowired
    public CountryService(ClassifierDao<Country> dao,
                          Mapper<Country, CountryView> mapper) {

        this.dao = dao;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CountryView> list() {
        List<Country> countries =  dao.findAll();
        return mapper.toViewList(countries);
    }
}