package com.bellintegrator.zirconium.service.classifier.impl;

import com.bellintegrator.zirconium.dao.ClassifierDao;
import com.bellintegrator.zirconium.model.DocumentType;
import com.bellintegrator.zirconium.model.mapper.Mapper;
import com.bellintegrator.zirconium.service.classifier.ClassifierService;
import com.bellintegrator.zirconium.view.DocumentTypeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("docs")
public class DocumentTypeService implements ClassifierService<DocumentTypeView> {

    private final ClassifierDao<DocumentType> dao;
    private final Mapper<DocumentType, DocumentTypeView> mapper;

    @Autowired
    public DocumentTypeService(ClassifierDao<DocumentType> dao,
                               Mapper<DocumentType, DocumentTypeView> mapper) {

        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public List<DocumentTypeView> list() {
        List<DocumentType> documents =  dao.findAll();
        return mapper.toViewList(documents);
    }
}