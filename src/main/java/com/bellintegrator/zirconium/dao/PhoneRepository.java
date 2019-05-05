package com.bellintegrator.zirconium.dao;

import com.bellintegrator.zirconium.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
