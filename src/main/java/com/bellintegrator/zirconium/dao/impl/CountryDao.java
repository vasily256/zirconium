package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.dao.ClassifierDao;
import com.bellintegrator.zirconium.dao.CountryRepository;
import com.bellintegrator.zirconium.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryDao implements ClassifierDao<Country> {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryDao(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
