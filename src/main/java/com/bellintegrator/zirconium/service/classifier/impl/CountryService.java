package com.bellintegrator.zirconium.service.classifier.impl;

import com.bellintegrator.zirconium.model.Country;
import com.bellintegrator.zirconium.model.mapper.Mapper;
import com.bellintegrator.zirconium.repository.CountryRepository;
import com.bellintegrator.zirconium.service.classifier.ClassifierService;
import com.bellintegrator.zirconium.view.CountryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("countries")
public class CountryService implements ClassifierService<CountryView> {

    private final CountryRepository countryRepository;
    private final Mapper<Country, CountryView> mapper;

    @Autowired
    public CountryService(CountryRepository countryRepository,
                          Mapper<Country, CountryView> mapper) {

        this.countryRepository = countryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CountryView> list() {
        List<Country> countries =  countryRepository.findAll();
        return mapper.toViewList(countries);
    }
}