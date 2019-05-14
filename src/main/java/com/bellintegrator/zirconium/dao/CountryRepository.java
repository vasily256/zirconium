package com.bellintegrator.zirconium.dao;

import com.bellintegrator.zirconium.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCode(String code);
}
