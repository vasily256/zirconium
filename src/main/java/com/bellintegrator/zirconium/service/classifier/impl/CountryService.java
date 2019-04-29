package com.bellintegrator.zirconium.service.classifier.impl;

import com.bellintegrator.zirconium.model.Country;
import com.bellintegrator.zirconium.repository.CountryRepository;
import com.bellintegrator.zirconium.service.classifier.ClassifierService;
import com.bellintegrator.zirconium.view.CountryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("countries")
public class CountryService implements ClassifierService {

    private final CountryRepository repository;

    @Autowired
    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<?> list() {
        List<Country> countries =  repository.findAll();
        List<CountryView> countryViews = new ArrayList<>();
        for (Country country : countries) {
            countryViews.add(new CountryView(country.getCode(), country.getName()));
        }
        return countryViews;
    }
}