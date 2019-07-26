package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.dao.ClassifierDao;
import com.bellintegrator.zirconium.dao.CountryRepository;
import com.bellintegrator.zirconium.model.Country;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CountryDao implements ClassifierDao<Country> {
    private final CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
