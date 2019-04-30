package com.bellintegrator.zirconium.service.classifier.impl;

import com.bellintegrator.zirconium.model.Country;
import com.bellintegrator.zirconium.model.mapper.MapperFacade;
import com.bellintegrator.zirconium.repository.CountryRepository;
import com.bellintegrator.zirconium.service.classifier.ClassifierService;
import com.bellintegrator.zirconium.view.CountryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("countries")
public class CountryService implements ClassifierService {

    private final CountryRepository countryRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public CountryService(CountryRepository countryRepository,
                          MapperFacade mapperFacade) {

        this.countryRepository = countryRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<?> list() {
        List<Country> countries =  countryRepository.findAll();
        return mapperFacade.mapAsList(countries, CountryView.class);
    }
}