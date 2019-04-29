package com.bellintegrator.zirconium.service.classifier.impl;

import com.bellintegrator.zirconium.model.DocumentType;
import com.bellintegrator.zirconium.repository.DocumentTypeRepository;
import com.bellintegrator.zirconium.service.classifier.ClassifierService;
import com.bellintegrator.zirconium.view.DocumentTypeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("docs")
public class DocumentTypeService implements ClassifierService {

    private final DocumentTypeRepository repository;

    @Autowired
    public DocumentTypeService(DocumentTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<?> list() {
        List<DocumentType> documents =  repository.findAll();
        List<DocumentTypeView> documentViews = new ArrayList<>();
        for (DocumentType document : documents) {
            documentViews.add(new DocumentTypeView(document.getCode(), document.getName()));
        }
        return documentViews;
    }
}