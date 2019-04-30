package com.bellintegrator.zirconium.service.classifier.impl;

import com.bellintegrator.zirconium.model.DocumentType;
import com.bellintegrator.zirconium.model.mapper.MapperFacade;
import com.bellintegrator.zirconium.repository.DocumentTypeRepository;
import com.bellintegrator.zirconium.service.classifier.ClassifierService;
import com.bellintegrator.zirconium.view.DocumentTypeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("docs")
public class DocumentTypeService implements ClassifierService {

    private final DocumentTypeRepository documentRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public DocumentTypeService(DocumentTypeRepository documentRepository,
                               MapperFacade mapperFacade) {

        this.documentRepository = documentRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<?> list() {
        List<DocumentType> documents =  documentRepository.findAll();
        return mapperFacade.mapAsList(documents, DocumentTypeView.class);
    }
}