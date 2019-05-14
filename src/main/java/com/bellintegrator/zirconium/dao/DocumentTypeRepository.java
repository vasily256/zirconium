package com.bellintegrator.zirconium.dao;

import com.bellintegrator.zirconium.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {
    DocumentType findByCode(String code);
}
