package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.dao.ClassifierDao;
import com.bellintegrator.zirconium.dao.DocumentTypeRepository;
import com.bellintegrator.zirconium.model.DocumentType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class DocumentTypeDao implements ClassifierDao<DocumentType> {
    private final DocumentTypeRepository documentTypeRepository;

    @Override
    public List<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }
}
