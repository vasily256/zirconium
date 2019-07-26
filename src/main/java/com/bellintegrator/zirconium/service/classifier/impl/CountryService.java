package com.bellintegrator.zirconium.service.classifier.impl;

import com.bellintegrator.zirconium.dao.ClassifierDao;
import com.bellintegrator.zirconium.model.Country;
import com.bellintegrator.zirconium.model.mapper.Mapper;
import com.bellintegrator.zirconium.service.classifier.ClassifierService;
import com.bellintegrator.zirconium.view.CountryView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис получения справочника стран мира
 */
@Service("countries")
@AllArgsConstructor
public class CountryService implements ClassifierService<CountryView> {

    private final ClassifierDao<Country> dao;
    private final Mapper<Country, CountryView> mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CountryView> list() {
        List<Country> countries =  dao.findAll();
        return mapper.toViewList(countries);
    }
}