package com.bellintegrator.zirconium.dao.impl;

import com.bellintegrator.zirconium.dao.ClassifierDao;
import com.bellintegrator.zirconium.dao.DocumentTypeRepository;
import com.bellintegrator.zirconium.model.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeDao implements ClassifierDao<DocumentType> {
    private final DocumentTypeRepository documentTypeRepository;

    @Autowired
    public DocumentTypeDao(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public List<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }
}
